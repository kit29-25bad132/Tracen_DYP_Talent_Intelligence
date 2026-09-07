import type { ApiConfig, ApiError, ApiResponse } from '../types/api'

declare const __APP_ENV__: { VITE_API_BASE_URL?: string } | undefined

const DEFAULT_CONFIG: ApiConfig = {
  baseUrl: __APP_ENV__?.VITE_API_BASE_URL ?? 'http://localhost:8080/api',
  requestTimeoutMs: 10000,
  credentialsMode: 'omit',
}

let config: ApiConfig = DEFAULT_CONFIG

export function setApiConfig(next: ApiConfig): void {
  config = { ...next }
}

export function getApiConfig(): Readonly<ApiConfig> {
  return config
}

async function readJsonBodyOnce(response: Response): Promise<unknown> {
  try {
    return await response.json()
  } catch {
    return null
  }
}

async function normalizeError(response: Response): Promise<ApiError> {
  let status = response.status
  let message = 'Request failed'
  let fieldErrors: Record<string, string> | undefined

  if (response.status === 200 || response.status === 201) {
    return { kind: 'unknown', status, message }
  }

  if (response.status === 401) {
    return { kind: 'unauthorized', status, message: 'Authentication required' }
  }

  if (response.status === 403) {
    return { kind: 'forbidden', status, message: 'Access denied' }
  }

  if (response.status === 404) {
    return { kind: 'not-found', status, message: 'Resource not found' }
  }

  if (response.status === 409) {
    message = 'Request conflict'
  }

  if (response.status === 422 || response.status === 400) {
    message = 'Validation failed'
  }

  if (response.status >= 500) {
    return { kind: 'server-error', status, message: 'Service unavailable' }
  }

  try {
    const cloned = response.clone()
    const parsed = (await readJsonBodyOnce(cloned)) as Record<string, unknown> | null
    if (parsed && typeof parsed === 'object') {
      if ('message' in parsed && typeof parsed.message === 'string') {
        message = parsed.message
      }
      if ('errors' in parsed) {
        const rawErrors = parsed.errors
        if (rawErrors && typeof rawErrors === 'object' && !Array.isArray(rawErrors)) {
          fieldErrors = Object.fromEntries(
            Object.entries(rawErrors as Record<string, unknown>).map(([k, v]) => [k, String(v)]),
          )
        }
      }
    }
  } catch {
    // Keep server-provided fallback message
  }

  if (fieldErrors && Object.keys(fieldErrors).length > 0) {
    return { kind: 'validation', status, message, fieldErrors }
  }

  const kindMap: Record<number, ApiError['kind']> = {
    400: 'validation',
    409: 'conflict',
    422: 'validation',
  }

  return {
    kind: kindMap[status] ?? 'unknown',
    status,
    message,
  }
}

export function createRequest(path: string, options: RequestInit = {}) {
  const headers = new Headers(options.headers)
  headers.set('Content-Type', 'application/json')

  const method = (options.method ?? 'GET').toUpperCase()
  if (method !== 'GET' && method !== 'HEAD' && options.body !== undefined) {
    headers.set('Content-Type', 'application/json')
  }

  const token = loadStoredToken()
  if (token) {
    headers.set('Authorization', `Bearer ${token}`)
  }

  const controller = new AbortController()
  const timeout = setTimeout(() => controller.abort(), config.requestTimeoutMs)

  return {
    request: new Request(new URL(path, config.baseUrl).toString(), {
      ...options,
      method,
      headers,
      signal: controller.signal,
    }),
    cancel: () => {
      clearTimeout(timeout)
      controller.abort()
    },
  }
}

function loadStoredToken(): string | null {
  if (typeof window === 'undefined') {
    return null
  }
  try {
    const raw = window.localStorage.getItem('dyp_auth_token')
    return raw?.trim() || null
  } catch {
    return null
  }
}

export async function request<T>(path: string, options: RequestInit = {}): Promise<ApiResponse<T>> {
  const { request: req, cancel } = createRequest(path, options)

  let response: Response
  try {
    response = await fetch(req, { credentials: config.credentialsMode })
  } catch (error) {
    cancel()
    const isAbort = error instanceof DOMException && error.name === 'AbortError'
    throw makeApiError(
      isAbort ? 'network' : 'network',
      0,
      isAbort ? 'Request timed out' : 'Network request failed',
    )
  } finally {
    cancel()
  }

  if (!response.ok) {
    throw await normalizeError(response)
  }

  let body: T
  const contentType = response.headers.get('content-type') ?? ''
  if (contentType.includes('application/json')) {
    try {
      body = (await response.clone().json()) as T
    } catch {
      throw makeApiError('unknown', response.status, 'Invalid response body')
    }
  } else {
    body = (await response.clone().text()) as unknown as T
  }

  return { data: body }
}

export function makeApiError(kind: ApiError['kind'], status: number, message: string, fieldErrors?: Record<string, string>): ApiError {
  return { kind, status, message, fieldErrors }
}

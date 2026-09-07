import type { LoginResult } from '../types/api'

const TOKEN_KEY = 'dyp_auth_token'
const USER_KEY = 'dyp_auth_user'

export interface StoredAuth {
  readonly token: string
  readonly user: { id: number; name: string; email: string }
}

export function readStoredAuth(): StoredAuth | null {
  if (typeof window === 'undefined') {
    return null
  }
  try {
    const token = window.localStorage.getItem(TOKEN_KEY)?.trim()
    if (!token) {
      return null
    }
    const userRaw = window.localStorage.getItem(USER_KEY)
    if (!userRaw) {
      return null
    }
    const user = JSON.parse(userRaw) as { id: number; name: string; email: string } | null
    if (!user || typeof user.id !== 'number' || !user.name || !user.email) {
      return null
    }
    return { token, user }
  } catch {
    return null
  }
}

export function writeStoredAuth(result: LoginResult): void {
  if (typeof window === 'undefined') {
    return
  }
  try {
    window.localStorage.setItem(TOKEN_KEY, result.token.trim())
    window.localStorage.setItem(USER_KEY, JSON.stringify({ id: result.id, name: result.name, email: result.email }))
  } catch {
    // Storage unavailable; caller should surface a user-facing error.
  }
}

export function clearStoredAuth(): void {
  if (typeof window === 'undefined') {
    return
  }
  try {
    window.localStorage.removeItem(TOKEN_KEY)
    window.localStorage.removeItem(USER_KEY)
  } catch {
    // Storage unavailable.
  }
}

export function hasStoredAuth(): boolean {
  return readStoredAuth() !== null
}

export function currentAuthSnapshot(): StoredAuth | null {
  const stored = readStoredAuth()
  if (!stored) {
    return null
  }
  if (!stored.token || !stored.user) {
    return null
  }
  return stored
}

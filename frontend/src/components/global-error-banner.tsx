import type { ApiError } from '../types/api'

interface GlobalErrorBannerProps {
  readonly error: ApiError | null
}

export function GlobalErrorBanner({ error }: GlobalErrorBannerProps) {
  if (!error) {
    return null
  }

  const kindClass = mapKindToClass(error.kind)
  const userMessage = getFriendlyMessage(error)

  return (
    <div className={`global-message ${kindClass}`} role="alert">
      <span className="global-message-text">{userMessage}</span>
    </div>
  )
}

function mapKindToClass(kind: ApiError['kind']): string {
  switch (kind) {
    case 'unauthorized':
    case 'forbidden':
    case 'server-error':
    case 'network':
    case 'conflict':
    case 'validation':
    case 'not-found':
    case 'unknown':
      return 'error'
    default:
      return 'error'
  }
}

function getFriendlyMessage(error: ApiError): string {
  const raw = error.message?.trim().toLowerCase() ?? ''

  if (error.kind === 'network') {
    return 'Could not reach the server. Check your connection and try again.'
  }

  if (error.kind === 'server-error') {
    return 'The service is temporarily unavailable. Please try again in a moment.'
  }

  if (error.kind === 'unknown' && !error.message) {
    return 'Something went wrong. Please try again.'
  }

  if (raw.includes('already registered') || raw.includes('email already')) {
    return 'That email is already registered. Try signing in instead.'
  }
  if (raw.includes('invalid email or password')) {
    return 'Email or password is incorrect. Please try again.'
  }
  if (raw.includes('validation failed')) {
    return 'Please check the highlighted fields and try again.'
  }
  if (raw.includes('authentication required')) {
    return 'Sign in required to continue.'
  }
  if (raw.includes('access denied')) {
    return 'You do not have permission to do that.'
  }

  if (error.message) {
    return error.message
  }

  return 'Something went wrong. Please try again.'
}

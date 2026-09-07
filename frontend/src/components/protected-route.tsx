import { Navigate } from 'react-router-dom'
import { useAuth } from '../context/auth-context'

interface ProtectedRouteProps {
  readonly children: React.ReactNode
  readonly fallback?: React.ReactNode
}

export function ProtectedRoute({ children, fallback }: ProtectedRouteProps) {
  const { isAuthenticated, isLoading } = useAuth()

  if (isLoading) {
    return <LoadingFallback />
  }

  if (!isAuthenticated) {
    const destination = fallback ?? <Navigate to="/login" replace />
    return typeof destination === 'boolean' ? null : destination
  }

  return <>{children}</>
}

function LoadingFallback() {
  return (
    <div style={{ textAlign: 'center', padding: '3rem' }}>
      <p>Loading…</p>
    </div>
  )
}

export function RedirectIfAuthenticated({ children }: { children: React.ReactNode }) {
  const { isAuthenticated, isLoading } = useAuth()

  if (isLoading) {
    return <LoadingFallback />
  }

  if (isAuthenticated) {
    return <Navigate to="/" replace />
  }

  return <>{children}</>
}

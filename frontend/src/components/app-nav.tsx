import { Link } from 'react-router-dom'
import { useAuth } from '../context/auth-context'

export function AppNav() {
  const { user, logout, isAuthenticated } = useAuth()

  return (
    <nav className="nav" aria-label="Main">
      <Link to="/" className="brand">
        TRACEN DYP
      </Link>

      <div className="spacer" />

      {isAuthenticated && user ? (
        <>
          <Link to="/app/profile">Profile</Link>
          <span className="user-badge">
            Signed in as <strong>{user.name}</strong>
          </span>
          <button type="button" data-testid="logout-btn" onClick={logout}>
            Sign out
          </button>
        </>
      ) : (
        <>
          <Link to="/login">Sign in</Link>
          <Link to="/register">Create account</Link>
        </>
      )}
    </nav>
  )
}

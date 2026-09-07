import { Link } from 'react-router-dom'
import { useAuth } from '../context/auth-context'
import { AppNav } from '../components/app-nav'

export function HomePage() {
  const { user, isAuthenticated, logout } = useAuth()

  return (
    <div style={{ width: '100%', maxWidth: '420px', margin: '0 auto', padding: '1rem' }}>
      <AppNav />

      <section style={{ padding: '2rem 0' }}>
        <h1 style={{ fontSize: '1.5rem', marginBottom: '0.5rem' }}>TRACEN DYP</h1>
        <p style={{ color: 'var(--text)', marginBottom: '1.5rem' }}>
          Discover Your Potential
        </p>

        {isAuthenticated && user ? (
          <div>
            <p style={{ marginBottom: '0.25rem' }}>
              Welcome back, <strong>{user.name}</strong>.
            </p>
            <p style={{ color: 'var(--text)', fontSize: '0.9rem' }}>
              You are signed in as {user.email}.
            </p>
            <div style={{ marginTop: '1rem', display: 'flex', gap: '0.75rem' }}>
              <Link to="/app/profile" className="submit-btn" style={{ textDecoration: 'none' }}>
                Go to Profile
              </Link>
              <button
                type="button"
                className="submit-btn"
                style={{ background: 'transparent', color: 'var(--text)', border: '1px solid var(--border)' }}
                onClick={logout}
              >
                Sign out
              </button>
            </div>
          </div>
        ) : (
          <p style={{ color: 'var(--text)', fontSize: '0.9rem' }}>
            Please sign in or create an account to continue.
          </p>
        )}
      </section>
    </div>
  )
}

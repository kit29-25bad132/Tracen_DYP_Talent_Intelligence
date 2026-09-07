import { useState, useEffect } from 'react'
import { Link } from 'react-router-dom'
import { useAuthApi } from '../hooks/use-auth-api'
import type { LoginCredentials } from '../types/api'

function LoginPage() {
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')
  const [serverErrorMessage, setServerErrorMessage] = useState<string | null>(null)
  const { login, submitState, apiError, clearError } = useAuthApi()

  const isSubmitting = submitState === 'submitting'

  useEffect(() => {
    if (apiError && submitState === 'error') {
      setServerErrorMessage(apiError.message || 'Something went wrong. Please try again.')
    } else {
      setServerErrorMessage(null)
    }
  }, [apiError, submitState])

  const handleSubmit = async (event: React.FormEvent) => {
    event.preventDefault()
    clearError()
    const credentials: LoginCredentials = { email, password }
    try {
      await login(credentials)
    } catch {
      // error is already stored by the hook
    }
  }

  return (
    <section className="auth-card" aria-labelledby="login-heading">
      <div className="auth-header">
        <h1 id="login-heading">Sign in to DYP</h1>
        <p>Welcome back. Enter your email and password to continue.</p>
      </div>

      <form className="form-stack" onSubmit={handleSubmit} noValidate>
        <div className="field">
          <label htmlFor="login-email">Email</label>
          <input
            id="login-email"
            data-testid="login-email"
            name="email"
            type="email"
            autoComplete="email"
            required
            value={email}
            onChange={(event) => setEmail(event.target.value)}
            placeholder="you@example.com"
            autoFocus
            disabled={isSubmitting}
            aria-label="Email"
          />
        </div>

        <div className="field">
          <label htmlFor="login-password">Password</label>
          <input
            id="login-password"
            data-testid="login-password"
            name="password"
            type="password"
            autoComplete="current-password"
            required
            value={password}
            onChange={(event) => setPassword(event.target.value)}
            disabled={isSubmitting}
            aria-label="Password"
          />
        </div>

        {serverErrorMessage ? (
          <p>
            <span className="field-error" role="alert">
              {serverErrorMessage}
            </span>
          </p>
        ) : null}

        <button type="submit" data-testid="login-submit" className="submit-btn" disabled={isSubmitting || !email || !password || (apiError && submitState === 'error') ? true : false} aria-label="Sign in">
          {isSubmitting ? 'Signing in…' : 'Sign in'}
        </button>
      </form>

      <p className="link-row">
        New to DYP?{' '}
        <Link to="/register">Create an account</Link>
      </p>
    </section>
  )
}

export default LoginPage

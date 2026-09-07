import { useState, useEffect } from 'react'
import { Link } from 'react-router-dom'
import { useAuthApi } from '../hooks/use-auth-api'
import type { RegisterCredentials } from '../types/api'

export function RegisterPage() {
  const [name, setName] = useState('')
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')
  const [passwordConfirm, setPasswordConfirm] = useState('')
  const [serverErrorMessage, setServerErrorMessage] = useState<string | null>(null)
  const { register, submitState, apiError, clearError } = useAuthApi()

  const isSubmitting = submitState === 'submitting'

  useEffect(() => {
    if (apiError && submitState === 'error') {
      setServerErrorMessage(apiError.message || 'Something went wrong. Please try again.')
    } else {
      setServerErrorMessage(null)
    }
  }, [apiError, submitState])

  const formErrors: Record<string, string> = {}
  if (password && password !== passwordConfirm && password.length >= 8) {
    formErrors.passwordConfirm = 'Passwords do not match.'
  }

  const isFormValid =
    !isSubmitting &&
    name.trim() !== '' &&
    email.trim() !== '' &&
    password.length >= 8 &&
    password === passwordConfirm &&
    !(apiError && submitState === 'error')

  const handleSubmit = async (event: React.FormEvent) => {
    event.preventDefault()
    clearError()
    const credentials: RegisterCredentials = { name, email, password }
    try {
      await register(credentials)
    } catch {
      // error is already stored by the hook
    }
  }

  return (
    <section className="auth-card" aria-labelledby="register-heading">
      <div className="auth-header">
        <h1 id="register-heading">Create a DYP account</h1>
        <p>Join TRACEN DYP to start building your talent profile.</p>
      </div>

      <form className="form-stack" onSubmit={handleSubmit} noValidate>
        <div className="field">
          <label htmlFor="register-name">Name</label>
          <input
            id="register-name"
            data-testid="register-name"
            name="name"
            type="text"
            autoComplete="name"
            required
            value={name}
            onChange={(event) => setName(event.target.value)}
            placeholder="Your name"
            disabled={isSubmitting}
            aria-label="Name"
          />
        </div>

        <div className="field">
          <label htmlFor="register-email">Email</label>
          <input
            id="register-email"
            data-testid="register-email"
            name="email"
            type="email"
            autoComplete="email"
            required
            value={email}
            onChange={(event) => setEmail(event.target.value)}
            placeholder="you@example.com"
            disabled={isSubmitting}
            aria-label="Email"
          />
        </div>

        <div className="field">
          <label htmlFor="register-password">Password</label>
          <input
            id="register-password"
            data-testid="register-password"
            name="password"
            type="password"
            autoComplete="new-password"
            required
            minLength={8}
            value={password}
            onChange={(event) => setPassword(event.target.value)}
            disabled={isSubmitting}
            placeholder="At least 8 characters"
            aria-label="Password"
          />
        </div>

        <div className="field">
          <label htmlFor="register-password-confirm">Confirm password</label>
          <input
            id="register-password-confirm"
            data-testid="register-password-confirm"
            name="passwordConfirm"
            type="password"
            autoComplete="new-password"
            required
            value={passwordConfirm}
            onChange={(event) => setPasswordConfirm(event.target.value)}
            disabled={isSubmitting}
            aria-label="Confirm password"
          />
          {formErrors.passwordConfirm ? (
            <span className="field-error" role="alert">
              {formErrors.passwordConfirm}
            </span>
          ) : null}
        </div>

        {serverErrorMessage ? (
          <p>
            <span className="field-error" role="alert">
              {serverErrorMessage}
            </span>
          </p>
        ) : null}

        <button type="submit" data-testid="register-submit" className="submit-btn" disabled={!isFormValid} aria-label="Create account">
          {isSubmitting ? 'Creating account…' : 'Create account'}
        </button>
      </form>

      <p className="link-row">
        Already have an account?{' '}
        <Link to="/login">Sign in</Link>
      </p>
    </section>
  )
}

export default RegisterPage

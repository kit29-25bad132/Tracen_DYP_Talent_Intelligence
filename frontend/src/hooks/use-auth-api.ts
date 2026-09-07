import { useCallback, useState } from 'react'
import { request } from '../services/api-client'
import type { RegisterCredentials, LoginCredentials, LoginResult, ApiError } from '../types/api'
import { useAuth } from '../context/auth-context'

type SubmitState = 'idle' | 'submitting' | 'error' | 'success'

export function useAuthApi() {
  const { login } = useAuth()
  const [submitState, setSubmitState] = useState<SubmitState>('idle')
  const [apiError, setApiError] = useState<ApiError | null>(null)

  const clearError = useCallback(() => {
    setApiError(null)
    setSubmitState('idle')
  }, [])

  const register = useCallback(
    async (credentials: RegisterCredentials) => {
      setSubmitState('submitting')
      setApiError(null)
      try {
        const response = await request<{ id: number; name: string; email: string }>('/users', {
          method: 'POST',
          body: JSON.stringify(credentials),
        })
        setSubmitState('success')
        return response.data
      } catch (error) {
        const apiError = error as ApiError
        setApiError(apiError)
        setSubmitState('error')
        throw apiError
      }
    },
    [],
  )

  const loginUser = useCallback(
    async (credentials: LoginCredentials) => {
      setSubmitState('submitting')
      setApiError(null)
      try {
        const response = await request<LoginResult>('/users/login', {
          method: 'POST',
          body: JSON.stringify(credentials),
        })
        const result = response.data
        login(result)
        setSubmitState('success')
        return result
      } catch (error) {
        const apiError = error as ApiError
        setApiError(apiError)
        setSubmitState('error')
        throw apiError
      }
    },
    [login],
  )

  return { register, login: loginUser, submitState, apiError, clearError }
}

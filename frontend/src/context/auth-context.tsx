import { createContext, useContext, useState, useCallback, type ReactNode } from 'react'
import type { LoginResult } from '../types/api'
import { writeStoredAuth, clearStoredAuth, currentAuthSnapshot } from '../services/auth'

interface AuthSnapshot {
  readonly user: { id: number; name: string; email: string } | null
  readonly token: string | null
}

function computeSnapshot(): AuthSnapshot {
  const stored = currentAuthSnapshot()
  if (stored) {
    return { user: stored.user, token: stored.token }
  }
  return { user: null, token: null }
}

interface AuthContextValue {
  readonly user: { id: number; name: string; email: string } | null
  readonly token: string | null
  readonly isLoading: boolean
  readonly isAuthenticated: boolean
  readonly login: (result: LoginResult) => void
  readonly logout: () => void
  readonly refreshFromStorage: () => void
}

const AuthContext = createContext<AuthContextValue | null>(null)

export function AuthProvider({ children }: { children: ReactNode }) {
  const [snapshot, setSnapshot] = useState<AuthSnapshot>(computeSnapshot)
  const [isHydrated, setIsHydrated] = useState(false)

  const login = useCallback(
    (result: LoginResult) => {
      writeStoredAuth(result)
      setSnapshot({ user: { id: result.id, name: result.name, email: result.email }, token: result.token })
      setIsHydrated(true)
    },
    [],
  )

  const logout = useCallback(() => {
    clearStoredAuth()
    setSnapshot({ user: null, token: null })
    setIsHydrated(true)
  }, [])

  const refreshFromStorage = useCallback(() => {
    setSnapshot(computeSnapshot())
    setIsHydrated(true)
  }, [])

  const value: AuthContextValue = {
    user: snapshot.user,
    token: snapshot.token,
    isLoading: !isHydrated,
    isAuthenticated: !isHydrated ? false : !!snapshot.token,
    login,
    logout,
    refreshFromStorage,
  }

  return (
    <AuthContext.Provider value={value}>
      {children}
    </AuthContext.Provider>
  )
}

export function useAuth(): AuthContextValue {
  const context = useContext(AuthContext)
  if (context === null) {
    throw new Error('useAuth must be used within an AuthProvider')
  }
  return context
}

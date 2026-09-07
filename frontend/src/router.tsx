import { Suspense, lazy } from 'react'
import { Routes, Route, Navigate, Outlet } from 'react-router-dom'
import { ProtectedRoute, RedirectIfAuthenticated } from './components/protected-route'
import { AppNav } from './components/app-nav'
import { HomePage } from './pages/home-page'

const ProfilePage = lazy(() => import('./pages/profile-page'))

export function AppRoutes() {
  const LoginPage = lazy(() => import('./pages/login-page'))
  const RegisterPage = lazy(() => import('./pages/register-page'))

  return (
    <Suspense fallback={<LoadingScreen />}>
      <Routes>
        <Route path="/" element={<HomePage />} />

        <Route
          path="/login"
          element={
            <RedirectIfAuthenticated>
              <LoginPage />
            </RedirectIfAuthenticated>
          }
        />

        <Route
          path="/register"
          element={
            <RedirectIfAuthenticated>
              <RegisterPage />
            </RedirectIfAuthenticated>
          }
        />

        <Route
          path="/app"
          element={
            <ProtectedRoute fallback={<Navigate to="/login" replace />}>
              <AppShell />
            </ProtectedRoute>
          }
        >
          <Route index element={<Navigate to="profile" replace />} />
          <Route
            path="profile"
            element={
              <Suspense fallback={<LoadingScreen />}>
                <ProfilePage />
              </Suspense>
            }
          />
        </Route>

        <Route path="*" element={<Navigate to="/" replace />} />
      </Routes>
    </Suspense>
  )
}

function LoadingScreen() {
  return (
    <div style={{ textAlign: 'center', padding: '3rem' }}>
      <p>Loading…</p>
    </div>
  )
}

function AppShell() {
  return (
    <div style={{ padding: '1rem' }}>
      <AppNav />
      <main style={{ padding: '2rem 0', minHeight: '200px' }}>
        <Outlet />
      </main>
    </div>
  )
}

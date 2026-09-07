import { test, expect } from '@playwright/test'

const BACKEND = 'http://localhost:8080/api'

test.describe('DYP authentication foundation', () => {
  test('register then login then logout flow works end to end', async ({ page }) => {
    await page.goto('http://localhost:5173/register')
    await page.waitForLoadState('domcontentloaded')
    await page.waitForTimeout(5000)

    await page.fill('input[data-testid="register-name"]', 'E2E User')
    await page.fill('input[data-testid="register-email"]', `e2e+${Date.now()}@example.com`)
    await page.fill('input[data-testid="register-password"]', 'password123')
    await page.fill('input[data-testid="register-password-confirm"]', 'password123')
    await page.click('button[data-testid="register-submit"]')

    await expect(page.getByRole('heading', { name: /sign in to dyp/i })).toBeVisible({ timeout: 15000 })

    await page.goto('http://localhost:5173/login')
    await page.waitForTimeout(5000)

    await page.fill('input[data-testid="login-email"]', `e2e+${Date.now()}@example.com`)
    await page.fill('input[data-testid="login-password"]', 'password123')
    await page.click('button[data-testid="login-submit"]')

    await expect(page.getByRole('button', { name: /sign out/i })).toBeVisible({ timeout: 15000 })

    const token = await page.evaluate(() => localStorage.getItem('dyp_auth_token'))
    expect(token).toBeTruthy()

    await page.goto('http://localhost:5173/app')
    await expect(page.getByText(/app area/i)).toBeVisible({ timeout: 15000 })

    await page.click('button[data-testid="logout-btn"]')
    await page.waitForTimeout(3000)
    await expect(page.getByRole('heading', { name: /sign in to dyp/i })).toBeVisible({ timeout: 15000 })
  })

  test('backend auth API contracts are compatible with frontend', async () => {
    const registerPayload = {
      name: 'API Contract User',
      email: `contract+${Date.now()}@example.com`,
      password: 'password123',
    }

    const registerRes = await fetch(`${BACKEND}/users`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(registerPayload),
    })

    expect(registerRes.status).toBe(200)
    const user = (await registerRes.json()) as { id: number; name: string; email: string }
    expect(user.id).toBeGreaterThan(0)

    const loginRes = await fetch(`${BACKEND}/users/login`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ email: registerPayload.email, password: registerPayload.password }),
    })

    expect(loginRes.status).toBe(200)
    const loginBody = (await loginRes.json()) as { token: string; id: number; name: string; email: string }
    expect(loginBody.token).toBeTruthy()
    expect(loginBody.id).toBe(user.id)

    const profileRes = await fetch(`${BACKEND}/users/${user.id}/profile`, {
      headers: { Authorization: `Bearer ${loginBody.token}` },
    })
    expect(profileRes.status).toBe(404)

    const unauthRes = await fetch(`${BACKEND}/users/${user.id}/profile`)
    expect(unauthRes.status).toBe(401)

    await fetch(`${BACKEND}/users/${user.id}/profile`, {
      method: 'DELETE',
      headers: { Authorization: `Bearer ${loginBody.token}` },
    }).catch(() => {})
  })
})

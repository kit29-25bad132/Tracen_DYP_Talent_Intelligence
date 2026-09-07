# Instructions

- Following Playwright test failed.
- Explain why, be concise, respect Playwright best practices.
- Provide a snippet of code with the fix, if possible.

# Test info

- Name: auth.spec.ts >> DYP authentication foundation >> register then login then logout flow works end to end
- Location: e2e\auth.spec.ts:6:3

# Error details

```
Test timeout of 30000ms exceeded.
```

```
Error: page.fill: Test timeout of 30000ms exceeded.
Call log:
  - waiting for locator('input[data-testid="register-name"]')

```

# Page snapshot

```yaml
- paragraph [ref=e4]: Loading…
```

# Test source

```ts
  1  | import { test, expect } from '@playwright/test'
  2  | 
  3  | const BACKEND = 'http://localhost:8080/api'
  4  | 
  5  | test.describe('DYP authentication foundation', () => {
  6  |   test('register then login then logout flow works end to end', async ({ page }) => {
  7  |     await page.goto('http://localhost:5173/register')
  8  |     await page.waitForLoadState('domcontentloaded')
  9  |     await page.waitForTimeout(5000)
  10 | 
> 11 |     await page.fill('input[data-testid="register-name"]', 'E2E User')
     |                ^ Error: page.fill: Test timeout of 30000ms exceeded.
  12 |     await page.fill('input[data-testid="register-email"]', `e2e+${Date.now()}@example.com`)
  13 |     await page.fill('input[data-testid="register-password"]', 'password123')
  14 |     await page.fill('input[data-testid="register-password-confirm"]', 'password123')
  15 |     await page.click('button[data-testid="register-submit"]')
  16 | 
  17 |     await expect(page.getByRole('heading', { name: /sign in to dyp/i })).toBeVisible({ timeout: 15000 })
  18 | 
  19 |     await page.goto('http://localhost:5173/login')
  20 |     await page.waitForTimeout(5000)
  21 | 
  22 |     await page.fill('input[data-testid="login-email"]', `e2e+${Date.now()}@example.com`)
  23 |     await page.fill('input[data-testid="login-password"]', 'password123')
  24 |     await page.click('button[data-testid="login-submit"]')
  25 | 
  26 |     await expect(page.getByRole('button', { name: /sign out/i })).toBeVisible({ timeout: 15000 })
  27 | 
  28 |     const token = await page.evaluate(() => localStorage.getItem('dyp_auth_token'))
  29 |     expect(token).toBeTruthy()
  30 | 
  31 |     await page.goto('http://localhost:5173/app')
  32 |     await expect(page.getByText(/app area/i)).toBeVisible({ timeout: 15000 })
  33 | 
  34 |     await page.click('button[data-testid="logout-btn"]')
  35 |     await page.waitForTimeout(3000)
  36 |     await expect(page.getByRole('heading', { name: /sign in to dyp/i })).toBeVisible({ timeout: 15000 })
  37 |   })
  38 | 
  39 |   test('backend auth API contracts are compatible with frontend', async () => {
  40 |     const registerPayload = {
  41 |       name: 'API Contract User',
  42 |       email: `contract+${Date.now()}@example.com`,
  43 |       password: 'password123',
  44 |     }
  45 | 
  46 |     const registerRes = await fetch(`${BACKEND}/users`, {
  47 |       method: 'POST',
  48 |       headers: { 'Content-Type': 'application/json' },
  49 |       body: JSON.stringify(registerPayload),
  50 |     })
  51 | 
  52 |     expect(registerRes.status).toBe(200)
  53 |     const user = (await registerRes.json()) as { id: number; name: string; email: string }
  54 |     expect(user.id).toBeGreaterThan(0)
  55 | 
  56 |     const loginRes = await fetch(`${BACKEND}/users/login`, {
  57 |       method: 'POST',
  58 |       headers: { 'Content-Type': 'application/json' },
  59 |       body: JSON.stringify({ email: registerPayload.email, password: registerPayload.password }),
  60 |     })
  61 | 
  62 |     expect(loginRes.status).toBe(200)
  63 |     const loginBody = (await loginRes.json()) as { token: string; id: number; name: string; email: string }
  64 |     expect(loginBody.token).toBeTruthy()
  65 |     expect(loginBody.id).toBe(user.id)
  66 | 
  67 |     const profileRes = await fetch(`${BACKEND}/users/${user.id}/profile`, {
  68 |       headers: { Authorization: `Bearer ${loginBody.token}` },
  69 |     })
  70 |     expect(profileRes.status).toBe(404)
  71 | 
  72 |     const unauthRes = await fetch(`${BACKEND}/users/${user.id}/profile`)
  73 |     expect(unauthRes.status).toBe(401)
  74 | 
  75 |     await fetch(`${BACKEND}/users/${user.id}/profile`, {
  76 |       method: 'DELETE',
  77 |       headers: { Authorization: `Bearer ${loginBody.token}` },
  78 |     }).catch(() => {})
  79 |   })
  80 | })
  81 | 
```
import react from '@vitejs/plugin-react'
import { defineConfig, loadEnv } from 'vite'

// https://vite.dev/config/
export default defineConfig(({ mode }) => {
  const env = loadEnv(mode, process.cwd(), '')

  return {
    plugins: [react()],
    server: {
      port: 5173,
      host: true,
    },
    define: {
      __APP_ENV__: JSON.stringify({
        VITE_API_BASE_URL: env.VITE_API_BASE_URL || 'http://localhost:8080/api',
      }),
    },
  }
})

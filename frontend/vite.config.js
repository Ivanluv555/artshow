import { defineConfig } from 'vite'
import path from 'path'

export default defineConfig({
  root: path.resolve(__dirname),
  base: '/',
  server: {
    port: 5173,
    proxy: {
      '/api': 'http://100.69.83.64:9090'
    }
  },
  build: {
    outDir: path.resolve(__dirname, '../src/main/resources/static'),
    emptyOutDir: false,
    rollupOptions: {
      input: {
        main: path.resolve(__dirname, 'index.html'),
        login: path.resolve(__dirname, 'login.html'),
        dashboard: path.resolve(__dirname, 'dashboard.html'),
        product: path.resolve(__dirname, 'product.html'),
        user: path.resolve(__dirname, 'user.html')
      }
    }
  }
})

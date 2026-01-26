# ArtShow Frontend (Multi-page Vite)

说明：这是一个最小的多页面前端骨架（Vite + 原生 JS），用于与 Spring Boot 后端配合。构建输出直接放到 Spring Boot 静态目录 `src/main/resources/static`，因此后端可以直接提供生产文件。

开发：

```bash
cd frontend
npm install
npm run dev
```

- 开发时，Vite 会在 `http://localhost:5173` 提供多页面预览，开发代理会把 `/api` 请求代理到 `http://localhost:8080`（可在 `vite.config.js` 修改）。

构建（生产）并把文件放入后端静态目录：

```bash
cd frontend
npm run build
# 然后运行后端（例如 ./mvnw spring-boot:run）
```

页面：
- `/index.html` - 首页
- `/login.html` - 登录
- `/dashboard.html` - 仪表盘
- `/product.html` - 商品列表示例
- `/user.html` - 用户信息示例

注意：示例 JS 使用 `/api/*` 路径调用后端 REST 接口，请根据后端实际路由调整。

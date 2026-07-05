# Artshow - 艺术展览与在线课程管理系统

一个集艺术展览、在线课程和社区互动于一体的综合平台。

## 项目结构

```none
artshow/
├── frontend/              # Vue 3 前端应用
├── artshowmobile/         # Flutter 移动端应用
├── src/                   # Spring Boot 后端
│   └── main/
│       ├── java/
│       └── resources/
├── database/              # 数据库结构和迁移脚本
├── docs/                  # 项目文档
└── pom.xml               # Maven 配置文件
```

## 技术栈

### 后端

- **Spring Boot 3.3.7** - Java 应用框架
- **Spring Data JPA** - 数据访问层
- **MySQL** - 数据库
- **JWT** - 身份认证
- **SpringDoc OpenAPI** - API 文档

### 前端

- **Vue 3** - 渐进式 JavaScript 框架
- **Vite** - 构建工具
- **Element Plus** - UI 组件库
- **Axios** - HTTP 客户端
- **Vue Router** - 路由管理

### 移动端

- **Flutter** - 跨平台移动应用框架
- **Dart** - 编程语言

## 快速开始

### 前置要求

- JDK 17+
- Maven 3.6+
- MySQL 8.0+
- Node.js 16+
- Flutter SDK（移动端开发）

### 后端设置

- 在 `src/main/resources/application.properties` 中配置数据库：

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/artshow
spring.datasource.username=root
spring.datasource.password=your_password
```

- 运行数据库脚本：

```bash
mysql -u root -p < database/ddl.sql
```

- 启动后端服务：

```bash
mvn spring-boot:run
```

后端运行地址：<http://localhost:8888>

### 前端设置

- 安装依赖：

```bash
cd frontend
npm install
```

- 启动开发服务器：

```bash
npm run dev
```

前端运行地址：<http://localhost:3000>

### 移动端设置

详细说明请参考 [移动端 README](artshowmobile/README.md)

## API 文档

访问 SpringDoc API 文档：

- Swagger UI：<http://localhost:8888/swagger-ui.html>
- OpenAPI JSON：<http://localhost:8888/v3/api-docs>

### 身份认证

- 通过 `POST /user/login` 登录：

```json
{
  "userName": "admin",
  "password": "123456"
}
```

- 在请求头中使用返回的 token：

```none
Authorization: Bearer <your-token>
```

- 在 Swagger UI 中：点击 "Authorize" 按钮，粘贴 token（不需要 "Bearer" 前缀）

## 功能特性

### 用户管理

- 用户注册与登录
- 个人资料管理
- 基于 JWT 的身份认证

### 艺术分类

- 浏览艺术分类和子分类
- 详细的艺术形式信息

### 在线课程

- 带讲师信息的课程目录
- 课程报名和进度跟踪
- 基于章节的学习系统

### 产品与购物

- 产品浏览和搜索
- 购物车管理
- 订单处理

### 社区功能

- 创建和分享帖子
- 点赞和评论系统
- 用户收藏功能

### 徽章与成就

- 用户徽章系统
- 成就跟踪

## 数据库结构

包含 20 张表，涵盖：

- 用户管理（user, user_address, user_badge）
- 艺术内容（art_category, art_subcategory）
- 课程系统（course, course_instructor, course_outline）
- 电商功能（product, order, order_item, shopping_cart_item）
- 社区互动（post, post_comment, post_like, post_collection）
- 徽章系统（badge, user_badge）
- 课程注册（user_course_enrollment, user_course_chapter_completed）

详细的字段映射请参考 [数据库实体对齐文档](database/DATABASE_ENTITY_ALIGNMENT.md)

## 项目文档

- [SpringDoc 使用指南](docs/SPRINGDOC_GUIDE.md) - API 文档使用说明
- [项目总结](docs/PROJECT_SUMMARY.md) - 详细的项目概览
- [数据库对齐报告](database/DATABASE_ENTITY_ALIGNMENT.md) - 实体表映射关系
- [业务调整指南](业务第一次调整指南.md) - 业务逻辑分析与优化建议

## 开发说明

### 代码结构

**后端（Java）：**

```none
src/main/java/org/ivan/artshow/
├── common/              # 公共工具和配置
│   ├── auth/           # 身份认证拦截器
│   ├── config/         # Spring 配置
│   ├── core/           # 核心工具类
│   ├── exception/      # 异常处理
│   └── utils/          # 工具类
└── module/             # 业务模块
    ├── user/           # 用户管理
    ├── product/        # 产品管理
    ├── course/         # 课程管理
    ├── post/           # 社区帖子
    └── ...
```

**前端（Vue）：**

```none
frontend/src/
├── api/                # API 接口集成
├── views/              # 页面组件
├── router/             # 路由配置
├── components/         # 可复用组件
└── utils/              # 工具函数
```

### 生产环境构建

**后端：**

```bash
mvn clean package
java -jar target/artshow-0.0.1-SNAPSHOT.jar
```

**前端：**

```bash
cd frontend
npm run build
# 构建文件位于 frontend/dist/
```

## 开源协议

MIT

## 作者

Ivan Horn

## 贡献

1. Fork 本仓库
2. 创建你的特性分支
3. 提交你的改动
4. 推送到分支
5. 创建 Pull Request

## 联系方式

如有问题或建议，请通过以下方式联系：

- 提交 Issue
- 发送邮件至：<ivanluv555@outlook.com>

# ArtShow Mobile - Setup Guide

## 快速开始指南

### 1. 后端配置

#### 确认后端正在运行
```bash
# 在 artshow 根目录
mvn spring-boot:run
```

后端应该运行在: `http://localhost:8888`

#### 测试后端连接
```bash
curl http://localhost:8888/artcate/list
```

### 2. 移动端配置

#### 根据你的运行环境修改 API 地址

编辑 `lib/utils/constants.dart`：

**情况1：Android 模拟器**
```dart
static const String baseUrl = 'http://10.0.2.2:8888';
```

**情况2：iOS 模拟器**
```dart
static const String baseUrl = 'http://localhost:8888';
```

**情况3：真实设备（手机/平板）**
```dart
static const String baseUrl = 'http://192.168.x.x:8888';  // 使用你电脑的局域网 IP
```

### 3. 运行应用

```bash
cd artshowmobile

# 安装依赖（已完成）
flutter pub get

# 运行应用
flutter run
```

### 4. 登录测试

使用后端数据库中的用户账号登录。如果还没有用户，可以通过以下方式创建：

#### 方法1：使用 Swagger UI
1. 访问 `http://localhost:8888/swagger-ui.html`
2. 找到 `POST /user` 接口
3. 创建测试用户

#### 方法2：直接使用 curl
```bash
curl -X POST http://localhost:8888/user \
  -H "Content-Type: application/json" \
  -d '{
    "userName": "testuser",
    "password": "123456",
    "email": "test@artshow.com"
  }'
```

## 功能测试清单

### ✅ 已实现的功能

1. **登录页面**
   - [x] 用户名/密码登录
   - [x] JWT Token 存储
   - [x] 表单验证

2. **主页（Home）**
   - [x] 轮播图横幅
   - [x] 艺术品分类筛选
   - [x] 艺术品网格展示
   - [x] 下拉刷新
   - [x] 侧边栏导航

3. **课程页面（Courses）**
   - [x] 课程列表展示
   - [x] 课程卡片（封面、标题、价格、学生数）
   - [x] 课程详情页
   - [x] 下拉刷新

4. **讲师页面（Instructors）**
   - [x] 讲师网格展示
   - [x] 讲师卡片（头像、姓名、职称）
   - [x] 讲师详情页
   - [x] 个人简介展示

5. **艺术品展示**
   - [x] 产品卡片
   - [x] 价格显示
   - [x] 库存状态
   - [x] 认证徽章

### 🚧 待实现功能

- [ ] 用户注册
- [ ] 搜索功能
- [ ] 购物车
- [ ] 课程报名
- [ ] 用户个人中心
- [ ] 收藏功能
- [ ] 评论和点赞
- [ ] 支付集成

## 项目结构

```
artshowmobile/
├── lib/
│   ├── main.dart                   # 应用入口
│   ├── models/                     # 数据模型
│   │   ├── api_result.dart         # API 响应封装
│   │   ├── art_category.dart       # 艺术分类
│   │   ├── course.dart             # 课程
│   │   ├── instructor.dart         # 讲师
│   │   └── product.dart            # 产品
│   ├── screens/                    # 页面
│   │   ├── login_screen.dart       # 登录页
│   │   ├── home_screen.dart        # 主页
│   │   ├── courses_screen.dart     # 课程列表
│   │   ├── course_detail_screen.dart
│   │   ├── instructors_screen.dart # 讲师列表
│   │   └── instructor_detail_screen.dart
│   ├── services/                   # 服务层
│   │   ├── api_service.dart        # API 调用
│   │   └── auth_service.dart       # 认证管理
│   ├── widgets/                    # 可复用组件
│   │   ├── category_chip.dart      # 分类标签
│   │   ├── course_card.dart        # 课程卡片
│   │   ├── instructor_card.dart    # 讲师卡片
│   │   └── product_card.dart       # 产品卡片
│   └── utils/
│       └── constants.dart          # 常量配置
├── pubspec.yaml                    # 依赖配置
└── README.md                       # 项目文档
```

## API 接口对应

| 功能 | 后端接口 | 前端调用 |
|------|---------|---------|
| 用户登录 | `POST /user/login` | `ApiService.login()` |
| 获取产品列表 | `GET /product/list` | `ApiService.getProducts()` |
| 获取分类列表 | `GET /artcate/list` | `ApiService.getCategories()` |
| 获取课程列表 | `GET /course/list` | `ApiService.getCourses()` |
| 获取课程详情 | `GET /course?courseId=X` | `ApiService.getCourse(id)` |
| 获取讲师列表 | `GET /instructor/list` | `ApiService.getInstructors()` |
| 获取讲师详情 | `GET /instructor?instructorId=X` | `ApiService.getInstructor(id)` |

## 常见问题

### 1. 无法连接到后端

**症状**: 登录时显示 "Connection refused" 或超时

**解决方案**:
1. 确认后端正在运行：`curl http://localhost:8888/artcate/list`
2. 检查防火墙设置
3. 根据运行环境修改 `baseUrl`（见上文"移动端配置"）

### 2. 登录失败 401 错误

**症状**: 显示 "Unauthorized" 或 "Invalid username or password"

**解决方案**:
1. 确认数据库中存在该用户
2. 检查用户名和密码是否正确
3. 查看后端日志确认请求是否到达

### 3. 图片无法加载

**症状**: 产品/课程/讲师图片显示为灰色占位符

**原因**: 数据库中的图片 URL 可能无效或为空

**解决方案**:
1. 使用有效的图片 URL（建议使用绝对路径）
2. 或者使用占位图片服务，如 `https://via.placeholder.com/300`

### 4. Flutter 依赖问题

```bash
# 清理并重新获取依赖
flutter clean
flutter pub get

# 如果还有问题，删除 pubspec.lock
rm pubspec.lock
flutter pub get
```

### 5. Android 构建错误

确保 `android/app/build.gradle` 中的 `minSdkVersion` 至少为 21：

```gradle
defaultConfig {
    minSdkVersion 21
    // ...
}
```

## 测试流程

### 1. 启动后端
```bash
cd artshow
mvn spring-boot:run
```

### 2. 验证后端
```bash
# 测试分类接口
curl http://localhost:8888/artcate/list

# 应该返回类似：
# {"code":200,"msg":"Success","data":[...]}
```

### 3. 启动移动应用
```bash
cd artshowmobile
flutter run
```

### 4. 功能测试
1. 打开应用看到启动页
2. 进入登录页
3. 输入用户名和密码登录
4. 成功后进入主页，看到：
   - 轮播图
   - 分类标签
   - 产品网格
5. 点击侧边栏菜单测试：
   - 课程页面
   - 讲师页面
6. 测试详情页导航

## 性能优化建议

1. **图片缓存**: 已使用 `cached_network_image` 实现
2. **下拉刷新**: 已在主要列表页面实现
3. **错误处理**: 已实现基础错误提示

## 下一步开发

### 优先级1：核心功能
- [ ] 实现搜索功能
- [ ] 实现购物车
- [ ] 实现课程报名

### 优先级2：用户体验
- [ ] 添加加载骨架屏
- [ ] 优化图片加载体验
- [ ] 添加错误重试机制

### 优先级3：扩展功能
- [ ] 用户个人中心
- [ ] 订单管理
- [ ] 评论系统
- [ ] 社交分享

## 技术栈

- **Flutter**: 3.12.2+
- **状态管理**: Provider
- **HTTP 客户端**: http
- **本地存储**: shared_preferences
- **图片缓存**: cached_network_image
- **UI 组件**: carousel_slider

## 相关文档

- [主项目 README](../README.md)
- [后端 API 文档](http://localhost:8888/swagger-ui.html)
- [Flutter 官方文档](https://docs.flutter.dev/)

---

**开发者**: Ivan Horn  
**更新时间**: 2026-07-04

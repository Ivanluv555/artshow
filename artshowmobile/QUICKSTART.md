# 🚀 ArtShow Mobile - 快速启动指南

## ✅ 项目状态

- **Flutter 依赖**: ✅ 已安装
- **代码分析**: ✅ 无错误
- **文件数量**: 19 个 Dart 文件
- **状态**: ✅ 可以运行

## 📋 5 分钟启动步骤

### 步骤 1: 启动后端服务器

```bash
cd w:/artshow
mvn spring-boot:run
```

等待看到: `Started ArtshowApplication in X.XXX seconds`

### 步骤 2: 验证后端运行

打开浏览器访问:
- Swagger UI: http://localhost:8888/swagger-ui.html
- 或运行: `curl http://localhost:8888/artcate/list`

### 步骤 3: 配置移动端 API 地址

编辑 `lib/utils/constants.dart`，根据你的运行环境选择：

```dart
// 选项 A: Android 模拟器
static const String baseUrl = 'http://10.0.2.2:8888';

// 选项 B: iOS 模拟器  
static const String baseUrl = 'http://localhost:8888';

// 选项 C: 真实设备（查看你的电脑 IP）
static const String baseUrl = 'http://192.168.x.x:8888';
```

**如何查看你的 IP:**
```bash
# Windows
ipconfig

# Mac/Linux
ifconfig
```

### 步骤 4: 运行移动应用

```bash
cd w:/artshow/artshowmobile
flutter run
```

选择运行设备:
- `[1]` - Android 模拟器
- `[2]` - iOS 模拟器
- `[3]` - Chrome 浏览器（开发测试）

### 步骤 5: 登录测试

如果还没有用户，先创建一个：

**方法 1: 使用 Swagger UI**
1. 打开 http://localhost:8888/swagger-ui.html
2. 找到 `POST /user` 接口
3. 点击 "Try it out"
4. 输入:
```json
{
  "userName": "demo",
  "password": "123456",
  "email": "demo@artshow.com"
}
```
5. 点击 "Execute"

**方法 2: 使用命令行**
```bash
curl -X POST http://localhost:8888/user \
  -H "Content-Type: application/json" \
  -d '{"userName":"demo","password":"123456","email":"demo@artshow.com"}'
```

然后在移动应用中使用这个账号登录。

## 📱 应用导航

登录成功后，你会看到:

### 主页
- 轮播图横幅
- 艺术品分类标签（可点击筛选）
- 产品网格展示

### 侧边栏菜单
- 🏠 Home - 回到主页
- 📚 Courses - 浏览课程
- 👨‍🏫 Instructors - 查看讲师
- 🚪 Logout - 退出登录

## 🎯 测试功能

### 测试清单
- [ ] 启动页显示
- [ ] 登录成功
- [ ] 主页加载产品
- [ ] 分类筛选工作
- [ ] 下拉刷新
- [ ] 课程列表显示
- [ ] 课程详情页
- [ ] 讲师列表显示
- [ ] 讲师详情页
- [ ] 侧边栏导航

## ⚠️ 常见问题

### 问题 1: 连接被拒绝 (Connection refused)

**检查清单:**
1. ✅ 后端是否运行？ → `curl http://localhost:8888/artcate/list`
2. ✅ 使用正确的 IP？ → 查看"步骤 3"
3. ✅ 防火墙是否阻止？ → 临时关闭测试

### 问题 2: 登录失败 401

**原因:** 用户不存在或密码错误

**解决:** 
1. 检查数据库中是否有该用户
2. 或者重新创建用户（见"步骤 5"）

### 问题 3: 图片不显示

**原因:** 数据库中的图片 URL 无效

**解决:** 这是正常的，数据库可能还没有产品数据。你可以：
1. 添加产品数据到数据库
2. 或者暂时看到灰色占位图

### 问题 4: Flutter 命令找不到

**安装 Flutter:**
1. 访问 https://docs.flutter.dev/get-started/install
2. 下载并安装 Flutter SDK
3. 运行 `flutter doctor` 检查环境

## 📊 项目结构概览

```
artshowmobile/
├── lib/
│   ├── main.dart                    # 入口 + 启动页
│   ├── models/                      # 数据模型 (5 个)
│   ├── screens/                     # 页面 (6 个)
│   ├── services/                    # API 服务 (2 个)
│   ├── widgets/                     # 可复用组件 (4 个)
│   └── utils/                       # 工具类 (1 个)
├── pubspec.yaml                     # 依赖配置
├── README.md                        # 完整文档
├── SETUP.md                         # 详细设置指南
└── DEVELOPMENT_SUMMARY.md           # 开发总结
```

## 🔧 开发命令

```bash
# 查看可用设备
flutter devices

# 运行应用
flutter run

# 热重载（应用运行时按 'r'）
# 热重启（应用运行时按 'R'）

# 代码分析
flutter analyze

# 清理并重新构建
flutter clean
flutter pub get
flutter run

# 构建 APK (Android)
flutter build apk --release

# 构建 iOS
flutter build ios --release
```

## 📞 获取帮助

**文档:**
- `README.md` - 完整项目文档
- `SETUP.md` - 详细设置指南
- `DEVELOPMENT_SUMMARY.md` - 开发总结

**在线资源:**
- Flutter 文档: https://docs.flutter.dev/
- 后端 API: http://localhost:8888/swagger-ui.html

## ✨ 功能亮点

1. **完整认证系统** - JWT Token 自动管理
2. **美观 UI** - Material Design 3 + 渐变主题
3. **流畅体验** - 图片缓存 + 下拉刷新
4. **错误处理** - 友好的错误提示
5. **代码质量** - ✅ 通过 Flutter 分析

## 🎉 就这样！

现在你应该可以：
1. ✅ 看到美观的启动页
2. ✅ 登录到应用
3. ✅ 浏览艺术品、课程、讲师
4. ✅ 享受流畅的用户体验

有任何问题？查看 `SETUP.md` 获取更详细的帮助。

---

**开发完成**: 2026-07-04  
**状态**: ✅ 可以使用

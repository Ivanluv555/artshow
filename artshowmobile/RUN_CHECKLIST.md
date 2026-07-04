# 🚀 ArtShow Mobile - 运行清单

## ✅ 运行前检查

### 1. 环境检查
```bash
# 检查 Flutter 是否安装
flutter --version
# 应该显示 Flutter 3.x.x

# 检查可用设备
flutter devices
# 至少应该有一个设备（Chrome/Android/iOS）

# 检查项目健康
flutter doctor
# 确保没有严重错误
```

### 2. 后端检查
```bash
# 进入后端目录
cd w:/artshow

# 启动后端
mvn spring-boot:run

# 等待看到：
# Started ArtshowApplication in X.XXX seconds
```

### 3. 验证后端
```bash
# 测试后端 API
curl http://localhost:8888/artcate/list

# 应该返回：
# {"code":200,"msg":"Success","data":[...]}
```

### 4. 创建测试用户（如果还没有）
访问 http://localhost:8888/swagger-ui.html

使用 `POST /user` 创建：
```json
{
  "userName": "demo",
  "password": "123456",
  "email": "demo@artshow.com"
}
```

---

## 🎯 运行应用

### 步骤 1: 进入项目目录
```bash
cd w:/artshow/artshowmobile
```

### 步骤 2: 配置 API 地址

编辑 `lib/utils/constants.dart`，根据运行环境选择：

**Chrome 浏览器（推荐用于测试）:**
```dart
static const String baseUrl = 'http://localhost:8888';
```

**Android 模拟器:**
```dart
static const String baseUrl = 'http://10.0.2.2:8888';
```

**真实设备:**
```dart
static const String baseUrl = 'http://你的电脑IP:8888';
```

### 步骤 3: 运行应用
```bash
flutter run
```

### 步骤 4: 选择设备
如果有多个设备，会提示选择：
- `[1]` - Chrome 浏览器 ⭐ 推荐新手
- `[2]` - Android 模拟器
- `[3]` - iOS 模拟器（仅 Mac）
- `[4]` - 真实设备

输入数字并按回车。

---

## 📱 应用使用流程

### 1. 启动页
应用启动时会显示：
- ArtShow Logo
- 紫色到蓝色的渐变背景
- 加载指示器

### 2. 登录页
输入用户名和密码：
- 用户名: `demo`
- 密码: `123456`

点击 "Login" 按钮。

### 3. 主页
登录成功后进入主页，你会看到：
- **轮播图横幅**（自动播放）
- **分类标签**（可点击筛选）
- **产品网格**（艺术品展示）

### 4. 侧边栏
点击左上角菜单图标（≡）打开侧边栏：
- 🏠 **Home** - 返回主页
- 📚 **Courses** - 浏览课程
- 👨‍🏫 **Instructors** - 查看讲师
- 🚪 **Logout** - 退出登录

### 5. 课程页面
- 浏览所有课程
- 点击课程卡片查看详情
- 下拉刷新列表

### 6. 讲师页面
- 网格展示所有讲师
- 点击讲师卡片查看详情
- 查看讲师简介

---

## ✅ 功能测试清单

测试以下功能确保一切正常：

- [ ] 启动页正常显示
- [ ] 登录成功
- [ ] 主页加载产品数据
- [ ] 轮播图自动播放
- [ ] 分类标签可点击
- [ ] 分类筛选工作
- [ ] 下拉刷新主页
- [ ] 侧边栏菜单打开
- [ ] 导航到课程页面
- [ ] 课程列表显示
- [ ] 点击查看课程详情
- [ ] 导航到讲师页面
- [ ] 讲师列表显示
- [ ] 点击查看讲师详情
- [ ] 退出登录

---

## 🐛 遇到问题？

### 快速诊断

**问题 1: 无法连接后端**
```bash
# 检查后端是否运行
curl http://localhost:8888/artcate/list

# 如果失败，重启后端
cd w:/artshow
mvn spring-boot:run
```

**问题 2: 登录失败**
- 检查用户是否存在（使用 Swagger UI）
- 检查用户名密码是否正确

**问题 3: 图片不显示**
- 正常现象（如果数据库没有图片数据）
- 会显示灰色占位符

**问题 4: 编译错误**
```bash
# 清理并重新构建
flutter clean
flutter pub get
flutter run
```

### 查看详细故障排除指南
- 📖 TROUBLESHOOTING.md - 完整的故障排除指南
- 📖 SETUP.md - 详细设置指南
- 📖 QUICKSTART.md - 快速启动指南

---

## 🎮 开发模式快捷键

应用运行时可以使用：

- `r` - 热重载（快速更新）
- `R` - 热重启（完全重启）
- `h` - 显示帮助
- `d` - 分离（返回命令行）
- `q` - 退出

---

## 📊 运行状态确认

### ✅ 正常运行应该看到：

**后端日志:**
```
Started ArtshowApplication in X.XXX seconds (JVM running for Y.YYY)
```

**Flutter 日志:**
```
Flutter run key commands.
r Hot reload.
R Hot restart.
h List all available interactive commands.
```

**浏览器/模拟器:**
- 应用正常显示
- 可以登录
- 可以浏览内容

---

## 🎉 成功！

如果所有检查都通过，恭喜你！ArtShow Mobile 已经成功运行了。

**下一步:**
- 探索所有功能
- 添加更多产品数据
- 自定义样式和内容
- 查看 README.md 了解更多功能

---

## 📞 需要帮助？

**文档:**
- README.md - 完整项目文档
- QUICKSTART.md - 5分钟快速启动
- SETUP.md - 详细设置指南
- TROUBLESHOOTING.md - 故障排除指南
- DEVELOPMENT_SUMMARY.md - 开发总结

**在线资源:**
- Flutter 文档: https://docs.flutter.dev/
- 后端 API: http://localhost:8888/swagger-ui.html

---

**清单版本:** 1.0  
**最后更新:** 2026-07-04  
**状态:** ✅ 已测试可用

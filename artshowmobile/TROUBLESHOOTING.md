# 故障排除指南

## 常见问题及解决方案

### 问题 1: carousel_slider 编译错误

**错误信息:**
```
'CarouselController' is imported from both 'package:carousel_slider/carousel_controller.dart' 
and 'package:flutter/src/material/carousel.dart'.
```

**原因:** carousel_slider 4.x 版本与 Flutter 新版本的 Material 组件命名冲突

**解决方案:**
```bash
# 已在项目中修复，使用 carousel_slider 5.0.0
flutter clean
flutter pub get
```

---

### 问题 2: 无法连接到后端

**错误信息:** `Connection refused` 或 `Failed to connect`

**检查清单:**

1. ✅ **后端是否运行？**
   ```bash
   # 测试后端
   curl http://localhost:8888/artcate/list
   ```

2. ✅ **使用正确的 API 地址？**
   
   编辑 `lib/utils/constants.dart`:
   
   ```dart
   // Chrome 浏览器
   static const String baseUrl = 'http://localhost:8888';
   
   // Android 模拟器
   static const String baseUrl = 'http://10.0.2.2:8888';
   
   // iOS 模拟器
   static const String baseUrl = 'http://localhost:8888';
   
   // 真实设备（查看你的 IP）
   static const String baseUrl = 'http://192.168.x.x:8888';
   ```
   
   **查看你的 IP:**
   ```bash
   # Windows
   ipconfig
   
   # Mac/Linux
   ifconfig
   ```

3. ✅ **防火墙是否阻止？**
   - 暂时关闭防火墙测试
   - 或添加端口 8888 到允许列表

---

### 问题 3: 登录失败 401

**错误信息:** `Unauthorized` 或 `Invalid username or password`

**原因:** 
- 用户不存在
- 密码错误
- 数据库未初始化

**解决方案:**

**方法 1: 使用 Swagger UI 创建用户**
1. 访问 http://localhost:8888/swagger-ui.html
2. 找到 `POST /user` 接口
3. 点击 "Try it out"
4. 输入：
   ```json
   {
     "userName": "demo",
     "password": "123456",
     "email": "demo@artshow.com"
   }
   ```
5. 点击 "Execute"

**方法 2: 使用 curl**
```bash
curl -X POST http://localhost:8888/user \
  -H "Content-Type: application/json" \
  -d '{"userName":"demo","password":"123456","email":"demo@artshow.com"}'
```

**方法 3: 检查数据库**
```sql
-- 连接到 MySQL
mysql -u root -p

-- 切换数据库
USE artshow;

-- 查看用户
SELECT * FROM user;
```

---

### 问题 4: 图片不显示

**症状:** 产品/课程/讲师卡片显示灰色占位符

**原因:** 
- 数据库中的图片 URL 无效或为空
- 网络问题
- CORS 问题

**解决方案:**

1. **检查数据库中的 URL:**
   ```sql
   SELECT name, cover_image_url FROM product LIMIT 5;
   ```

2. **使用有效的测试图片:**
   - 使用占位图片服务: `https://via.placeholder.com/300`
   - 或使用真实的图片 URL

3. **更新数据库 URL:**
   ```sql
   UPDATE product 
   SET cover_image_url = 'https://via.placeholder.com/300/9C27B0/FFFFFF?text=Art'
   WHERE cover_image_url IS NULL OR cover_image_url = '';
   ```

---

### 问题 5: Flutter 找不到

**错误信息:** `flutter: command not found`

**解决方案:**

1. **安装 Flutter:**
   - 访问 https://docs.flutter.dev/get-started/install
   - 下载适合你系统的版本
   - 解压并添加到 PATH

2. **验证安装:**
   ```bash
   flutter doctor
   ```

3. **检查环境:**
   ```bash
   # 查看 Flutter 版本
   flutter --version
   
   # 查看可用设备
   flutter devices
   ```

---

### 问题 6: 依赖下载慢

**症状:** `flutter pub get` 很慢或超时

**解决方案:**

1. **使用国内镜像（中国用户）:**
   ```bash
   # Windows PowerShell
   $env:PUB_HOSTED_URL="https://pub.flutter-io.cn"
   $env:FLUTTER_STORAGE_BASE_URL="https://storage.flutter-io.cn"
   
   # Mac/Linux
   export PUB_HOSTED_URL="https://pub.flutter-io.cn"
   export FLUTTER_STORAGE_BASE_URL="https://storage.flutter-io.cn"
   
   flutter pub get
   ```

2. **清理并重试:**
   ```bash
   flutter clean
   flutter pub cache clean
   flutter pub get
   ```

---

### 问题 7: 热重载不工作

**症状:** 修改代码后按 `r` 没有更新

**解决方案:**

1. **使用热重启:** 按 `R`（大写）而不是 `r`
2. **完全重启:** 停止应用并重新运行 `flutter run`
3. **检查错误:** 查看终端是否有编译错误

---

### 问题 8: Android 构建失败

**错误信息:** `minSdkVersion` 相关错误

**解决方案:**

编辑 `android/app/build.gradle`:
```gradle
android {
    defaultConfig {
        minSdkVersion 21  // 至少 21
        targetSdkVersion 33
        // ...
    }
}
```

---

### 问题 9: 数据加载慢

**症状:** 列表加载很慢

**检查清单:**

1. ✅ **网络速度:** 检查网络连接
2. ✅ **后端性能:** 查看后端日志
3. ✅ **数据量:** 检查返回的数据量

**临时解决方案:**
- 使用下拉刷新重试
- 检查后端日志是否有错误
- 确保数据库连接正常

---

### 问题 10: Web 版本 CORS 错误

**错误信息:** `Access-Control-Allow-Origin` 错误

**解决方案:**

在后端添加 CORS 配置：

```java
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:*")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
```

---

## 调试技巧

### 1. 查看 Flutter 日志
```bash
flutter run --verbose
```

### 2. 查看网络请求
在 `lib/services/api_service.dart` 中添加：
```dart
print('Request URL: ${response.request}');
print('Response Status: ${response.statusCode}');
print('Response Body: ${response.body}');
```

### 3. 查看后端日志
```bash
# 在后端项目中
tail -f logs/application.log
```

### 4. 清理所有缓存
```bash
flutter clean
flutter pub cache clean
rm -rf .dart_tool
rm -rf build
flutter pub get
```

---

## 性能优化建议

### 1. 图片优化
- 使用合适的图片尺寸
- 启用图片缓存（已实现）
- 使用 WebP 格式

### 2. 网络优化
- 实现分页加载
- 使用数据缓存
- 减少不必要的请求

### 3. UI 优化
- 使用 const 构造函数
- 避免不必要的 rebuild
- 使用 ListView.builder 而不是 ListView

---

## 获取帮助

如果以上方案都无法解决问题：

1. **查看完整错误日志**
   ```bash
   flutter run --verbose > debug.log 2>&1
   ```

2. **查看文档**
   - QUICKSTART.md - 快速启动指南
   - SETUP.md - 详细设置指南
   - README.md - 完整项目文档

3. **检查 Flutter 环境**
   ```bash
   flutter doctor -v
   ```

4. **重新初始化项目**
   ```bash
   cd w:/artshow/artshowmobile
   flutter clean
   rm -rf .dart_tool
   rm pubspec.lock
   flutter pub get
   ```

---

**最后更新:** 2026-07-04  
**状态:** 所有已知问题已修复

# Maven 到 Gradle 迁移说明

本项目已从 Maven 迁移到 Gradle 构建系统。

## 已完成的工作

1. ✅ 创建了 `build.gradle` - 主构建配置文件
2. ✅ 创建了 `settings.gradle` - 项目设置文件
3. ✅ 创建了 `gradle.properties` - Gradle 属性配置
4. ✅ 创建了 Gradle Wrapper 文件（`gradlew`、`gradlew.bat`）
5. ✅ 更新了 `.gitignore` 以支持 Gradle
6. ✅ 所有 Maven 依赖已转换为 Gradle 格式

## Java 版本兼容性问题

当前系统使用 Java 25，但 Gradle 8.12 不支持该版本。有以下几种解决方案：

### 方案 1: 使用支持 Java 25 的 Gradle 版本（推荐）

需要 Gradle 8.10.2 或更高版本。手动下载并安装：

```bash
# 下载 Gradle 8.10.2（或更高版本）
wget https://services.gradle.org/distributions/gradle-8.10.2-all.zip -P /tmp

# 解压到用户目录
unzip /tmp/gradle-8.10.2-all.zip -d ~/gradle

# 更新 gradle-wrapper.properties
sed -i 's|gradle-8.12-bin.zip|gradle-8.10.2-all.zip|' gradle/wrapper/gradle-wrapper.properties

# 或者直接设置环境变量使用本地 Gradle
export PATH=~/gradle/gradle-8.10.2/bin:$PATH
```

### 方案 2: 安装并使用 Java 17

```bash
# Fedora/RHEL
sudo dnf install java-17-openjdk-devel

# 设置 JAVA_HOME 指向 Java 17
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk
export PATH=$JAVA_HOME/bin:$PATH

# 然后运行 Gradle
./gradlew build
```

### 方案 3: 使用 SDKMAN 管理 Gradle 和 Java

```bash
# 安装 SDKMAN
curl -s "https://get.sdkman.io" | bash
source "$HOME/.sdkman/bin/sdkman-init.sh"

# 安装 Gradle 8.10.2
sdk install gradle 8.10.2

# 安装 Java 17（可选）
sdk install java 17.0.9-tem
sdk use java 17.0.9-tem

# 使用 Gradle
gradle build
```

## 常用 Gradle 命令

### 构建项目
```bash
./gradlew build
```

### 运行应用
```bash
./gradlew bootRun
```

### 清理构建
```bash
./gradlew clean
```

### 运行测试
```bash
./gradlew test
```

### 查看依赖
```bash
./gradlew dependencies
```

### 创建可执行 JAR
```bash
./gradlew bootJar
```
生成的 JAR 文件位于 `build/libs/` 目录。

## Maven 与 Gradle 命令对照

| Maven 命令 | Gradle 命令 |
|-----------|------------|
| `mvn clean` | `./gradlew clean` |
| `mvn compile` | `./gradlew compileJava` |
| `mvn test` | `./gradlew test` |
| `mvn package` | `./gradlew build` |
| `mvn install` | `./gradlew publishToMavenLocal` |
| `mvn spring-boot:run` | `./gradlew bootRun` |
| `mvn dependency:tree` | `./gradlew dependencies` |

## 项目结构

项目结构保持不变：
```
artshow/
├── build.gradle              # Gradle 构建配置（替代 pom.xml）
├── settings.gradle           # Gradle 设置
├── gradle.properties         # Gradle 属性
├── gradlew                   # Unix/Linux Gradle Wrapper
├── gradlew.bat              # Windows Gradle Wrapper
├── gradle/
│   └── wrapper/
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
└── src/
    ├── main/
    │   ├── java/
    │   └── resources/
    └── test/
        ├── java/
        └── resources/
```

## 依赖管理

所有 Maven 依赖已转换为 Gradle 格式。查看 `build.gradle` 文件了解详情。

主要依赖：
- Spring Boot 3.5.16
- Spring Data JPA
- Spring Web
- Spring WebSocket
- Spring Security Crypto
- JWT (jjwt 0.11.5)
- MySQL Connector
- SpringDoc OpenAPI 2.3.0

## IDE 支持

### IntelliJ IDEA
1. 打开项目目录
2. IDEA 会自动识别为 Gradle 项目
3. 等待 Gradle 同步完成

### VS Code
1. 安装 "Gradle for Java" 扩展
2. 打开项目
3. 使用命令面板运行 Gradle 任务

## 故障排除

### 构建失败：Unsupported class file major version
说明 Gradle 版本不支持当前的 Java 版本。请按照上述方案升级 Gradle 或降级 Java。

### 下载依赖缓慢
可以配置国内镜像。编辑 `build.gradle`：

```groovy
repositories {
    maven { url 'https://maven.aliyun.com/repository/public/' }
    maven { url 'https://maven.aliyun.com/repository/spring/' }
    mavenCentral()
}
```

### Gradle Daemon 问题
```bash
# 停止所有 Gradle Daemon
./gradlew --stop

# 清理 Gradle 缓存
rm -rf ~/.gradle/caches/
```

## 保留 Maven 支持（可选）

如果需要同时保留 Maven 支持，可以保留 `pom.xml` 文件。两个构建系统可以并存。

## 迁移后的下一步

1. 解决 Java/Gradle 版本兼容性问题
2. 运行 `./gradlew build` 验证构建
3. 测试应用程序功能
4. 如果一切正常，可以删除 Maven 相关文件：
   - `pom.xml`
   - `mvnw` 和 `mvnw.cmd`
   - `.mvn/` 目录

## 参考资料

- [Gradle 官方文档](https://docs.gradle.org/)
- [Spring Boot with Gradle](https://docs.spring.io/spring-boot/docs/current/gradle-plugin/reference/htmlsingle/)
- [Gradle Wrapper 文档](https://docs.gradle.org/current/userguide/gradle_wrapper.html)

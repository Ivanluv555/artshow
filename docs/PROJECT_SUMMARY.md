# Project Status Summary

## Project Information

**Project Name:** Artshow - Art Exhibition and Course Management System  
**Version:** 0.0.1-SNAPSHOT  
**Last Updated:** 2026-07-04  
**Status:** Development

---

## ✅ Completed Tasks

### 1. Database-Entity Alignment (2026-07-02)
- ✅ Fixed 12 entities with missing timestamp fields
- ✅ Added @Column annotations to Sci (Shopping Cart) entity
- ✅ Created database migration script: `database/add_missing_fields.sql`
- ✅ Verified 100% alignment between DDL and entities
- **Report:** `database/DATABASE_ENTITY_ALIGNMENT.md`

### 2. Internationalization (2026-07-02)
- ✅ Converted all error messages to English
- ✅ Converted all log messages to English
- ✅ Converted API documentation to English
- ✅ Created logback-spring.xml for UTF-8 encoding
- ✅ Fixed log encoding issues (no more garbled Chinese)
- **Report:** `docs/INTERNATIONALIZATION_COMPLETE.md`

### 3. API Documentation Migration (2026-07-04)
- ✅ Removed Knife4j dependency
- ✅ Migrated to SpringDoc OpenAPI
- ✅ Updated WebConfig exclusion paths
- ✅ Updated application.properties configuration
- **Guide:** `docs/SPRINGDOC_GUIDE.md`

---

## 📁 Project Structure

```
artshow/
├── src/
│   ├── main/
│   │   ├── java/org/ivan/artshow/
│   │   │   ├── common/           # Common utilities and configuration
│   │   │   │   ├── auth/          # Authentication (JWT, Interceptor)
│   │   │   │   ├── config/        # Spring configuration (Web, Swagger)
│   │   │   │   ├── core/          # Core classes (Result, ResultCodes)
│   │   │   │   ├── exception/     # Exception handling
│   │   │   │   └── utils/         # Utility classes (JWT, Validation, Date)
│   │   │   └── module/            # Business modules
│   │   │       ├── address/       # User addresses
│   │   │       ├── artcategory/   # Art categories
│   │   │       ├── artsubcategory/# Art subcategories
│   │   │       ├── badge/         # User badges
│   │   │       ├── chapter/       # Course chapters
│   │   │       ├── collection/    # Post collections
│   │   │       ├── comment/       # Post comments
│   │   │       ├── course/        # Courses and enrollments
│   │   │       ├── instructor/    # Course instructors
│   │   │       ├── like/          # Post likes
│   │   │       ├── order/         # Orders
│   │   │       ├── orderitem/     # Order items
│   │   │       ├── post/          # Social posts
│   │   │       ├── product/       # Products
│   │   │       ├── shopcartitem/  # Shopping cart
│   │   │       └── user/          # Users
│   │   └── resources/
│   │       ├── application.properties  # Application configuration
│   │       └── logback-spring.xml      # Logging configuration
│   └── test/                      # Unit and integration tests
├── database/
│   ├── ddl.sql                    # Complete database schema
│   ├── add_missing_fields.sql     # Migration script for timestamp fields
│   └── DATABASE_ENTITY_ALIGNMENT.md  # Alignment report
├── docs/
│   ├── SPRINGDOC_GUIDE.md         # SpringDoc usage guide
│   ├── INTERNATIONALIZATION_COMPLETE.md  # i18n report
│   └── PROJECT_SUMMARY.md         # This file
└── pom.xml                        # Maven dependencies
```

---

## 🗄️ Database Schema

### Core Tables (20)

#### User Management
- `user` - User accounts and profiles
- `user_address` - User shipping addresses
- `user_badge` - User achievement badges

#### E-commerce
- `product` - Product catalog
- `shopping_cart_item` - Shopping cart
- `order` - Orders
- `order_item` - Order line items
- `order_shipping_address` - Order delivery addresses

#### Social Platform
- `post` - User posts
- `post_comment` - Post comments
- `post_like` - Post likes
- `post_collection` - Post favorites

#### Art Categories
- `art_category` - Main art categories
- `art_subcategory` - Art subcategories

#### Course System
- `course` - Course catalog
- `course_instructor` - Course instructors
- `course_outline` - Course chapters
- `user_course_enrollment` - Course registrations
- `user_course_chapter_completed` - Chapter completion tracking

#### Gamification
- `badge` - Available badges

**Total:** 20 tables, 100% aligned with entity classes

---

## 🔧 Technology Stack

### Backend Framework
- **Spring Boot:** 3.3.7
- **Java:** 17
- **Build Tool:** Maven

### Database
- **Database:** MySQL
- **ORM:** Spring Data JPA / Hibernate
- **Connection Pool:** HikariCP (default)

### Security
- **Authentication:** JWT (JSON Web Tokens)
- **Library:** jjwt 0.11.5
- **Token Storage:** Authorization header (Bearer scheme)

### API Documentation
- **Framework:** SpringDoc OpenAPI 2.3.0
- **UI:** Swagger UI
- **Access:** http://localhost:8888/swagger-ui.html

### Logging
- **Framework:** Logback (Spring Boot default)
- **Encoding:** UTF-8
- **Format:** Timestamp + Thread + Level + Logger + Message

### Validation
- **Framework:** Hibernate Validator (Jakarta Bean Validation)

### Utilities
- **Lombok:** Code generation (getters, setters, constructors)
- **Jackson:** JSON serialization/deserialization

---

## 🚀 Getting Started

### Prerequisites
- Java 17 or higher
- Maven 3.6+
- MySQL 8.0+

### Configuration

**Database Connection** (application.properties):
```properties
spring.datasource.url=${DB_URL:jdbc:mysql://47.237.188.77:3306/wChina?serverTimezone=UTC}
spring.datasource.username=${DB_USERNAME:root}
spring.datasource.password=${DB_PASSWORD:WaterChina@242526}
```

**JWT Secret**:
```properties
jwt.secret=${JWT_SECRET:artshow_local_dev_secret_key_must_be_very_long_123456789}
```

### Setup Steps

1. **Clone Repository**
   ```bash
   git clone <repository-url>
   cd artshow
   ```

2. **Create Database**
   ```bash
   mysql -u root -p < database/ddl.sql
   ```

3. **Apply Migrations** (if needed)
   ```bash
   mysql -u root -p artshow < database/add_missing_fields.sql
   ```

4. **Build Project**
   ```bash
   mvn clean install
   ```

5. **Run Application**
   ```bash
   mvn spring-boot:run
   ```

6. **Access API Documentation**
   - Open browser: http://localhost:8888/swagger-ui.html

---

## 🔐 Authentication Flow

### 1. Login
```bash
POST /user/login
{
  "userName": "admin",
  "password": "123456"
}
```

**Response:**
```json
{
  "code": 200,
  "msg": "Success",
  "data": "eyJhbGciOiJIUzI1NiJ9..."
}
```

### 2. Use Token
```bash
GET /user/list
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...
```

### 3. Token Validation
- Interceptor checks Authorization header
- Validates JWT signature and expiration
- Extracts user ID and stores in ThreadLocal
- Request proceeds if valid, returns 401 if invalid

---

## 📊 Code Quality

### Architecture Pattern
- **Layered Architecture:**
  - Controller → Service → Repository
  - DTO for data transfer
  - Entity for database mapping

### Code Standards
- ✅ English comments and messages
- ✅ Consistent naming conventions
- ✅ Proper exception handling
- ✅ Logging at appropriate levels
- ✅ Input validation on DTOs
- ✅ JWT authentication on all endpoints (except login/register)

### Testing
- Unit tests: `src/test/java`
- Integration tests: TBD

---

## 📝 API Endpoints Summary

### Public Endpoints (No Auth Required)
- `POST /user/login` - User login
- `POST /user/register` - User registration

### Protected Endpoints (Auth Required)

#### User Management
- `GET /user` - Get user by ID
- `GET /user/list` - List all users
- `POST /user` - Create user
- `PUT /user` - Update user
- `DELETE /user` - Delete user
- `POST /user/batch` - Batch query users

#### Product Management
- Product CRUD operations
- Product search and filtering

#### Order Management
- Order creation and tracking
- Order history

#### Course Management
- Course catalog
- Enrollment management
- Chapter completion tracking

#### Social Features
- Post creation and interaction
- Comments and likes
- Collections

---

## 🔍 Troubleshooting

### Issue: 401 Not logged in

**Check:**
1. Token is valid (not expired)
2. Token is in Authorization header
3. Token has "Bearer " prefix
4. User ID exists in database

**Debug:**
```properties
logging.level.org.ivan.artshow.common.auth=DEBUG
```

### Issue: Database connection failed

**Check:**
1. MySQL is running
2. Database exists: `artshow`
3. Credentials are correct
4. Network connectivity

### Issue: Port 8888 already in use

**Solution:**
Change port in application.properties:
```properties
server.port=8080
```

---

## 📚 Documentation Files

### Active Documentation
- `docs/SPRINGDOC_GUIDE.md` - API documentation guide
- `docs/INTERNATIONALIZATION_COMPLETE.md` - i18n report
- `docs/PROJECT_SUMMARY.md` - This file
- `database/DATABASE_ENTITY_ALIGNMENT.md` - Entity alignment report

### Database
- `database/ddl.sql` - Complete schema
- `database/add_missing_fields.sql` - Migration script

---

## 🎯 Next Steps

### Short Term
- [ ] Add more unit tests
- [ ] Implement user registration endpoint
- [ ] Add API rate limiting
- [ ] Implement refresh token mechanism

### Medium Term
- [ ] Add Redis for caching
- [ ] Implement file upload for images
- [ ] Add email notifications
- [ ] Implement search functionality

### Long Term
- [ ] Add WebSocket for real-time features
- [ ] Implement payment integration
- [ ] Add admin dashboard
- [ ] Mobile app API optimization

---

## 👥 Team

**Developer:** Ivan Horn  
**Contact:** ivan@artshow.com

---

## 📄 License

TBD

---

**Document Version:** 1.0  
**Generated:** 2026-07-04  
**Status:** Active

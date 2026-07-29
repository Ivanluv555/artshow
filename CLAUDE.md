# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

Artshow is a full-stack art exhibition and online course management system with three main components:
- **Backend**: Spring Boot 3.5.16 REST API (Java 25, Gradle)
- **Frontend**: Vue 3 + Vite web application
- **Mobile**: Flutter cross-platform app

The backend serves as a comprehensive API for art categories, online courses, e-commerce (products/orders), and social features (posts/comments/likes).

## Build and Development Commands

### Backend (Spring Boot + Gradle)

```bash
# Build the project
./gradlew build

# Run the application (localhost:8888)
./gradlew bootRun

# Run tests
./gradlew test

# Clean build artifacts
./gradlew clean

# Create executable JAR
./gradlew bootJar
# Output: build/libs/artshow-0.0.1-SNAPSHOT.jar

# Run the JAR
java -jar build/libs/artshow-0.0.1-SNAPSHOT.jar
```

### Frontend (Vue 3)

```bash
cd frontend

# Install dependencies
npm install

# Run dev server (localhost:3000)
npm run dev

# Build for production
npm run build

# Preview production build
npm run preview
```

### Mobile (Flutter)

See [artshowmobile/README.md](artshowmobile/README.md) for Flutter-specific setup.

## Architecture

### Backend Structure

The backend follows a modular architecture organized by business domain:

```
src/main/java/org/ivan/artshow/
├── common/                    # Cross-cutting concerns
│   ├── auth/                 # JWT authentication & authorization
│   │   ├── AuthInterceptor   # JWT validation, role-based access control
│   │   ├── UserContext       # ThreadLocal user context
│   │   ├── @Public           # Skip authentication annotation
│   │   └── @RequireRole      # Role requirement annotation
│   ├── config/               # Spring configuration
│   │   ├── WebConfig         # Interceptor registration
│   │   ├── SwaggerConfig     # API documentation
│   │   └── SnowflakeId*      # Snowflake ID generator for entities
│   ├── core/
│   │   ├── result/Result     # Unified API response wrapper
│   │   └── resultcode/       # Standard result codes
│   ├── exception/            # Global exception handling
│   └── utils/                # Utilities (JWT, Password, Date, Validation)
└── module/                   # Business modules
    ├── user/                 # User management & authentication
    ├── address/              # User addresses
    ├── artcategory/          # Art categories
    ├── artsubcategory/       # Art subcategories
    ├── course/               # Online courses
    ├── instructor/           # Course instructors
    ├── chapter/              # Course chapters
    ├── enrollment/           # Course enrollments
    ├── product/              # E-commerce products
    ├── order/                # Orders
    ├── orderitem/            # Order line items
    ├── shopcartitem/         # Shopping cart
    ├── post/                 # Social posts
    ├── comment/              # Post comments
    ├── like/                 # Post likes
    ├── collection/           # User collections
    └── badge/                # User badges/achievements
```

Each module follows a consistent layered pattern:
- **controller/** - REST endpoints
- **service/** - Business logic
- **repository/** - JPA repositories
- **pojo/** - POJOs (entities, DTOs)

### Authentication & Authorization

The system uses JWT tokens with role-based access control:

1. **JWT Authentication**: `AuthInterceptor` validates JWT tokens from `Authorization` header
2. **UserContext**: ThreadLocal storage for current user ID and role
3. **Annotations**:
   - `@Public` - Skip authentication (e.g., login, register)
   - `@RequireRole(UserRole.ADMIN)` - Require specific role
4. **Roles**: ADMIN, USER (defined in `UserRole` enum)

**Important**: By default, all endpoints require authentication. Use `@Public` for public endpoints.

### Response Format

All API responses use the `Result<T>` wrapper:

```json
{
  "code": 200,
  "message": "success",
  "data": { ... }
}
```

Controllers should return `Result.success(data)` or `Result.error(ResultCodes.ERROR_CODE)`.

### ID Generation

The system uses **Snowflake IDs** (64-bit Long) for all entity primary keys, not auto-increment integers. Entities are annotated with `@SnowflakeId` for automatic ID generation.

### Database

- **Type**: MySQL 8.0+
- **Dialect**: MariaDB (configured in application.yml)
- **Connection**: Default localhost:3306/artshow (configurable via environment variables)
- **Schema**: 20+ tables covering users, courses, products, orders, posts, and social interactions
- **Migrations**: Manual SQL scripts in `database/ddl.sql`
- **JPA**: Spring Data JPA with Hibernate (ddl-auto: update)

### Configuration

Environment variables override defaults in `application.yml`:
- `DB_URL` - Database connection URL
- `DB_USERNAME` / `DB_PASSWORD` - Database credentials
- `JWT_SECRET` - JWT signing secret (must be very long)
- `JPA_DDL_AUTO` - Hibernate DDL mode (default: update)
- `SHOW_SQL` - Show SQL queries (default: false)

## API Documentation

The API is documented with SpringDoc OpenAPI (Swagger):
- **Swagger UI**: http://localhost:8888/swagger-ui.html
- **OpenAPI JSON**: http://localhost:8888/v3/api-docs

To authenticate in Swagger UI:
1. Call `POST /user/login` with credentials
2. Copy the returned token
3. Click "Authorize" button
4. Paste token (without "Bearer" prefix)

## Frontend Architecture

The Vue 3 frontend uses:
- **Vite** for fast development and building
- **Element Plus** for UI components
- **Axios** for API requests with automatic JWT token injection
- **Vue Router** for navigation

API requests are proxied through Vite dev server (`/api` → `http://localhost:8888`).

## Testing

Run backend tests:
```bash
./gradlew test
```

The project uses JUnit Platform for testing with Spring Boot Test support.

## Important Patterns

### Permission Checks

Many operations include author/admin permission checks. When modifying resources (posts, comments, products), verify:
```java
if (!userId.equals(resource.getUserId()) && !UserContext.hasRole(UserRole.ADMIN)) {
    throw new BizException(ResultCodes.FORBIDDEN);
}
```

### Password Security

Passwords are hashed using BCrypt via `PasswordUtils.hashPassword()` and validated with `PasswordUtils.verifyPassword()`.

### Date Handling

Use `DateUtils` for consistent date formatting and timezone handling.

## Project History

The codebase has undergone several significant migrations documented in `docs/`:
- Snowflake ID migration (from auto-increment)
- BCrypt password encryption implementation
- BigDecimal for monetary values (precision handling)
- Permission control enhancements
- User-instructor relationship implementation

Refer to these documents when modifying related functionality.

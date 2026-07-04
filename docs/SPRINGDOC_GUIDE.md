# SpringDoc API Documentation Guide

## Overview

The project now uses **SpringDoc OpenAPI** (native Swagger UI) instead of Knife4j for API documentation.

**Access URL:** http://localhost:8888/swagger-ui.html

---

## Configuration

### Dependencies (pom.xml)
```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.3.0</version>
</dependency>
```

### Application Properties
```properties
# SpringDoc OpenAPI configuration
springdoc.api-docs.enabled=true
springdoc.api-docs.path=/v3/api-docs
springdoc.swagger-ui.enabled=true
springdoc.swagger-ui.path=/swagger-ui.html
springdoc.swagger-ui.operationsSorter=method
springdoc.swagger-ui.tagsSorter=alpha
springdoc.default-produces-media-type=application/json
springdoc.default-consumes-media-type=application/json
```

### Interceptor Exclusions (WebConfig.java)
```java
.excludePathPatterns(
    "/user/login",
    "/user/register",
    "/v3/api-docs/**",
    "/swagger-ui.html",
    "/swagger-ui/**",
    "/swagger-resources/**",
    "/webjars/**",
    "/favicon.ico",
    "/error"
)
```

---

## Using JWT Authentication

### Step 1: Login to Get Token

**Endpoint:** `POST /user/login`

**Request:**
```json
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
  "data": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNzA5MzU2MTIzfQ.signature"
}
```

**Copy the token** from the `data` field.

---

### Step 2: Authorize in Swagger UI

1. Visit http://localhost:8888/swagger-ui.html
2. Click the **"Authorize"** button (🔓 icon in the top right)
3. In the popup dialog, find **"BearerAuth (http, Bearer)"**
4. In the **Value** field, paste the token **without "Bearer " prefix**
5. Click **"Authorize"**
6. Click **"Close"**
7. The lock icon should change to 🔒

---

### Step 3: Test Authenticated Endpoints

Now all requests will automatically include the Authorization header.

**Example:**
- Navigate to **User Management** → **GET /user/list**
- Click **"Try it out"**
- Click **"Execute"**
- Should return **200** with user list

---

## API Documentation Structure

### Available URLs

| URL | Purpose |
|-----|---------|
| http://localhost:8888/swagger-ui.html | Interactive API documentation UI |
| http://localhost:8888/v3/api-docs | OpenAPI JSON specification |
| http://localhost:8888/v3/api-docs.yaml | OpenAPI YAML specification |

### API Groups

- **User Management** - User registration, login, CRUD operations
- **Product Management** - Product catalog and inventory
- **Order Management** - Order processing and tracking
- **Course Management** - Course and enrollment operations
- **Address Management** - User address management
- **Post Management** - Social posts and interactions

---

## Common Issues

### Issue 1: 401 Unauthorized

**Symptoms:**
```json
{
  "code": 401,
  "msg": "Not logged in"
}
```

**Solution:**
1. Verify you clicked the "Authorize" button
2. Check that token is valid (not expired)
3. Verify token was pasted correctly (no extra spaces)
4. Re-login if token expired

---

### Issue 2: Token Not Sent

**Check in Browser DevTools:**
1. Press **F12** to open Developer Tools
2. Go to **Network** tab
3. Execute an API request
4. Click on the request in the Network tab
5. Check **Request Headers** should include:
   ```
   Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...
   ```

**If missing:**
- Authorization was not configured
- Click "Authorize" button again

---

### Issue 3: CORS Errors

**Solution:**
WebConfig already includes CORS configuration:
```java
.allowedOriginPatterns("*")
.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
.allowedHeaders("*")
.allowCredentials(true)
```

If issues persist, check browser console for specific CORS error messages.

---

## Testing with cURL

### Login
```bash
curl -X POST http://localhost:8888/user/login \
  -H "Content-Type: application/json" \
  -d '{"userName":"admin","password":"123456"}'
```

### Use Token
```bash
TOKEN="your_token_here"

curl -X GET http://localhost:8888/user/list \
  -H "Authorization: Bearer $TOKEN"
```

### Full Script
```bash
#!/bin/bash

# Login and extract token
TOKEN=$(curl -s -X POST http://localhost:8888/user/login \
  -H "Content-Type: application/json" \
  -d '{"userName":"admin","password":"123456"}' \
  | grep -o '"data":"[^"]*"' | cut -d'"' -f4)

echo "Token: $TOKEN"

# Test authenticated endpoint
curl -X GET http://localhost:8888/user/list \
  -H "Authorization: Bearer $TOKEN"
```

---

## API Response Format

### Success Response
```json
{
  "code": 200,
  "msg": "Success",
  "data": { /* response data */ }
}
```

### Error Response
```json
{
  "code": 401,
  "msg": "Not logged in"
}
```

### Common Status Codes

| Code | Message | Meaning |
|------|---------|---------|
| 200 | Success | Operation successful |
| 400 | Login failed | Invalid credentials |
| 401 | Not logged in | Missing or invalid token |
| 402 | No permission | Insufficient privileges |
| 404 | Resource not found | Entity doesn't exist |
| 500 | Internal server error | Server-side error |

---

## Security Configuration

### JWT Token
- **Format:** Bearer token
- **Location:** Authorization header
- **Expiration:** 1 hour (configurable in JwtUtils)
- **Secret Key:** Configured in application.properties

### Token Validation
All endpoints except the following require authentication:
- `POST /user/login`
- `POST /user/register`
- Swagger UI resources
- API documentation endpoints

---

## Development Tips

### 1. Enable Debug Logging
```properties
logging.level.org.ivan.artshow.common.auth=DEBUG
```

### 2. Check Token in Logs
```
DEBUG o.i.a.common.auth.AuthInterceptor - Authorization header: Bearer eyJhbGc...
DEBUG o.i.a.common.auth.AuthInterceptor - Token validated successfully, user ID: 1
```

### 3. Customize Swagger UI
You can customize the Swagger UI appearance by adding:
```properties
springdoc.swagger-ui.displayRequestDuration=true
springdoc.swagger-ui.defaultModelsExpandDepth=1
springdoc.swagger-ui.defaultModelExpandDepth=1
```

---

## Migration from Knife4j

### Changes Made

1. **Removed Dependency:**
   ```xml
   <!-- Removed -->
   <dependency>
       <groupId>com.github.xiaoymin</groupId>
       <artifactId>knife4j-openapi3-jakarta-spring-boot-starter</artifactId>
   </dependency>
   ```

2. **Added SpringDoc:**
   ```xml
   <dependency>
       <groupId>org.springdoc</groupId>
       <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
       <version>2.3.0</version>
   </dependency>
   ```

3. **URL Changes:**
   - Old: http://localhost:8888/doc.html
   - New: http://localhost:8888/swagger-ui.html

4. **Configuration Updates:**
   - Removed Knife4j-specific properties
   - Added SpringDoc properties
   - Updated WebConfig exclusion paths

### Why Switch to SpringDoc?

- **Native Integration:** Better Spring Boot integration
- **Active Development:** More actively maintained
- **Standard Compliance:** Pure OpenAPI 3.0 specification
- **Simpler Configuration:** Less configuration overhead
- **Better Performance:** Lighter weight implementation

---

## Additional Resources

### Official Documentation
- SpringDoc: https://springdoc.org/
- OpenAPI Specification: https://spec.openapis.org/oas/v3.1.0

### Project Files
- Configuration: `src/main/java/org/ivan/artshow/common/config/SwaggerConfig.java`
- Properties: `src/main/resources/application.properties`
- Interceptor: `src/main/java/org/ivan/artshow/common/auth/AuthInterceptor.java`

---

**Document Version:** 1.0  
**Last Updated:** 2026-07-04  
**Status:** Active

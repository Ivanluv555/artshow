# Artshow - Art Exhibition and Course Management System

A comprehensive platform for art exhibition, online courses, and community engagement.

## Project Structure

```
artshow/
├── frontend/              # Vue 3 frontend application
├── src/                   # Spring Boot backend
│   └── main/
│       ├── java/
│       └── resources/
├── database/              # Database schemas and migrations
├── docs/                  # Documentation
└── pom.xml               # Maven configuration
```

## Technology Stack

### Backend
- **Spring Boot 3.3.7** - Java application framework
- **Spring Data JPA** - Data access layer
- **MySQL** - Database
- **JWT** - Authentication
- **SpringDoc OpenAPI** - API documentation

### Frontend
- **Vue 3** - Progressive JavaScript framework
- **Vite** - Build tool
- **Element Plus** - UI component library
- **Axios** - HTTP client
- **Vue Router** - Routing

## Quick Start

### Prerequisites
- JDK 17+
- Maven 3.6+
- MySQL 8.0+
- Node.js 16+

### Backend Setup

1. Configure database in `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/artshow
spring.datasource.username=root
spring.datasource.password=your_password
```

2. Run database schema:
```bash
mysql -u root -p < database/ddl.sql
```

3. Start backend:
```bash
mvn spring-boot:run
```

Backend runs at: http://localhost:8888

### Frontend Setup

1. Install dependencies:
```bash
cd frontend
npm install
```

2. Start development server:
```bash
npm run dev
```

Frontend runs at: http://localhost:3000

## API Documentation

Access SpringDoc API documentation at:
- Swagger UI: http://localhost:8888/swagger-ui.html
- OpenAPI JSON: http://localhost:8888/v3/api-docs

### Authentication

1. Login via `POST /user/login`:
```json
{
  "userName": "admin",
  "password": "123456"
}
```

2. Use returned token in Authorization header:
```
Authorization: Bearer <your-token>
```

3. In Swagger UI: Click "Authorize" button and paste token (without "Bearer" prefix)

## Features

### User Management
- User registration and login
- Profile management
- JWT-based authentication

### Art Categories
- Browse art categories and subcategories
- Detailed information about art forms

### Online Courses
- Course catalog with instructors
- Course enrollment and progress tracking
- Chapter-based learning

### Products & Shopping
- Product browsing and search
- Shopping cart management
- Order processing

### Community
- Create and share posts
- Like and comment system
- User collections

### Badges & Achievements
- User badge system
- Achievement tracking

## Database Schema

20 tables covering:
- User management (user, user_address, user_badge)
- Art content (art_category, art_subcategory)
- Courses (course, course_instructor, course_outline)
- E-commerce (product, order, order_item, shopping_cart_item)
- Community (post, post_comment, post_like, post_collection)
- Badges (badge, user_badge)
- Enrollments (user_course_enrollment, user_course_chapter_completed)

See `database/DATABASE_ENTITY_ALIGNMENT.md` for detailed field mapping.

## Documentation

- [SpringDoc Guide](docs/SPRINGDOC_GUIDE.md) - API documentation usage
- [Project Summary](docs/PROJECT_SUMMARY.md) - Detailed project overview
- [Database Alignment](database/DATABASE_ENTITY_ALIGNMENT.md) - Entity-table mapping
- [Frontend README](frontend/README.md) - Frontend setup and structure

## Development

### Code Structure

**Backend (Java):**
```
src/main/java/org/ivan/artshow/
├── common/              # Common utilities and configuration
│   ├── auth/           # Authentication interceptor
│   ├── config/         # Spring configuration
│   ├── core/           # Core utilities
│   ├── exception/      # Exception handling
│   └── utils/          # Utility classes
└── module/             # Business modules
    ├── user/           # User management
    ├── product/        # Product management
    ├── course/         # Course management
    ├── post/           # Community posts
    └── ...
```

**Frontend (Vue):**
```
frontend/src/
├── api/                # API integration
├── views/              # Page components
├── router/             # Routing configuration
├── components/         # Reusable components
└── utils/              # Utility functions
```

### Building for Production

**Backend:**
```bash
mvn clean package
java -jar target/artshow-0.0.1-SNAPSHOT.jar
```

**Frontend:**
```bash
cd frontend
npm run build
# Dist files in frontend/dist/
```

## License

MIT

## Author

Ivan Horn

## Contributing

1. Fork the repository
2. Create your feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

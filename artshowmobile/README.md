# ArtShow Mobile

A Flutter mobile application for the ArtShow platform - Art Exhibition and Course Management System.

## Features

### 1. Authentication
- **Login Screen**: User authentication with username and password
- **JWT Token Management**: Secure token storage and automatic authorization

### 2. Home Screen
- **Banner Carousel**: Promotional banners with auto-scroll
- **Art Categories**: Browse art categories with filtering
- **Product Grid**: Display art products with images, prices, and certification badges
- **Navigation Drawer**: Quick access to all app sections

### 3. Courses
- **Course Listing**: Browse all available courses
- **Course Details**: View course information, pricing, student count
- **Instructor Info**: See course instructor details
- **Enrollment**: Enroll in courses (coming soon)

### 4. Instructors
- **Instructor Grid**: Browse all instructors
- **Instructor Profile**: View detailed instructor information
- **Biography**: Read instructor background and expertise
- **Courses by Instructor**: See courses taught by each instructor (coming soon)

### 5. Products (Art Works)
- **Product Cards**: Display art products with images
- **Certification Badge**: Show certified artworks
- **Stock Status**: Display availability information
- **Pricing**: Clear price display

## Project Structure

```
lib/
├── main.dart                 # App entry point
├── models/                   # Data models
│   ├── api_result.dart       # API response wrapper
│   ├── art_category.dart     # Art category model
│   ├── course.dart           # Course model
│   ├── instructor.dart       # Instructor model
│   └── product.dart          # Product model
├── screens/                  # App screens
│   ├── login_screen.dart     # Login page
│   ├── home_screen.dart      # Main home page
│   ├── courses_screen.dart   # Course listing
│   ├── course_detail_screen.dart
│   ├── instructors_screen.dart
│   └── instructor_detail_screen.dart
├── services/                 # Business logic
│   ├── api_service.dart      # Backend API communication
│   └── auth_service.dart     # Authentication management
├── widgets/                  # Reusable components
│   ├── category_chip.dart    # Category filter chip
│   ├── course_card.dart      # Course display card
│   ├── instructor_card.dart  # Instructor display card
│   └── product_card.dart     # Product display card
└── utils/                    # Utilities
    └── constants.dart        # App constants and config
```

## Backend API

This app connects to the ArtShow Spring Boot backend:

### Base URL
```
http://localhost:8888
```

### Main Endpoints
- `POST /user/login` - User authentication
- `GET /product/list` - Get all products
- `GET /artcate/list` - Get all art categories
- `GET /course/list` - Get all courses
- `GET /instructor/list` - Get all instructors
- `GET /course?courseId=X` - Get course details
- `GET /instructor?instructorId=X` - Get instructor details

### Authentication
The app uses JWT Bearer token authentication. After login, the token is:
- Stored locally using `shared_preferences`
- Automatically included in all API requests via `Authorization: Bearer <token>` header

## Configuration

### API Base URL

To change the backend API URL, edit `lib/utils/constants.dart`:

```dart
class ApiConstants {
  static const String baseUrl = 'http://your-backend-url:port';
  // ...
}
```

**Important**: 
- For Android emulator, use `http://10.0.2.2:8888` to access localhost
- For iOS simulator, use `http://localhost:8888`
- For physical devices, use your computer's IP address

## Getting Started

### Prerequisites
- Flutter SDK (3.12.2 or higher)
- Dart SDK
- Android Studio / VS Code with Flutter extensions
- Running ArtShow backend server

### Installation

1. **Clone the repository** (if not already done)

2. **Navigate to the mobile app directory**:
```bash
cd artshowmobile
```

3. **Install dependencies**:
```bash
flutter pub get
```

4. **Configure backend URL** (if needed):
Edit `lib/utils/constants.dart` and update the `baseUrl`.

5. **Run the app**:
```bash
# For Android
flutter run

# For iOS
flutter run -d ios

# For web (development)
flutter run -d chrome
```

## Dependencies

```yaml
dependencies:
  flutter:
    sdk: flutter
  cupertino_icons: ^1.0.8
  http: ^1.1.0                      # HTTP client
  provider: ^6.1.1                  # State management
  shared_preferences: ^2.2.2        # Local storage
  cached_network_image: ^3.3.1      # Image caching
  carousel_slider: ^4.2.1           # Banner carousel
  pull_to_refresh: ^2.0.0           # Pull to refresh
```

## Development

### Running in Development Mode

```bash
flutter run
```

### Building for Production

#### Android APK
```bash
flutter build apk --release
```

#### Android App Bundle (for Google Play)
```bash
flutter build appbundle --release
```

#### iOS
```bash
flutter build ios --release
```

### Code Organization

- **Models**: Data classes matching backend DTOs
- **Services**: API communication and business logic
- **Screens**: Full-page UI components
- **Widgets**: Reusable UI components
- **Utils**: Constants, helpers, and utilities

## Features Roadmap

### Current Features ✅
- [x] User login with JWT authentication
- [x] Home page with banners and categories
- [x] Product listing with images
- [x] Course listing and details
- [x] Instructor listing and profiles
- [x] Category filtering
- [x] Pull to refresh

### Coming Soon 🚧
- [ ] User registration
- [ ] Search functionality
- [ ] Shopping cart
- [ ] Course enrollment
- [ ] User profile
- [ ] Favorites/Collections
- [ ] Comments and likes
- [ ] Push notifications
- [ ] Offline mode
- [ ] Payment integration

## Troubleshooting

### Connection Issues

If you can't connect to the backend:

1. **Check backend is running**:
   ```bash
   curl http://localhost:8888/artcate/list
   ```

2. **For Android Emulator**, update the URL to:
   ```dart
   static const String baseUrl = 'http://10.0.2.2:8888';
   ```

3. **For iOS Simulator**, `localhost` should work

4. **For Physical Devices**, use your computer's local IP:
   ```dart
   static const String baseUrl = 'http://192.168.x.x:8888';
   ```

### Clear Cache

```bash
flutter clean
flutter pub get
```

### Debug Mode

Enable verbose logging in `lib/services/api_service.dart`:
```dart
print('Request: ${response.request}');
print('Response: ${response.body}');
```

## Testing

### Run Tests
```bash
flutter test
```

### Integration Tests
```bash
flutter drive --target=test_driver/app.dart
```

## License

MIT License - See main project LICENSE file

## Authors

Ivan Horn

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## Support

For issues and questions, please open an issue on GitHub.

---

**Note**: This is a mobile client for the ArtShow platform. Make sure the backend server is running before using this app.

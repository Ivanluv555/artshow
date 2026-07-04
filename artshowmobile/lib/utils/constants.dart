/// API Constants and Configuration
class ApiConstants {
  // Base URL - 根据后端配置修改
  static const String baseUrl = 'http://localhost:8888';

  // Auth endpoints
  static const String login = '/user/login';
  static const String register = '/user/register';

  // Product endpoints
  static const String productList = '/product/list';
  static const String product = '/product';

  // Category endpoints
  static const String categoryList = '/artcate/list';
  static const String category = '/artcate';

  // Course endpoints
  static const String courseList = '/course/list';
  static const String course = '/course';

  // Instructor endpoints
  static const String instructorList = '/instructor/list';
  static const String instructor = '/instructor';

  // Build full URL
  static String url(String endpoint) => '$baseUrl$endpoint';
}

/// App Constants
class AppConstants {
  static const String appName = 'ArtShow';
  static const String tokenKey = 'auth_token';
  static const String userIdKey = 'user_id';
}

/// UI Constants
class UIConstants {
  static const double paddingSmall = 8.0;
  static const double paddingMedium = 16.0;
  static const double paddingLarge = 24.0;

  static const double radiusSmall = 8.0;
  static const double radiusMedium = 12.0;
  static const double radiusLarge = 16.0;
}

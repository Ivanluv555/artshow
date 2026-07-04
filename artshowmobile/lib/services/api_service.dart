import 'dart:convert';
import 'package:http/http.dart' as http;
import 'package:shared_preferences/shared_preferences.dart';
import '../models/api_result.dart';
import '../models/product.dart';
import '../models/art_category.dart';
import '../models/course.dart';
import '../models/instructor.dart';
import '../utils/constants.dart';

/// API Service for backend communication
class ApiService {
  static final ApiService _instance = ApiService._internal();
  factory ApiService() => _instance;
  ApiService._internal();

  String? _token;

  /// Initialize service and load token
  Future<void> init() async {
    final prefs = await SharedPreferences.getInstance();
    _token = prefs.getString(AppConstants.tokenKey);
  }

  /// Save token to local storage
  Future<void> saveToken(String token) async {
    _token = token;
    final prefs = await SharedPreferences.getInstance();
    await prefs.setString(AppConstants.tokenKey, token);
  }

  /// Clear token
  Future<void> clearToken() async {
    _token = null;
    final prefs = await SharedPreferences.getInstance();
    await prefs.remove(AppConstants.tokenKey);
  }

  /// Get headers with authorization
  Map<String, String> _getHeaders() {
    final headers = <String, String>{
      'Content-Type': 'application/json',
    };
    if (_token != null && _token!.isNotEmpty) {
      headers['Authorization'] = 'Bearer $_token';
    }
    return headers;
  }

  /// Handle HTTP response
  dynamic _handleResponse(http.Response response) {
    if (response.statusCode == 200) {
      return json.decode(utf8.decode(response.bodyBytes));
    } else if (response.statusCode == 401) {
      throw Exception('Unauthorized - Please login again');
    } else {
      throw Exception('Request failed: ${response.statusCode}');
    }
  }

  /// Login
  Future<String> login(String username, String password) async {
    final response = await http.post(
      Uri.parse(ApiConstants.url(ApiConstants.login)),
      headers: {'Content-Type': 'application/json'},
      body: json.encode({
        'userName': username,
        'password': password,
      }),
    );

    final jsonData = _handleResponse(response);
    final result = ApiResult<String>.fromJson(
      jsonData,
      (data) => data.toString(),
    );

    if (result.isSuccess && result.data != null) {
      await saveToken(result.data!);
      return result.data!;
    } else {
      throw Exception(result.msg);
    }
  }

  /// Get all products
  Future<List<Product>> getProducts() async {
    final response = await http.get(
      Uri.parse(ApiConstants.url(ApiConstants.productList)),
      headers: _getHeaders(),
    );

    final jsonData = _handleResponse(response);
    final result = ApiResult<List<Product>>.fromJson(
      jsonData,
      (data) => (data as List).map((item) => Product.fromJson(item)).toList(),
    );

    if (result.isSuccess && result.data != null) {
      return result.data!;
    } else {
      throw Exception(result.msg);
    }
  }

  /// Get all art categories
  Future<List<ArtCategory>> getCategories() async {
    final response = await http.get(
      Uri.parse(ApiConstants.url(ApiConstants.categoryList)),
      headers: _getHeaders(),
    );

    final jsonData = _handleResponse(response);
    final result = ApiResult<List<ArtCategory>>.fromJson(
      jsonData,
      (data) => (data as List).map((item) => ArtCategory.fromJson(item)).toList(),
    );

    if (result.isSuccess && result.data != null) {
      return result.data!;
    } else {
      throw Exception(result.msg);
    }
  }

  /// Get all courses
  Future<List<Course>> getCourses() async {
    final response = await http.get(
      Uri.parse(ApiConstants.url(ApiConstants.courseList)),
      headers: _getHeaders(),
    );

    final jsonData = _handleResponse(response);
    final result = ApiResult<List<Course>>.fromJson(
      jsonData,
      (data) => (data as List).map((item) => Course.fromJson(item)).toList(),
    );

    if (result.isSuccess && result.data != null) {
      return result.data!;
    } else {
      throw Exception(result.msg);
    }
  }

  /// Get all instructors
  Future<List<Instructor>> getInstructors() async {
    final response = await http.get(
      Uri.parse(ApiConstants.url(ApiConstants.instructorList)),
      headers: _getHeaders(),
    );

    final jsonData = _handleResponse(response);
    final result = ApiResult<List<Instructor>>.fromJson(
      jsonData,
      (data) => (data as List).map((item) => Instructor.fromJson(item)).toList(),
    );

    if (result.isSuccess && result.data != null) {
      return result.data!;
    } else {
      throw Exception(result.msg);
    }
  }

  /// Get course by ID
  Future<Course> getCourse(int courseId) async {
    final response = await http.get(
      Uri.parse('${ApiConstants.url(ApiConstants.course)}?courseId=$courseId'),
      headers: _getHeaders(),
    );

    final jsonData = _handleResponse(response);
    final result = ApiResult<Course>.fromJson(
      jsonData,
      (data) => Course.fromJson(data),
    );

    if (result.isSuccess && result.data != null) {
      return result.data!;
    } else {
      throw Exception(result.msg);
    }
  }

  /// Get instructor by ID
  Future<Instructor> getInstructor(int instructorId) async {
    final response = await http.get(
      Uri.parse('${ApiConstants.url(ApiConstants.instructor)}?instructorId=$instructorId'),
      headers: _getHeaders(),
    );

    final jsonData = _handleResponse(response);
    final result = ApiResult<Instructor>.fromJson(
      jsonData,
      (data) => Instructor.fromJson(data),
    );

    if (result.isSuccess && result.data != null) {
      return result.data!;
    } else {
      throw Exception(result.msg);
    }
  }
}

import 'package:flutter/foundation.dart';
import 'package:shared_preferences/shared_preferences.dart';
import '../utils/constants.dart';
import 'api_service.dart';

/// Authentication state management
class AuthService extends ChangeNotifier {
  final ApiService _apiService = ApiService();
  bool _isLoggedIn = false;
  String? _token;

  bool get isLoggedIn => _isLoggedIn;
  String? get token => _token;

  /// Initialize auth state
  Future<void> init() async {
    await _apiService.init();
    final prefs = await SharedPreferences.getInstance();
    _token = prefs.getString(AppConstants.tokenKey);
    _isLoggedIn = _token != null && _token!.isNotEmpty;
    notifyListeners();
  }

  /// Login
  Future<void> login(String username, String password) async {
    try {
      final token = await _apiService.login(username, password);
      _token = token;
      _isLoggedIn = true;
      notifyListeners();
    } catch (e) {
      rethrow;
    }
  }

  /// Logout
  Future<void> logout() async {
    await _apiService.clearToken();
    _token = null;
    _isLoggedIn = false;
    notifyListeners();
  }
}

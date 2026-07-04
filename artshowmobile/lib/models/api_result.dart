/// API Result wrapper matching backend Result\<T\> structure
class ApiResult<T> {
  final int code;
  final String msg;
  final T? data;

  ApiResult({
    required this.code,
    required this.msg,
    this.data,
  });

  bool get isSuccess => code == 200;

  factory ApiResult.fromJson(
    Map<String, dynamic> json,
    T Function(dynamic)? fromJsonT,
  ) {
    return ApiResult<T>(
      code: json['code'] ?? 0,
      msg: json['msg'] ?? '',
      data: json['data'] != null && fromJsonT != null
          ? fromJsonT(json['data'])
          : json['data'],
    );
  }
}

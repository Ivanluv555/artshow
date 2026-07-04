/// Art Category Model
class ArtCategory {
  final int categoryId;
  final String categoryName;
  final String? iconUrl;
  final DateTime? createdAt;

  ArtCategory({
    required this.categoryId,
    required this.categoryName,
    this.iconUrl,
    this.createdAt,
  });

  factory ArtCategory.fromJson(Map<String, dynamic> json) {
    return ArtCategory(
      categoryId: json['categoryId'] ?? 0,
      categoryName: json['categoryName'] ?? '',
      iconUrl: json['iconUrl'],
      createdAt: json['createdAt'] != null
          ? DateTime.tryParse(json['createdAt'])
          : null,
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'categoryId': categoryId,
      'categoryName': categoryName,
      'iconUrl': iconUrl,
      'createdAt': createdAt?.toIso8601String(),
    };
  }
}

/// Course Model
class Course {
  final int courseId;
  final int? instructorId;
  final String title;
  final String? coverImageUrl;
  final double? price;
  final String? type;
  final int? studentCount;
  final String? description;
  final DateTime? createdAt;
  final DateTime? updatedAt;

  Course({
    required this.courseId,
    this.instructorId,
    required this.title,
    this.coverImageUrl,
    this.price,
    this.type,
    this.studentCount,
    this.description,
    this.createdAt,
    this.updatedAt,
  });

  factory Course.fromJson(Map<String, dynamic> json) {
    return Course(
      courseId: json['courseId'] ?? 0,
      instructorId: json['instructorId'],
      title: json['title'] ?? '',
      coverImageUrl: json['coverImageUrl'],
      price: json['price'] != null ? (json['price'] as num).toDouble() : null,
      type: json['type'],
      studentCount: json['studentCount'],
      description: json['description'],
      createdAt: json['createdAt'] != null
          ? DateTime.tryParse(json['createdAt'])
          : null,
      updatedAt: json['updatedAt'] != null
          ? DateTime.tryParse(json['updatedAt'])
          : null,
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'courseId': courseId,
      'instructorId': instructorId,
      'title': title,
      'coverImageUrl': coverImageUrl,
      'price': price,
      'type': type,
      'studentCount': studentCount,
      'description': description,
      'createdAt': createdAt?.toIso8601String(),
      'updatedAt': updatedAt?.toIso8601String(),
    };
  }
}

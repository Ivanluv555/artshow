/// Instructor Model
class Instructor {
  final int id;
  final String name;
  final String? title;
  final String? avatarUrl;
  final String? bio;
  final DateTime? createdAt;

  Instructor({
    required this.id,
    required this.name,
    this.title,
    this.avatarUrl,
    this.bio,
    this.createdAt,
  });

  factory Instructor.fromJson(Map<String, dynamic> json) {
    return Instructor(
      id: json['id'] ?? 0,
      name: json['name'] ?? '',
      title: json['title'],
      avatarUrl: json['avatarUrl'],
      bio: json['bio'],
      createdAt: json['createdAt'] != null
          ? DateTime.tryParse(json['createdAt'])
          : null,
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'id': id,
      'name': name,
      'title': title,
      'avatarUrl': avatarUrl,
      'bio': bio,
      'createdAt': createdAt?.toIso8601String(),
    };
  }
}

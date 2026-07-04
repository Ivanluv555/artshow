/// Product Model
class Product {
  final int id;
  final int? sellerId;
  final String name;
  final double price;
  final int stock;
  final String? imageUrl;
  final String? description;
  final bool? isCertified;
  final DateTime? createdAt;
  final DateTime? updatedAt;

  Product({
    required this.id,
    this.sellerId,
    required this.name,
    required this.price,
    required this.stock,
    this.imageUrl,
    this.description,
    this.isCertified,
    this.createdAt,
    this.updatedAt,
  });

  factory Product.fromJson(Map<String, dynamic> json) {
    return Product(
      id: json['id'] ?? 0,
      sellerId: json['sellerId'],
      name: json['name'] ?? '',
      price: (json['price'] ?? 0).toDouble(),
      stock: json['stock'] ?? 0,
      imageUrl: json['imageUrl'],
      description: json['description'],
      isCertified: json['isCertified'],
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
      'id': id,
      'sellerId': sellerId,
      'name': name,
      'price': price,
      'stock': stock,
      'imageUrl': imageUrl,
      'description': description,
      'isCertified': isCertified,
      'createdAt': createdAt?.toIso8601String(),
      'updatedAt': updatedAt?.toIso8601String(),
    };
  }
}

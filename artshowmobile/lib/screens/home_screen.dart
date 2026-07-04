import 'package:flutter/material.dart';
import 'package:cached_network_image/cached_network_image.dart';
import 'package:carousel_slider/carousel_slider.dart';
import '../services/api_service.dart';
import '../models/product.dart';
import '../models/art_category.dart';
import '../widgets/product_card.dart';
import '../widgets/category_chip.dart';
import '../utils/constants.dart';
import 'courses_screen.dart';
import 'instructors_screen.dart';
import 'login_screen.dart';

class HomeScreen extends StatefulWidget {
  const HomeScreen({super.key});

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  final ApiService _apiService = ApiService();
  List<Product> _products = [];
  List<ArtCategory> _categories = [];
  bool _isLoading = true;
  String? _error;
  int _selectedCategoryIndex = -1; // -1 means "All"

  @override
  void initState() {
    super.initState();
    _loadData();
  }

  Future<void> _loadData() async {
    setState(() {
      _isLoading = true;
      _error = null;
    });

    try {
      final products = await _apiService.getProducts();
      final categories = await _apiService.getCategories();

      setState(() {
        _products = products;
        _categories = categories;
        _isLoading = false;
      });
    } catch (e) {
      setState(() {
        _error = e.toString();
        _isLoading = false;
      });
    }
  }

  List<Product> get _filteredProducts {
    if (_selectedCategoryIndex == -1) {
      return _products;
    }
    // Note: In a real app, you'd need to add categoryId to Product model
    // For now, return all products
    return _products;
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text(AppConstants.appName),
        backgroundColor: Colors.purple.shade600,
        foregroundColor: Colors.white,
        actions: [
          IconButton(
            icon: const Icon(Icons.search),
            onPressed: () {
              // TODO: Implement search
            },
          ),
          IconButton(
            icon: const Icon(Icons.shopping_cart),
            onPressed: () {
              // TODO: Implement cart
            },
          ),
        ],
      ),
      drawer: Drawer(
        child: ListView(
          padding: EdgeInsets.zero,
          children: [
            DrawerHeader(
              decoration: BoxDecoration(
                gradient: LinearGradient(
                  colors: [Colors.purple.shade600, Colors.blue.shade600],
                ),
              ),
              child: const Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                mainAxisAlignment: MainAxisAlignment.end,
                children: [
                  Icon(Icons.palette, size: 48, color: Colors.white),
                  SizedBox(height: 8),
                  Text(
                    AppConstants.appName,
                    style: TextStyle(
                      color: Colors.white,
                      fontSize: 24,
                      fontWeight: FontWeight.bold,
                    ),
                  ),
                ],
              ),
            ),
            ListTile(
              leading: const Icon(Icons.home),
              title: const Text('Home'),
              onTap: () {
                Navigator.pop(context);
              },
            ),
            ListTile(
              leading: const Icon(Icons.school),
              title: const Text('Courses'),
              onTap: () {
                Navigator.pop(context);
                Navigator.push(
                  context,
                  MaterialPageRoute(builder: (_) => const CoursesScreen()),
                );
              },
            ),
            ListTile(
              leading: const Icon(Icons.person),
              title: const Text('Instructors'),
              onTap: () {
                Navigator.pop(context);
                Navigator.push(
                  context,
                  MaterialPageRoute(builder: (_) => const InstructorsScreen()),
                );
              },
            ),
            const Divider(),
            ListTile(
              leading: const Icon(Icons.logout),
              title: const Text('Logout'),
              onTap: () {
                Navigator.pop(context);
                Navigator.pushReplacement(
                  context,
                  MaterialPageRoute(builder: (_) => const LoginScreen()),
                );
              },
            ),
          ],
        ),
      ),
      body: RefreshIndicator(
        onRefresh: _loadData,
        child: _isLoading
            ? const Center(child: CircularProgressIndicator())
            : _error != null
                ? Center(
                    child: Column(
                      mainAxisAlignment: MainAxisAlignment.center,
                      children: [
                        const Icon(Icons.error_outline, size: 48, color: Colors.red),
                        const SizedBox(height: 16),
                        Text('Error: $_error'),
                        const SizedBox(height: 16),
                        ElevatedButton(
                          onPressed: _loadData,
                          child: const Text('Retry'),
                        ),
                      ],
                    ),
                  )
                : CustomScrollView(
                    slivers: [
                      // Banner Carousel
                      SliverToBoxAdapter(
                        child: _buildBannerCarousel(),
                      ),

                      // Categories Section
                      SliverToBoxAdapter(
                        child: Padding(
                          padding: const EdgeInsets.all(UIConstants.paddingMedium),
                          child: Column(
                            crossAxisAlignment: CrossAxisAlignment.start,
                            children: [
                              Text(
                                'Art Categories',
                                style: Theme.of(context).textTheme.titleLarge?.copyWith(
                                      fontWeight: FontWeight.bold,
                                    ),
                              ),
                              const SizedBox(height: UIConstants.paddingSmall),
                              _buildCategoryChips(),
                            ],
                          ),
                        ),
                      ),

                      // Products Section
                      SliverPadding(
                        padding: const EdgeInsets.symmetric(
                          horizontal: UIConstants.paddingMedium,
                        ),
                        sliver: SliverToBoxAdapter(
                          child: Text(
                            'Art Products',
                            style: Theme.of(context).textTheme.titleLarge?.copyWith(
                                  fontWeight: FontWeight.bold,
                                ),
                          ),
                        ),
                      ),

                      // Product Grid
                      SliverPadding(
                        padding: const EdgeInsets.all(UIConstants.paddingMedium),
                        sliver: _filteredProducts.isEmpty
                            ? const SliverToBoxAdapter(
                                child: Center(
                                  child: Text('No products available'),
                                ),
                              )
                            : SliverGrid(
                                gridDelegate: const SliverGridDelegateWithFixedCrossAxisCount(
                                  crossAxisCount: 2,
                                  childAspectRatio: 0.75,
                                  crossAxisSpacing: UIConstants.paddingMedium,
                                  mainAxisSpacing: UIConstants.paddingMedium,
                                ),
                                delegate: SliverChildBuilderDelegate(
                                  (context, index) {
                                    return ProductCard(product: _filteredProducts[index]);
                                  },
                                  childCount: _filteredProducts.length,
                                ),
                              ),
                      ),
                    ],
                  ),
      ),
    );
  }

  Widget _buildBannerCarousel() {
    final banners = [
      {
        'image': 'https://via.placeholder.com/800x300/9C27B0/FFFFFF?text=Art+Exhibition',
        'title': 'Explore Art',
      },
      {
        'image': 'https://via.placeholder.com/800x300/3F51B5/FFFFFF?text=Online+Courses',
        'title': 'Learn Art',
      },
      {
        'image': 'https://via.placeholder.com/800x300/E91E63/FFFFFF?text=Art+Community',
        'title': 'Join Community',
      },
    ];

    return CarouselSlider(
      options: CarouselOptions(
        height: 200,
        autoPlay: true,
        enlargeCenterPage: true,
        viewportFraction: 0.9,
      ),
      items: banners.map((banner) {
        return Builder(
          builder: (BuildContext context) {
            return Container(
              width: MediaQuery.of(context).size.width,
              margin: const EdgeInsets.symmetric(vertical: 10),
              decoration: BoxDecoration(
                borderRadius: BorderRadius.circular(UIConstants.radiusMedium),
                boxShadow: [
                  BoxShadow(
                    color: Colors.black.withValues(alpha: 0.2),
                    blurRadius: 8,
                    offset: const Offset(0, 4),
                  ),
                ],
              ),
              child: ClipRRect(
                borderRadius: BorderRadius.circular(UIConstants.radiusMedium),
                child: CachedNetworkImage(
                  imageUrl: banner['image']!,
                  fit: BoxFit.cover,
                  placeholder: (context, url) => const Center(
                    child: CircularProgressIndicator(),
                  ),
                  errorWidget: (context, url, error) => Container(
                    color: Colors.grey.shade300,
                    child: const Icon(Icons.error),
                  ),
                ),
              ),
            );
          },
        );
      }).toList(),
    );
  }

  Widget _buildCategoryChips() {
    return SingleChildScrollView(
      scrollDirection: Axis.horizontal,
      child: Row(
        children: [
          // "All" chip
          Padding(
            padding: const EdgeInsets.only(right: 8),
            child: CategoryChip(
              label: 'All',
              isSelected: _selectedCategoryIndex == -1,
              onTap: () {
                setState(() {
                  _selectedCategoryIndex = -1;
                });
              },
            ),
          ),
          // Category chips
          ..._categories.asMap().entries.map((entry) {
            final index = entry.key;
            final category = entry.value;
            return Padding(
              padding: const EdgeInsets.only(right: 8),
              child: CategoryChip(
                label: category.categoryName,
                isSelected: _selectedCategoryIndex == index,
                onTap: () {
                  setState(() {
                    _selectedCategoryIndex = index;
                  });
                },
              ),
            );
          }),
        ],
      ),
    );
  }
}

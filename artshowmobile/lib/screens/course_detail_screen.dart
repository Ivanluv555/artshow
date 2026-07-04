import 'package:flutter/material.dart';
import 'package:cached_network_image/cached_network_image.dart';
import '../models/course.dart';
import '../utils/constants.dart';

class CourseDetailScreen extends StatelessWidget {
  final Course course;

  const CourseDetailScreen({super.key, required this.course});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: CustomScrollView(
        slivers: [
          // App Bar with cover image
          SliverAppBar(
            expandedHeight: 250,
            pinned: true,
            backgroundColor: Colors.purple.shade600,
            flexibleSpace: FlexibleSpaceBar(
              title: Text(
                course.title,
                style: const TextStyle(
                  color: Colors.white,
                  fontWeight: FontWeight.bold,
                  shadows: [
                    Shadow(
                      color: Colors.black45,
                      blurRadius: 4,
                    ),
                  ],
                ),
              ),
              background: course.coverImageUrl != null
                  ? CachedNetworkImage(
                      imageUrl: course.coverImageUrl!,
                      fit: BoxFit.cover,
                      placeholder: (context, url) => Container(
                        color: Colors.grey.shade300,
                        child: const Center(child: CircularProgressIndicator()),
                      ),
                      errorWidget: (context, url, error) => Container(
                        color: Colors.grey.shade300,
                        child: const Icon(Icons.school, size: 64),
                      ),
                    )
                  : Container(
                      color: Colors.purple.shade300,
                      child: const Icon(Icons.school, size: 64, color: Colors.white),
                    ),
            ),
          ),

          // Course details
          SliverToBoxAdapter(
            child: Padding(
              padding: const EdgeInsets.all(UIConstants.paddingLarge),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  // Price and students
                  Row(
                    mainAxisAlignment: MainAxisAlignment.spaceBetween,
                    children: [
                      if (course.price != null)
                        Text(
                          '\$${course.price!.toStringAsFixed(2)}',
                          style: Theme.of(context).textTheme.headlineSmall?.copyWith(
                                color: Colors.purple.shade700,
                                fontWeight: FontWeight.bold,
                              ),
                        ),
                      Row(
                        children: [
                          const Icon(Icons.people, size: 20, color: Colors.grey),
                          const SizedBox(width: 4),
                          Text(
                            '${course.studentCount ?? 0} students',
                            style: Theme.of(context).textTheme.bodyMedium,
                          ),
                        ],
                      ),
                    ],
                  ),

                  const SizedBox(height: UIConstants.paddingMedium),

                  // Type badge
                  if (course.type != null)
                    Chip(
                      label: Text(course.type!),
                      backgroundColor: Colors.blue.shade100,
                    ),

                  const SizedBox(height: UIConstants.paddingLarge),

                  // Description
                  Text(
                    'About this course',
                    style: Theme.of(context).textTheme.titleLarge?.copyWith(
                          fontWeight: FontWeight.bold,
                        ),
                  ),
                  const SizedBox(height: UIConstants.paddingSmall),
                  Text(
                    course.description ?? 'No description available',
                    style: Theme.of(context).textTheme.bodyLarge,
                  ),

                  const SizedBox(height: UIConstants.paddingLarge),

                  // Enroll button
                  SizedBox(
                    width: double.infinity,
                    height: 50,
                    child: ElevatedButton(
                      onPressed: () {
                        ScaffoldMessenger.of(context).showSnackBar(
                          const SnackBar(
                            content: Text('Enrollment feature coming soon'),
                          ),
                        );
                      },
                      style: ElevatedButton.styleFrom(
                        backgroundColor: Colors.purple.shade600,
                        foregroundColor: Colors.white,
                        shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(UIConstants.radiusMedium),
                        ),
                      ),
                      child: const Text(
                        'Enroll Now',
                        style: TextStyle(
                          fontSize: 16,
                          fontWeight: FontWeight.bold,
                        ),
                      ),
                    ),
                  ),
                ],
              ),
            ),
          ),
        ],
      ),
    );
  }
}

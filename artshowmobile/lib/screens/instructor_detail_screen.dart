import 'package:flutter/material.dart';
import 'package:cached_network_image/cached_network_image.dart';
import '../models/instructor.dart';
import '../utils/constants.dart';

class InstructorDetailScreen extends StatelessWidget {
  final Instructor instructor;

  const InstructorDetailScreen({super.key, required this.instructor});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(instructor.name),
        backgroundColor: Colors.purple.shade600,
        foregroundColor: Colors.white,
      ),
      body: SingleChildScrollView(
        child: Column(
          children: [
            // Header with avatar
            Container(
              width: double.infinity,
              padding: const EdgeInsets.all(UIConstants.paddingLarge),
              decoration: BoxDecoration(
                gradient: LinearGradient(
                  begin: Alignment.topLeft,
                  end: Alignment.bottomRight,
                  colors: [Colors.purple.shade400, Colors.blue.shade600],
                ),
              ),
              child: Column(
                children: [
                  // Avatar
                  CircleAvatar(
                    radius: 60,
                    backgroundColor: Colors.white,
                    child: instructor.avatarUrl != null
                        ? ClipOval(
                            child: CachedNetworkImage(
                              imageUrl: instructor.avatarUrl!,
                              width: 120,
                              height: 120,
                              fit: BoxFit.cover,
                              placeholder: (context, url) => const CircularProgressIndicator(),
                              errorWidget: (context, url, error) => const Icon(
                                Icons.person,
                                size: 60,
                                color: Colors.grey,
                              ),
                            ),
                          )
                        : const Icon(
                            Icons.person,
                            size: 60,
                            color: Colors.grey,
                          ),
                  ),
                  const SizedBox(height: UIConstants.paddingMedium),

                  // Name
                  Text(
                    instructor.name,
                    style: Theme.of(context).textTheme.headlineSmall?.copyWith(
                          color: Colors.white,
                          fontWeight: FontWeight.bold,
                        ),
                  ),

                  // Title
                  if (instructor.title != null) ...[
                    const SizedBox(height: UIConstants.paddingSmall),
                    Text(
                      instructor.title!,
                      style: Theme.of(context).textTheme.titleMedium?.copyWith(
                            color: Colors.white.withValues(alpha: 0.9),
                          ),
                    ),
                  ],
                ],
              ),
            ),

            // Bio section
            Padding(
              padding: const EdgeInsets.all(UIConstants.paddingLarge),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text(
                    'Biography',
                    style: Theme.of(context).textTheme.titleLarge?.copyWith(
                          fontWeight: FontWeight.bold,
                        ),
                  ),
                  const SizedBox(height: UIConstants.paddingSmall),
                  Text(
                    instructor.bio ?? 'No biography available',
                    style: Theme.of(context).textTheme.bodyLarge,
                  ),

                  const SizedBox(height: UIConstants.paddingLarge),

                  // Placeholder for courses section
                  Text(
                    'Courses by ${instructor.name}',
                    style: Theme.of(context).textTheme.titleLarge?.copyWith(
                          fontWeight: FontWeight.bold,
                        ),
                  ),
                  const SizedBox(height: UIConstants.paddingMedium),
                  const Card(
                    child: Padding(
                      padding: EdgeInsets.all(UIConstants.paddingMedium),
                      child: Center(
                        child: Text('Course list coming soon'),
                      ),
                    ),
                  ),
                ],
              ),
            ),
          ],
        ),
      ),
    );
  }
}

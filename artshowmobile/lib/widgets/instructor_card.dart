import 'package:flutter/material.dart';
import 'package:cached_network_image/cached_network_image.dart';
import '../models/instructor.dart';
import '../utils/constants.dart';

class InstructorCard extends StatelessWidget {
  final Instructor instructor;
  final VoidCallback onTap;

  const InstructorCard({
    super.key,
    required this.instructor,
    required this.onTap,
  });

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: onTap,
      child: Card(
        elevation: 2,
        shape: RoundedRectangleBorder(
          borderRadius: BorderRadius.circular(UIConstants.radiusMedium),
        ),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            const SizedBox(height: UIConstants.paddingMedium),

            // Avatar
            CircleAvatar(
              radius: 40,
              backgroundColor: Colors.grey.shade200,
              child: instructor.avatarUrl != null
                  ? ClipOval(
                      child: CachedNetworkImage(
                        imageUrl: instructor.avatarUrl!,
                        width: 80,
                        height: 80,
                        fit: BoxFit.cover,
                        placeholder: (context, url) => const CircularProgressIndicator(),
                        errorWidget: (context, url, error) => const Icon(
                          Icons.person,
                          size: 40,
                          color: Colors.grey,
                        ),
                      ),
                    )
                  : const Icon(
                      Icons.person,
                      size: 40,
                      color: Colors.grey,
                    ),
            ),

            const SizedBox(height: UIConstants.paddingSmall),

            // Name
            Padding(
              padding: const EdgeInsets.symmetric(horizontal: UIConstants.paddingSmall),
              child: Text(
                instructor.name,
                style: const TextStyle(
                  fontWeight: FontWeight.bold,
                  fontSize: 16,
                ),
                maxLines: 1,
                overflow: TextOverflow.ellipsis,
                textAlign: TextAlign.center,
              ),
            ),

            // Title
            if (instructor.title != null)
              Padding(
                padding: const EdgeInsets.symmetric(horizontal: UIConstants.paddingSmall),
                child: Text(
                  instructor.title!,
                  style: TextStyle(
                    color: Colors.grey.shade600,
                    fontSize: 12,
                  ),
                  maxLines: 2,
                  overflow: TextOverflow.ellipsis,
                  textAlign: TextAlign.center,
                ),
              ),

            const SizedBox(height: UIConstants.paddingMedium),
          ],
        ),
      ),
    );
  }
}

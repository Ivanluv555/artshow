import 'package:flutter/material.dart';
import '../services/api_service.dart';
import '../models/instructor.dart';
import '../utils/constants.dart';
import '../widgets/instructor_card.dart';
import 'instructor_detail_screen.dart';

class InstructorsScreen extends StatefulWidget {
  const InstructorsScreen({super.key});

  @override
  State<InstructorsScreen> createState() => _InstructorsScreenState();
}

class _InstructorsScreenState extends State<InstructorsScreen> {
  final ApiService _apiService = ApiService();
  List<Instructor> _instructors = [];
  bool _isLoading = true;
  String? _error;

  @override
  void initState() {
    super.initState();
    _loadInstructors();
  }

  Future<void> _loadInstructors() async {
    setState(() {
      _isLoading = true;
      _error = null;
    });

    try {
      final instructors = await _apiService.getInstructors();
      setState(() {
        _instructors = instructors;
        _isLoading = false;
      });
    } catch (e) {
      setState(() {
        _error = e.toString();
        _isLoading = false;
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Instructors'),
        backgroundColor: Colors.purple.shade600,
        foregroundColor: Colors.white,
      ),
      body: RefreshIndicator(
        onRefresh: _loadInstructors,
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
                          onPressed: _loadInstructors,
                          child: const Text('Retry'),
                        ),
                      ],
                    ),
                  )
                : _instructors.isEmpty
                    ? const Center(child: Text('No instructors available'))
                    : GridView.builder(
                        padding: const EdgeInsets.all(UIConstants.paddingMedium),
                        gridDelegate: const SliverGridDelegateWithFixedCrossAxisCount(
                          crossAxisCount: 2,
                          childAspectRatio: 0.8,
                          crossAxisSpacing: UIConstants.paddingMedium,
                          mainAxisSpacing: UIConstants.paddingMedium,
                        ),
                        itemCount: _instructors.length,
                        itemBuilder: (context, index) {
                          final instructor = _instructors[index];
                          return InstructorCard(
                            instructor: instructor,
                            onTap: () {
                              Navigator.push(
                                context,
                                MaterialPageRoute(
                                  builder: (_) => InstructorDetailScreen(
                                    instructor: instructor,
                                  ),
                                ),
                              );
                            },
                          );
                        },
                      ),
      ),
    );
  }
}

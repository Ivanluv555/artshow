import 'package:flutter_test/flutter_test.dart';
import 'package:artshowmobile/main.dart';

void main() {
  testWidgets('App smoke test', (WidgetTester tester) async {
    // Build our app and trigger a frame.
    await tester.pumpWidget(const ArtShowApp());

    // Verify that splash screen appears
    expect(find.text('ArtShow'), findsOneWidget);
  });
}

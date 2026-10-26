# FarmHub Frontend

Flutter and Dart client. Begin with Flutter Web in Chrome; Android support can be enabled later.

## Prerequisites

- Flutter SDK
- Dart (included with Flutter)
- Google Chrome
- VS Code with Flutter and Dart extensions

## Complete the Flutter web scaffold

From the repository root:

```powershell
cd frontend
flutter create --platforms=web .
flutter pub get
```

## Configure Firebase

1. Open the Firebase project and enable **Authentication → Sign-in method → Email/Password**.
2. Create a Cloud Firestore database.
3. Install/configure FlutterFire:

```powershell
dart pub global activate flutterfire_cli
dart pub global run flutterfire_cli:flutterfire configure --project=farmhub-4042c --platforms=web
```

4. Select the Firebase project and register the Web app if prompted. The command generates `lib/firebase_options.dart`.
5. Keep `lib/firebase_options.dart` in Git so other developers can build the web app. It contains client configuration, not a Firebase Admin service-account private key. Never commit Admin SDK private keys.
6. Initialize Firebase in `lib/main.dart`:

```dart
import 'firebase_options.dart';

await Firebase.initializeApp(
  options: DefaultFirebaseOptions.currentPlatform,
);
```

## Run in Chrome

From `frontend/` run:

```powershell
flutter pub get
flutter run -d chrome --dart-define=FARMHUB_API=http://localhost:8080/api
```

Firebase Authentication must be enabled and configured before login/registration can work. The backend must also be running for API features.

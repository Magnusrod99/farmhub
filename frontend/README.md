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

1. Create a Firebase project in the Firebase Console.
2. Enable **Authentication → Sign-in method → Email/Password**.
3. Create a Cloud Firestore database.
4. Install/configure FlutterFire:

```powershell
dart pub global activate flutterfire_cli
flutterfire configure
```

5. Select the Firebase project and register the Web app.
6. The command generates `lib/firebase_options.dart`. This generated file is intentionally ignored by Git.
7. In `lib/main.dart`, add:

```dart
import 'firebase_options.dart';
```

8. Replace `await Firebase.initializeApp();` with:

```dart
await Firebase.initializeApp(
  options: DefaultFirebaseOptions.currentPlatform,
);
```

## Run in Chrome

Start the backend separately, then from `frontend/` run:

```powershell
flutter run -d chrome --dart-define=FARMHUB_API=http://localhost:8080/api
```

Firebase Authentication must be configured before login/registration can work.

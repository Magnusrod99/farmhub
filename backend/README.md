# FarmHub Backend

Spring Boot REST API using Java 21, Maven, Firebase Admin SDK and Cloud Firestore.

## Prerequisites

- Java 21
- Maven 3.9 or newer
- A Firebase project with Cloud Firestore enabled

## Firebase service account

1. In Firebase Console, open **Project settings → Service accounts**.
2. Generate a private key and save the downloaded JSON outside this repository.
3. In PowerShell, set the environment variable to the JSON file:

```powershell
$env:GOOGLE_APPLICATION_CREDENTIALS = "C:\secure\farmhub-service-account.json"
```

Never commit the service-account JSON to GitHub.

## Run the API

From this directory:

```powershell
mvn spring-boot:run
```

Health check: http://localhost:8080/api/health

All other current API endpoints require a Firebase ID token in the Authorization bearer header.

## Important security limitation

This is an early development scaffold. The generic collection endpoints are not yet protected by role-based authorization, per-record ownership checks, or business-specific validation. Do not deploy this API publicly or use it with real users/data until those controls, Firestore security rules, and tests are implemented.

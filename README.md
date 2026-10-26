# FarmHub

FarmHub is a browser-based agricultural marketplace and equipment-rental platform.

## Technology stack

- **Frontend:** Flutter Web (Dart)
- **Backend:** Java 21, Spring Boot and Maven
- **Authentication:** Firebase Authentication (email and password)
- **Database:** Cloud Firestore
- **Image storage:** Firebase Storage
- **Version control:** Git and GitHub

## Repository structure

```
farmhub/
├── frontend/       # Flutter client
├── backend/        # Spring Boot REST API
├── firebase/       # Firebase setup notes
└── docs/           # Project scope and documentation
```

## Account types

- **Customer:** Buys agricultural products and rents equipment.
- **Equipment Owner & Seller:** One combined account for listing equipment and selling agricultural products.
- **Admin:** Separate protected account; public self-registration must not create admin accounts.

## Getting started

1. Install Flutter, Java 21, Maven, Git, VS Code and Google Chrome.
2. Follow [frontend setup](frontend/README.md) to create the Flutter Web scaffold and configure Firebase.
3. Follow [backend setup](backend/README.md) to configure the Firebase Admin SDK and run Spring Boot.
4. Read [Firebase setup notes](firebase/README.md) and [project scope](docs/PROJECT_SCOPE.md).

Start development with Flutter Web in Chrome. Android support can be enabled later.

## Security warning

The current code is an early development scaffold. Generic backend collection endpoints do not yet implement role-based authorization or per-record ownership checks. Do not deploy the API publicly or use real user data until authorization, validation, restrictive Firestore rules, and tests are complete.

Never commit Firebase service-account JSON files, passwords, signing keys, or local environment files. Keep Admin SDK credentials outside the repository and configure them through `GOOGLE_APPLICATION_CREDENTIALS`.

## Current status

Initial login/registration and dashboard shell, Firebase Admin token verification, Firestore service, and starter REST endpoints are provided. Ordering, rentals, payment/refund flows, stock reservation, return inspection, image uploads, seller/admin dashboards, and production security are planned implementation increments.

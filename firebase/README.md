# Firebase setup notes

FarmHub uses:

- Firebase Authentication (email and password)
- Cloud Firestore
- Firebase Storage (image upload integration is a later implementation step)

## Initial Firestore collections

- `users`
- `equipment`
- `bookings`
- `rentalAgreements`
- `products`
- `orders`
- `orderItems`

Firestore creates collections automatically when the first document is written; there is no need to pre-create empty collections.

## Deploy Firestore security rules

The current starter rules are in [firestore.rules](firestore.rules).

To publish them:

1. Open Firebase Console → Firestore Database → Rules.
2. Replace the editor contents with `firebase/firestore.rules`.
3. Click Publish.

These initial rules allow an authenticated customer to create and read their own `users/{uid}` profile and update only their name and phone. Public clients cannot set their own role to seller, owner or admin. All other collections are denied by default until module-specific access rules are designed and tested.

The Spring Boot Admin SDK uses server credentials and is not governed by client Firestore rules. Its REST API must therefore enforce its own role and ownership checks before it is used with real data or exposed publicly.

## Security

Do not commit Firebase Admin service-account keys or other private credentials. Backend credentials must be stored outside the repository and supplied using `GOOGLE_APPLICATION_CREDENTIALS`.

Firestore rules and backend authorization must be reviewed and tested before production use.

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

The schema and validation rules will be implemented as the project modules are developed.

## Security

Do not commit Firebase Admin service-account keys or other private credentials. Backend credentials must be stored outside the repository and supplied using `GOOGLE_APPLICATION_CREDENTIALS`.

Firestore security rules are not yet finalized. Do not use production data or expose the current backend publicly until role checks, ownership validation, and restrictive Firestore rules have been implemented and tested.

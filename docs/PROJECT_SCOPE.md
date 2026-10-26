# FarmHub — Initial Project Scope

## Platform and technology

- Browser-based application, with Flutter Web as the first development target.
- Flutter/Dart frontend.
- Java 21 and Spring Boot REST backend.
- Firebase Authentication with email and password.
- Cloud Firestore for application data.
- Firebase Storage for product/equipment images.
- GitHub for source control.

## Account types

1. **Customer:** Can buy agricultural products and rent equipment.
2. **Equipment Owner & Seller:** One combined account type that can list equipment and sell agricultural products.
3. **Admin:** Separate protected account, created and managed outside public self-registration.

A customer account may later be upgraded to the combined Equipment Owner & Seller account without losing customer history. Role changes must be authorized by the backend, not trusted from client input.

## Customer capabilities

- Register, log in, log out and manage profile.
- Browse, search and filter equipment by name, category, location, price and availability.
- View equipment details and request rentals for selected dates.
- Review and accept the rental agreement before booking confirmation.
- View booking status, active rentals and rental history.
- Request/mark equipment return and view inspection outcome.
- Browse, search and filter agricultural products.
- Manage cart, place orders and view order history/status.
- Submit return requests within the agreed return window.

## Equipment owner capabilities

- Create, edit, delete and view equipment listings.
- Set equipment details, location, rental price, availability, condition, deposit and rental terms.
- Review rental requests and accept or reject them after checking availability.
- View active rentals and rental history.
- Record return inspection, damage details and any security-deposit deduction.

## Agricultural product seller capabilities

- Create, edit, delete and view product listings.
- Maintain product images, descriptions, categories, prices and stock quantities.
- Manage orders and update delivery status.
- Review customer return requests.

## Product ordering rules agreed for the project

- Product orders are placed instantly when sufficient stock is available.
- Stock is reserved when an order is placed.
- Seller delivery is the selected fulfillment method; seller sets delivery charges.
- Delivery status is updated by the seller (no GPS tracking in the initial scope).
- If available stock is lower than requested quantity, the customer is asked to accept a partial order or cancel.
- Return requests are reviewed by the seller; the agreed return window is seven days from delivery.
- Refund method still needs a final decision.

## Rental rules agreed for the project

- Customer requests a date range; owner accepts or rejects after checking availability.
- Customer accepts the agreement containing rental terms, equipment condition, deposit and damage liability.
- Owner defines the deposit and remaining-rental-fee deadline.
- Deposit payment is due within 12 hours after agreement acceptance; an unpaid booking expires.
- Online and offline payment are supported. Offline deposit receipt must be acknowledged by the owner in the app.
- Customer cancellation at least 24 hours before start: full refund of rental advance paid.
- Customer cancellation less than 24 hours before start but before rental begins: 50% refund of rental advance paid.
- After rental starts, there is no automatic advance refund; exceptional cases may be reviewed.
- Owner cancellation: refund all booking payments, including security deposit; no additional compensation.
- Security deposit is optional and owner-defined. Owner records inspection and damage claim details before deciding the deduction; the dispute workflow remains to be implemented.

## Not yet implemented

The initial code is a technical scaffold, not a feature-complete system. Booking workflows, order workflows, payment integration, stock reservation transactions, date-overlap protection, server-side role authorization, record ownership checks, returns, refunds, image uploads, admin features, and production Firestore rules remain future implementation work.

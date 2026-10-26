import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/material.dart';

class HomeScreen extends StatelessWidget {
  const HomeScreen({super.key});

  @override
  Widget build(BuildContext context) {
    final user = FirebaseAuth.instance.currentUser;

    return Scaffold(
      appBar: AppBar(
        title: const Text('FarmHub'),
        actions: [
          IconButton(
            tooltip: 'Logout',
            onPressed: () => FirebaseAuth.instance.signOut(),
            icon: const Icon(Icons.logout),
          ),
        ],
      ),
      body: ListView(
        padding: const EdgeInsets.all(20),
        children: [
          Text(
            'Welcome, ${user?.displayName ?? user?.email ?? 'Farmer'}!',
            style: Theme.of(context).textTheme.headlineSmall?.copyWith(
                  fontWeight: FontWeight.bold,
                ),
          ),
          const SizedBox(height: 6),
          const Text(
            'Your agricultural marketplace and equipment rental companion.',
          ),
          const SizedBox(height: 24),
          _dashboardTile(
            context,
            Icons.shopping_basket,
            'Buy agricultural products',
            'Browse seeds, fertilizers and crop-care products',
            'Products module',
          ),
          _dashboardTile(
            context,
            Icons.agriculture,
            'Rent farm equipment',
            'Find equipment by category, location and price',
            'Equipment rental module',
          ),
          _dashboardTile(
            context,
            Icons.storefront,
            'Seller / Owner workspace',
            'Manage products, equipment and customer requests',
            'Seller dashboard',
          ),
          _dashboardTile(
            context,
            Icons.receipt_long,
            'My orders & bookings',
            'Track purchases, rentals and returns',
            'Customer dashboard',
          ),
          _dashboardTile(
            context,
            Icons.admin_panel_settings,
            'Admin',
            'Manage users, listings, bookings and orders',
            'Admin access is role-restricted in the backend',
          ),
        ],
      ),
    );
  }

  Widget _dashboardTile(
    BuildContext context,
    IconData icon,
    String title,
    String subtitle,
    String status,
  ) {
    return Card(
      child: ListTile(
        leading: CircleAvatar(child: Icon(icon)),
        title: Text(title),
        subtitle: Text(subtitle),
        trailing: const Icon(Icons.chevron_right),
        onTap: () {
          ScaffoldMessenger.of(context).showSnackBar(
            SnackBar(
              content: Text('$status — implementation module is next.'),
            ),
          );
        },
      ),
    );
  }
}

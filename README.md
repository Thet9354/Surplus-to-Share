# Surplus-to-Share

[![License](https://img.shields.io/badge/License-MIT-blue.svg)](https://opensource.org/licenses/MIT)

Surplus to Share is a Grocery Shopping App is an Android application that allows users to select shops, buy food items, add them to Firebase Realtime Database, view order history, locate shops on Google Maps, and share orders on social media platforms. It also includes a profile page with basic details.

## Features

- Splash page for an attractive app launch experience
- Fragment layout with a custom bottom navigation for easy navigation between app sections
- Select and buy food items from different shops
- Add orders to Firebase Realtime Database for easy order management
- View and track order history to keep track of past purchases
- Locate shops on Google Maps to find their physical locations
- Share orders through popular social media platforms such as WhatsApp, Facebook, and Instagram
- Profile page for users to manage basic details

## Screenshots

Include a demo vidoe to showcase the app's UI and functionality:

https://drive.google.com/file/d/18l2AGaJBZs1AvzTjWPuSsjq40qvRclKm/view?usp=sharing

## Technologies Used

- Android Studio
- Java
- Firebase Realtime Database
- Google Maps API
- Social media sharing APIs (e.g., WhatsApp, Facebook, Instagram)

## Getting Started

These instructions will help you get a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

- Android Studio (version X.X or higher)
- Android SDK
- Java Development Kit (JDK) 8 or higher
- Firebase account with Realtime Database enabled
- Google Maps API key

### Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/your-username/grocery-shopping-app.git
   ```

2. Open the project in Android Studio.

3. Configure Firebase Realtime Database:

   - Create a new Firebase project and enable Realtime Database.
   - Add the `google-services.json` file to the `app/` directory of the project.
   - Modify the Firebase configuration in the `build.gradle` file and `AndroidManifest.xml`.

4. Obtain a Google Maps API key:

   - Go to the Google Cloud Console and create a new project.
   - Enable the Maps SDK for Android and generate an API key.
   - Replace the API key in the `AndroidManifest.xml` file.

5. Build and run the app on an emulator or physical device.

## Usage

Describe how to use your app and provide any necessary instructions or guidelines. For example:

1. On the home screen, select a shop from the list.
2. Browse and add food items to your cart.
3. Proceed to checkout and complete the order.
4. View the order history to see previous purchases.
5. Navigate to the profile page to manage your basic details.


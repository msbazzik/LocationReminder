# Location Reminder

Location Reminder is an Android Kotlin app which represents a TODO list with location reminders that
remind the user to do something when the user is at a specific location. The app will require the
user to create an account and login to set and access reminders.

## Project Overview
The app implemented Login feature with FirebaseUI


## Using Location Reminder App

1. Clone the project to your local machine.
2. Open the project using Android Studio.

3. There are some dependencies that are needed to use the project, here are the step-by-step
   instructions to get a dev environment running:

   3.1. To enable Firebase Authentication:
    - Go to the authentication tab at the Firebase [console](https://console.firebase.google.com/)
      and enable Email/Password and Google Sign-in methods.
    - Download *google-services.json* and add it to the app.

   3.2. To enable [Google Maps](https://developers.google.com/maps/documentation):
    - Go to APIs & Services at the Google console.
    - Select your project and go to APIs & Credentials.
    - Create a new api key and restrict it for android apps.
    - Add your package name and SHA-1 signing-certificate fingerprint.
    - Enable Maps SDK for Android from API restrictions and Save.
    - Copy the api key to the *google_maps_api.xml*

4. Run the app on your mobile phone or emulator with Google Play Services in it.
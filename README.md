# Everything Calculator v1.6

A separate offline Android app for everyday, scientific, graphing, finance, trucking, conversion and advanced math calculations. It does not share an application ID or saved data with Mike's Math Coach.

Version 1.1 keeps the phone keyboard closed while using the built-in calculator keypad. Manual typing is available through an explicit Keyboard button, and keypad input now shows a live answer preview.

Version 1.2 fixes all keypad and navigation taps by making haptic feedback non-blocking and declaring Android's vibration permission.

Version 1.3 applies Android system-bar insets so the calculator header remains below the notification bar and the bottom navigation remains above gesture/system controls.

Version 1.4 replaces the ineffective WebView-padding workaround with Android window-level system-bar containment. The app targets Android 14 behavior to prevent Android 15/16 from forcing this sideloaded app edge-to-edge.

Version 1.5 uses explicit top and bottom system-bar margins while keeping the WebView full-width, fixing Samsung devices that reported an incorrect right-side inset.

Version 1.6 compacts the calculator display and action controls, removes duplicated bottom safe-area spacing, and keeps the large keypad buttons intact.

## Build

Open the folder in Android Studio and select **Build > Build APK(s)**. Minimum Android version: Android 6.0.

## Privacy

The app requests no internet, location, contacts, camera or storage permissions. Calculation history and settings remain on the device in WebView local storage.

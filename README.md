# MAD Lab Experiment 8: Menus and WebView in Android

## 1. Project Title and Experiment Number
**Experiment No.:** 8
**Title:** Implement Menus and WebView in an Android Application.

## 2. Experiment Aim
To understand and implement Android Menus (Options Menu) and an embedded `WebView` to display web content inside an Android application.

## 3. Problem Statement and Real-World Scenario
Develop an educational application, "Student Learning Hub," that provides students with an integrated dashboard. The app should allow navigation using Android's native Options Menu and seamlessly load external educational content within the application without forcing the user to open an external web browser.

## 4. Introduction
Android provides built-in support for different types of menus, with the **Options Menu** being the primary collection of menu items for an activity. To display web pages directly inside an application, Android provides the **WebView** component. This experiment combines both by allowing users to navigate between a native Android dashboard and embedded web content using menu options.

## 5. Objectives
- Implement a native Android Options Menu with multiple actionable items.
- Embed a `WebView` and handle page loading, progress indication, and error states.
- Configure safe navigation back through the WebView's history.
- Design a modern, responsive user interface using Material Design 3.

## 6. Concepts and Technologies Used
- **Language:** Kotlin
- **UI Framework:** XML Layouts (CoordinatorLayout, Material Components)
- **Android Menus:** `onCreateOptionsMenu` and `onOptionsItemSelected`
- **WebView API:** `WebViewClient`, `WebChromeClient`, History Navigation
- **Tooling:** Android Studio Ladybug, Gradle

## 7. Explanation of Android Options Menu
The Android Options Menu is a native UI component usually hosted in the app's `Toolbar` or `ActionBar`. It is defined in XML (`res/menu/`) and inflated dynamically in the Activity using `menuInflater.inflate()`. When a user selects an item, `onOptionsItemSelected()` captures the event using the item's unique ID, allowing the app to execute the corresponding logic natively (like opening dialogs or navigating).

## 8. Explanation of WebView and its Working
A `WebView` is an Android View that displays web pages. It uses the WebKit rendering engine to display HTML, CSS, and execute JavaScript. 
- `WebViewClient`: Handles page navigation, loading, and error callbacks natively. We specifically check `request?.isForMainFrame` to ensure we don't trigger offline errors for background trackers.
- `WebChromeClient`: Handles UI-related actions like the progress bar updates.
Unlike an external browser, the `WebView` acts as a component inside the app, allowing the developer to control the layout and interact with the content directly.

## 9. Application Features
- **Student Dashboard:** Displays student identity on an attractive Material Card.
- **Options Menu:** Features navigation to Home, Learning Content, Profile Dialog, About Dialog, and Exit.
- **Embedded Web Content:** Loads an educational website seamlessly inside the app.
- **Offline Fallback:** Displays a localized HTML page if the internet is disconnected.
- **Smart Back Navigation:** Back button gracefully traverses website history before exiting the `WebView`.

## 10. Prerequisites and Software Requirements
- Android Studio Ladybug (or higher)
- Android SDK (API 24 to API 37)
- Gradle Plugin 8+
- Active Internet Connection

## 11. Project Installation and Setup
1. Clone the repository to your local machine:
   ```bash
   git clone <repository-url>
   ```
2. Open the project folder `Exp8MenusandWebView` in Android Studio.
3. Allow Gradle to sync dependencies.

## 12. Steps to Build and Run the Application
1. Connect an Android device or start an Android Emulator.
2. Click the **Run** button (green play icon) in Android Studio.
3. Once built, the app will launch displaying the Student Dashboard.
4. Use the Options Menu in the top right to navigate.
5. Tap "Open Learning Content" to view the embedded `WebView`.

## 13. Project Folder and File Structure
```text
D:/MAD-Lab/Exp8MenusandWebView/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── AndroidManifest.xml
│   │   │   ├── java/com/example/exp8menusandwebview/
│   │   │   │   ├── MainActivity.kt
│   │   │   │   └── WebViewActivity.kt
│   │   │   ├── res/
│   │   │   │   ├── drawable/
│   │   │   │   ├── layout/
│   │   │   │   │   ├── activity_main.xml
│   │   │   │   │   └── activity_webview.xml
│   │   │   │   ├── menu/
│   │   │   │   │   └── main_menu.xml
│   │   │   │   ├── values/
│   │   │   │   │   ├── colors.xml
│   │   │   │   │   └── strings.xml
│   │   │   ├── assets/
│   │   │   │   └── error.html
├── build.gradle.kts
└── settings.gradle.kts
```

## 14. Important Files and Their Responsibilities
- **`MainActivity.kt`**: Initializes the main dashboard, inflates the Options Menu, and handles actions like opening Dialogs or the WebView screen.
- **`WebViewActivity.kt`**: Manages the `WebView`, configures `WebViewClient` for errors/loading, `WebChromeClient` for the progress bar, and handles the back-press logic for browser history.
- **`activity_main.xml`**: Uses Coordinator Layout and Material Cards to create a beautiful aesthetic UI.
- **`main_menu.xml`**: Defines the list of items inside the Android Options Menu.

## 15. Application Workflow
1. User opens the app → `MainActivity` launches.
2. Dashboard displays Name and USN.
3. User opens Options Menu → Selects "Student Profile" → Native `AlertDialog` shows details.
4. User selects "Open Learning Content" → `WebViewActivity` launches.
5. App attempts to fetch `https://developer.android.com/`. If successful, the page loads. If it fails, `error.html` loads from assets.

## 16. Test Cases and Evidence

### TC01 — Home Screen and Student Identity
**Objective:** Verify that the application launches correctly and displays the student information.
**Preconditions:** Application is installed.
**Steps:**
1. Launch the application.
2. Verify the home screen and navigation controls.
3. Verify that the student's full name and USN are visible.
**Expected Result:** The Home Screen renders with the student's name and USN properly formatted in a Material Card.
**Actual Result:** Passed. Name and USN are clearly visible on the dashboard.
**Screenshot:**
![Home Screen](screenshots/test-case-01-student-identity.png)

### TC02 — WebView Content Loading
**Objective:** Verify that WebView displays educational content.
**Preconditions:** Device has an internet connection.
**Steps:**
1. Click the "Open Learning Content" button.
2. Wait for the page to load.
**Expected Result:** The `WebView` successfully renders the website.
**Actual Result:** Passed. Website is rendered inside the app window.
**Screenshot:**
![WebView](screenshots/test-case-02-webview.png)

### TC03 — WebView Navigation Support
**Objective:** Verify that WebView supports internal link navigation.
**Preconditions:** Device has an internet connection, and WebView is open.
**Steps:**
1. Click on a link inside the WebView.
2. Verify that the page changes and the toolbar remains visible.
**Expected Result:** The `WebView` successfully navigates to the next page while remaining inside the application.
**Actual Result:** Passed. The WebView navigated properly.
**Screenshot:**
![WebView Navigation](screenshots/test-case-03-webview.png)

## 17. Actual Screenshots of the Final Application
*(See Test Cases section above for embedded screenshots).*

## 18. Student Details
**Student Name:** Shrawan Gautam
**USN:** 25MCAR0229

## 19. Learning Outcomes
- Successfully implemented a native Android Options Menu.
- Integrated a `WebView` seamlessly into an Android application.
- Handled offline fallback routing safely using local HTML assets.
- Designed a structured, beautiful layout using modern Android UI principles.

## 20. Conclusion
The experiment successfully demonstrates how to navigate within an Android application using an Options Menu and how to embed web content securely using a WebView. The application handles networking, native menus, and Material UI aesthetics perfectly.
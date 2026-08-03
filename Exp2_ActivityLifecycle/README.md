# Experiment 02 – Android Activity Lifecycle

## Aim

To explore the Android Activity Lifecycle by implementing all lifecycle callback methods and observing their execution through customized Toast notifications and Logcat messages.

---

## Overview

This experiment demonstrates how an Android Activity transitions through different lifecycle states. Every lifecycle callback is implemented to display a custom Toast notification as well as a Logcat message, allowing users to understand how Android manages activities during various user interactions.

---

## Technologies Used

* Android Studio
* Kotlin
* Jetpack Compose
* Java (for Custom Toast implementation)
* Android SDK
* Logcat

---

## Key Highlights

* Displays a simple **Hello World** interface.
* Implements all major Activity Lifecycle methods:

  * `onCreate()`
  * `onStart()`
  * `onResume()`
  * `onPause()`
  * `onStop()`
  * `onRestart()`
  * `onDestroy()`
* Shows a customized Toast whenever a lifecycle callback is executed.
* Toast notifications include:

  * Rounded background
  * Information icon
  * Custom styling
  * Display at the top of the screen
* Records every lifecycle event in **Logcat** for debugging and analysis.

---

## Activity Lifecycle Summary

| Lifecycle Method | Purpose                                                        |
| ---------------- | -------------------------------------------------------------- |
| **onCreate()**   | Initializes the activity and loads the user interface.         |
| **onStart()**    | Makes the activity visible to the user.                        |
| **onResume()**   | Places the activity in the foreground for user interaction.    |
| **onPause()**    | Invoked when the activity loses focus temporarily.             |
| **onStop()**     | Called when the activity is completely hidden.                 |
| **onRestart()**  | Executed before the activity starts again after being stopped. |
| **onDestroy()**  | Performs cleanup before the activity is removed from memory.   |

---

## Project Directory

```
Experiment-02-ActivityLifecycle/
│
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   ├── MainActivity.kt
│   │   │   │   └── CustomToaster.java
│   │   │   ├── res/
│   │   │   │   ├── layout/
│   │   │   │   │   └── toast_layout.xml
│   │   │   │   ├── drawable/
│   │   │   │   │   └── toast_background.xml
│   │   │   │   └── values/
│   │   │   └── AndroidManifest.xml
│
├── gradle/
├── build.gradle
├── settings.gradle
└── README.md
```

---

## Execution Steps

1. Download or clone the project.
2. Open it in Android Studio.
3. Allow Gradle to complete synchronization.
4. Connect an Android device or launch an emulator.
5. Build and run the application.
6. Monitor lifecycle execution using:

   * Custom Toast notifications
   * Logcat output

---

## Test Scenarios

### Test Case 1 – Application Launch

**Action**

Launch the application.

**Expected Output**

* onCreate()
* onStart()
* onResume()

---

### Test Case 2 – Background & Resume

**Action**

Press the **Home** button and reopen the application.

**Expected Output**

* onPause()
* onStop()
* onRestart()
* onStart()
* onResume()

---

### Test Case 3 – Exit Application

**Action**

Press the **Back** button.

**Expected Output**

* onPause()
* onStop()
* onDestroy()

---

## Demo

**Video:** `

https://github.com/user-attachments/assets/e0096a81-c10a-4423-8801-0107e60cd53e

`

---

## Learning Outcomes

After completing this experiment, the following concepts were understood:

* Working of the Android Activity Lifecycle.
* Implementation of lifecycle callback methods in Kotlin.
* Creating and displaying custom Toast notifications.
* Monitoring application events using Logcat.
* Designing custom Toast layouts using XML resources.

---

## Author

**Developed by:** *Shrawan Gautam*

**Course:** MCA

**Institution:** Jain University

---

## Note

This experiment has been developed for educational and laboratory learning purposes to understand Android Activity Lifecycle management.

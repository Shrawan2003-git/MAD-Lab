# Experiment 5 – Android Notifications

## Aim

To develop an Android application that demonstrates the creation and display of system notifications using Android Notification APIs.

---

## Experiment Description

This experiment demonstrates how an Android application can create and display notifications to the user.

The application provides a user-friendly notification interface where the user can enter notification details such as notification type, notification priority, notification title, and notification message.

When the user presses the **SEND NOTIFICATION** button, the application generates and displays an Android system notification.

---

## Concept / Technology Used

### Android Notification

An Android notification is a message displayed outside the application's normal user interface. Notifications are used to inform users about important events, updates, reminders, announcements, and other activities.

### Notification Channel

A Notification Channel is used to organize and manage notifications on Android 8.0 (API level 26) and above.

### NotificationManager

`NotificationManager` is responsible for managing and displaying notifications through the Android system.

### NotificationCompat

`NotificationCompat` is used to create notifications while maintaining compatibility across different Android versions.

### Notification Permission

Android 13 and later require the `POST_NOTIFICATIONS` permission before an application can display notifications.

---

# Technologies Used

- Android Studio
- Kotlin
- XML
- Android SDK
- Android Notification API
- NotificationChannel
- NotificationManager
- NotificationCompat
- Material Design

---

# Scenario

The application represents an academic notification management system.

The user can enter notification information such as:

- Notification Type
- Notification Priority
- Notification Title
- Notification Message

After entering the required information, the user presses the **SEND NOTIFICATION** button.

The application creates an Android notification using the entered information and displays it in the device notification panel.

This scenario demonstrates how notification functionality can be used in real-world academic applications for announcements, assignments, examinations, reminders, and other important updates.

---

# Application Flow

```text
┌─────────────────────────────┐
│        MainActivity         │
│                             │
│   Notification Type         │
│   Notification Priority     │
│   Notification Title        │
│   Notification Message      │
│                             │
│    SEND NOTIFICATION        │
└──────────────┬──────────────┘
               │
               │ Notification API
               ↓
┌─────────────────────────────┐
│    Notification Channel     │
│                             │
│    NotificationManager      │
│    NotificationCompat       │
└──────────────┬──────────────┘
               │
               ↓
┌─────────────────────────────┐
│     Android Notification    │
│                             │
│     Title                   │
│     Message                 │
│     Priority                │
└─────────────────────────────┘

##Project Folder Structure

EXP 5 notification/
│
├── app/
│   ├── src/
│   │   ├── androidTest/
│   │   │
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/example/experiment5_notifications/
│   │   │   │       └── MainActivity.kt
│   │   │   │
│   │   │   ├── res/
│   │   │   │   ├── drawable/
│   │   │   │   ├── layout/
│   │   │   │   │   └── activity_main.xml
│   │   │   │   ├── mipmap/
│   │   │   │   └── values/
│   │   │   │
│   │   │   └── AndroidManifest.xml
│   │   │
│   │   └── test/
│   │
│   └── build.gradle.kts
│
├── Screenshots/
│   ├── ui.png
│   ├── Test case 1.png
│   ├── Test case_2.png
│   └── Test case 3.png
│
├── .gitignore
├── README.md
├── build.gradle.kts
├── gradle.properties
├── gradlew
├── gradlew.bat
└── settings.gradle.kts

##Core Code

The notification is created using NotificationCompat.Builder():

val notification = NotificationCompat.Builder(
    this,
    channelId
)
    .setSmallIcon(android.R.drawable.ic_dialog_info)
    .setContentTitle(title)
    .setContentText(message)
    .setStyle(
        NotificationCompat.BigTextStyle()
            .bigText(message)
    )
    .setPriority(priority)
    .setAutoCancel(true)
    .build()

The notification is displayed using NotificationManagerCompat:

NotificationManagerCompat.from(this)
    .notify(
        System.currentTimeMillis().toInt(),
        notification
    )

##Output Screenshot
Application User Interface

The following screenshot shows the final user interface of the Android Notification application.

Test Cases
Test Case 1 – USN and Name
Input

Notification Type: Academic
Notification Priority: High

Test Case 2 – Assignment Notification
Input

Notification Type: Assignment
Notification Priority: Medium

Test Case 3 – Examination Notification
Input

Notification Type: Examination
Notification Priority: High

##Result

The Android Notification application was successfully developed using Kotlin and Android Notification APIs.

The application successfully accepts notification details from the user and generates an Android system notification using NotificationChannel, NotificationCompat, and NotificationManager.

The application was tested using three different test cases, including a test case containing the student's name and USN. All test cases were successfully executed and the expected output was obtained.

##Conclusion

This experiment demonstrates the implementation of Android notifications and provides an understanding of Notification Channels, NotificationManager, NotificationCompat, notification priority, and runtime notification permissions.

The experiment successfully demonstrates how notification functionality can be integrated into an Android application for practical academic and real-world use cases.
#  Mobile Application Development (MAD) Lab
## Experiment 1 – Hello World Android Application

---

## Student Details

| Field | Details |
|-------|---------|
| **Name** | Shrawan Gautam |
| **Course** | Master of Computer Applications (MCA) |
| **Subject** | Mobile Application Development (MAD) Lab |
| **Experiment No.** | 1 |
| **Experiment Name** | Hello World Android Application |

---

# Experiment Objective

The objective of this experiment is to understand the fundamentals of Android application development using Android Studio and Kotlin. The experiment demonstrates how to create, build, and run a basic Android application that displays a simple message on the screen.

This experiment also helps in understanding the structure of an Android project, the role of XML layouts, Kotlin programming, and the Android application lifecycle.

---

# Concept / Technology Behind the Experiment

Android is an open-source operating system developed by Google for mobile devices. Android applications are primarily developed using **Android Studio**, the official Integrated Development Environment (IDE) provided by Google.

This project is developed using the following technologies:

- **Android Studio** – Used to develop, build, and debug Android applications.
- **Kotlin** – The official programming language for Android development.
- **XML (Extensible Markup Language)** – Used to design the application's user interface.
- **ConstraintLayout** – Used to position UI components efficiently.
- **Gradle** – Build automation system responsible for compiling and packaging the application.
- **Android SDK** – Provides libraries and APIs required for Android application development.

### Working Principle

When the application starts, Android launches the **MainActivity**. The `onCreate()` method is executed, which loads the layout file (`activity_main.xml`) using the `setContentView()` method. The XML layout contains a `TextView` that displays the message:

> **"Hellow World! This is my first Android App"**

The Android framework renders this layout and displays it on the device screen.

---

# Scenario Used to Demonstrate the Experiment

Suppose a software company has set up a new Android development environment for its developers. Before developing large applications, it is important to verify that the development tools are installed correctly.

To validate the environment, a simple Android application is created that displays a text message on the screen.

This experiment demonstrates that:

- Android Studio is installed correctly.
- Gradle successfully builds the project.
- XML layouts are rendered correctly.
- Kotlin code executes successfully.
- The application can run on an Android emulator or a physical Android device.

This "Hello World" application serves as the foundation for developing more advanced Android applications.

---

# Project Folder Structure

```
MAD Applications
│
├── app
│   ├── src
│   │   ├── androidTest
│   │   ├── test
│   │   └── main
│   │       ├── java
│   │       │   └── com
│   │       │       └── example
│   │       │           └── myfirstapplication
│   │       │               └── MainActivity.kt
│   │       │
│   │       ├── res
│   │       │   ├── drawable
│   │       │   ├── layout
│   │       │   │   └── activity_main.xml
│   │       │   ├── mipmap
│   │       │   ├── values
│   │       │   └── xml
│   │       │
│   │       └── AndroidManifest.xml
│   │
│   ├── build.gradle.kts
│   └── .gitignore
│
├── Screenshot
│   ├── Hello World.jpeg
│   ├── Quotes Test case 3.jpeg
│   └── USN & Name.jpeg
│
├── gradle
├── build.gradle.kts
├── gradle.properties
├── settings.gradle.kts
├── gradlew
├── gradlew.bat
└── README.md
```

---

# Main Code

## activity_main.xml

```xml
<TextView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Hello World! This is my first Android App"
    android:textSize="24sp"
    app:layout_constraintTop_toTopOf="parent"
    app:layout_constraintBottom_toBottomOf="parent"
    app:layout_constraintStart_toStartOf="parent"
    app:layout_constraintEnd_toEndOf="parent"/>
```

### Explanation

The **TextView** widget displays the message on the screen. ConstraintLayout centers the TextView horizontally and vertically, making the user interface simple and responsive.

---

## MainActivity.kt

```kotlin
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
```

### Explanation

`MainActivity` is the first screen that appears when the application starts.

The `onCreate()` method is called automatically when the activity is created.

`setContentView()` loads the XML layout so that the user interface becomes visible.

---

# Output Screenshot

### Application Output

![Application Output](Screenshot/Hello%20World.jpeg)
<img width="862" height="1006" alt="Hello World" src="https://github.com/user-attachments/assets/7f461faa-15c8-4ab1-8d9f-e01aa12a6f2a" />

<img width="688" height="942" alt="USN   Name" src="https://github.com/user-attachments/assets/176a615d-c761-4562-bfc7-4b4dbf488330" />

<img width="705" height="1011" alt="Quotes Test case 3" src="https://github.com/user-attachments/assets/e7a21cab-26c2-4c61-af24-1c762013d79f" />

---

# Test Cases

| Test Case | Expected Result |               Status |
|------------|-------------|-----------------|--------|
| TC-01 | Hello World! This is my first Andorid App  | ✅ Pass |
| TC-02 |  25MCAR0229 Shrawan Gautam         | ✅ Pass |
| TC-03 | "Success starts with every challenge,not with the comfort zone" Shrawan Gautam| ✅ Pass |

---

# Test Case Screenshots

## Test Case 1

Hello World! This is my first Andorid App

<img width="862" height="1006" alt="Hello World" src="https://github.com/user-attachments/assets/7f461faa-15c8-4ab1-8d9f-e01aa12a6f2a" />

---
## Test Case 2 (USN & Name)

25MCAR0229 Shrawan Gautam

<img width="688" height="942" alt="USN   Name" src="https://github.com/user-attachments/assets/176a615d-c761-4562-bfc7-4b4dbf488330" />

---
## Test Case 3 

"Success starts with every challenge,not with the comfort zone" Shrawan Gautam

<img width="705" height="1011" alt="Quotes Test case 3" src="https://github.com/user-attachments/assets/e7a21cab-26c2-4c61-af24-1c762013d79f" />


---

# Learning Outcomes

After completing this experiment, the following concepts were learned:

- Android project structure
- Android Studio environment
- Kotlin basics for Android
- XML-based user interface design
- ConstraintLayout
- Android Activity lifecycle
- Gradle build system
- Running applications on an emulator and a physical Android device
- GitHub project management and documentation

---

# Conclusion

This experiment successfully demonstrated the development of a basic Android application using Kotlin and Android Studio. The project helped in understanding the Android development environment, project structure, XML layouts, activity lifecycle, and Gradle build process. The successful execution of the application confirms that the Android development environment is correctly configured and ready for building more advanced mobile applications.

---

# Author

**Shrawan Gautam**

Master of Computer Applications (MCA)

Mobile Application Development Laboratory

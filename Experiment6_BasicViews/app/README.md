# Experiment 6 – Android Application Using Basic Views

## Aim

To develop an Android application using basic Android Views to create an interactive student registration form and display the submitted information on a separate result screen.

---

## Experiment Description

This experiment demonstrates the practical implementation of **Basic Views in Android application development**.

The application provides a student registration form through which the user can enter personal, academic, and technical information. After submitting the form, the application navigates to a result screen and displays the submitted information in a structured profile summary.

The application consists of two Activities:

1. **MainActivity** – Displays the student registration form and collects user input.
2. **ResultActivity** – Displays the submitted student information after successful registration.

The application demonstrates the use of different Android Views along with Activity navigation and Intent-based data passing.

---

## Concept / Technology Used

### Basic Android Views

Android Views are the fundamental user interface components used to create Android applications.

The following Basic Views are used in this experiment:

- TextView
- EditText
- Button
- RadioButton
- RadioGroup
- CheckBox
- Switch
- Spinner
- LinearLayout
- ScrollView

These Views are combined to create a structured and user-friendly student registration form.

### Explicit Intent

An **Explicit Intent** is used to navigate from `MainActivity` to `ResultActivity`.

The information entered by the user is passed from the first Activity to the second Activity using Intent extras.

Example:

```kotlin
val intent = Intent(this, ResultActivity::class.java)

intent.putExtra("NAME", name)
intent.putExtra("USN", usn)
intent.putExtra("EMAIL", email)
intent.putExtra("MOBILE", mobile)
intent.putExtra("GENDER", gender)
intent.putExtra("SPECIALIZATION", specialization)
intent.putExtra("SKILLS", skills)
intent.putExtra("NOTIFICATIONS", notificationStatus)

startActivity(intent)
```

The data is retrieved in the second Activity using:

```kotlin
intent.getStringExtra("NAME")
intent.getStringExtra("USN")
intent.getStringExtra("EMAIL")
intent.getStringExtra("MOBILE")
```

---

## Scenario

The application represents a **Student Profile Registration System**.

A student enters the following information:

- Full Name
- University Seat Number (USN)
- Email Address
- Mobile Number
- Gender
- Specialization
- Technical Skills
- Notification Preference
- Terms and Conditions

After entering the required information, the student clicks the **REGISTER** button.

The application validates the information and opens the result screen.

The result screen displays a **Registration Successful** message along with a formatted **Profile Summary** containing the submitted student information.

---

# Application Flow

```text
┌───────────────────────────────┐
│          MainActivity         │
│                               │
│       Profile Information     │
│                               │
│       Full Name               │
│       USN                     │
│       Email Address           │
│       Mobile Number           │
│                               │
│       Gender                  │
│       Specialization          │
│       Technical Skills        │
│       Notifications            │
│       Terms & Conditions      │
│                               │
│          REGISTER             │
└───────────────┬───────────────┘
                │
                │ Explicit Intent
                │
                │ putExtra()
                ↓
┌───────────────────────────────┐
│        ResultActivity         │
│                               │
│     Registration Successful   │
│                               │
│        Profile Summary        │
│                               │
│       Full Name               │
│       USN                     │
│       Email Address           │
│       Mobile Number           │
│       Gender                  │
│       Specialization          │
│       Technical Skills        │
│       Notifications           │
│                               │
│      BACK TO REGISTRATION     │
└───────────────────────────────┘
```

---

# User Interface

The application uses a clean and professional UI with a soft **mint, cream, and blue-green colour theme**.

The registration screen uses rounded input fields and clearly separated sections for profile information, gender, specialization, technical skills, and notification preferences.

The result screen uses the same visual theme to maintain consistency throughout the application.

## Main Application UI

![Main Application UI](Screenshots/main_page.jpeg)

---

# Project Folder Structure

```text
Experiment6_BasicViews/
│
├── app/
│   ├── src/
│   │   ├── androidTest/
│   │   │
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/example/experiment6_basicviews/
│   │   │   │       ├── MainActivity.kt
│   │   │   │       └── ResultActivity.kt
│   │   │   │
│   │   │   ├── res/
│   │   │   │   ├── drawable/
│   │   │   │   ├── layout/
│   │   │   │   │   ├── activity_main.xml
│   │   │   │   │   └── activity_result.xml
│   │   │   │   │
│   │   │   │   ├── mipmap/
│   │   │   │   ├── values/
│   │   │   │   └── xml/
│   │   │   │
│   │   │   └── AndroidManifest.xml
│   │   │
│   │   └── test/
│   │
│   └── build.gradle.kts
│
├── Screenshots/
│   ├── main_page.jpeg
│   ├── test_case_1.jpeg
│   ├── test_case_2.jpeg
│   └── test_case_3.jpeg
│
├── .gitignore
├── README.md
├── build.gradle.kts
├── gradle.properties
├── gradlew
├── gradlew.bat
└── settings.gradle.kts
```

---

# Important Files

## MainActivity.kt

`MainActivity.kt` is responsible for:

- Displaying the registration form.
- Reading user input.
- Validating the entered information.
- Handling gender selection.
- Handling specialization selection.
- Handling technical skill selection.
- Handling notification preference.
- Checking the Terms and Conditions.
- Creating an Explicit Intent.
- Passing the entered information to `ResultActivity`.

---

## ResultActivity.kt

`ResultActivity.kt` is responsible for:

- Receiving the information from `MainActivity`.
- Displaying the registration success message.
- Displaying the submitted profile information.
- Showing the selected technical skills.
- Showing the notification status.
- Providing a button to return to the registration screen.

---

## activity_main.xml

`activity_main.xml` defines the main registration interface.

It contains Android Basic Views such as:

- TextView
- EditText
- RadioGroup
- RadioButton
- Spinner
- CheckBox
- Switch
- Button
- LinearLayout
- ScrollView

---

## activity_result.xml

`activity_result.xml` defines the successful registration screen.

It displays:

- Registration Successful
- Profile Summary
- Full Name
- USN
- Email Address
- Mobile Number
- Gender
- Specialization
- Technical Skills
- Notification Status
- Back to Registration button

---

## AndroidManifest.xml

`AndroidManifest.xml` contains the application configuration and registers the Activities used by the application.

---

# Test Cases

## Test Case 1 – Student Registration with USN and Name

### Input

```text
Full Name:
Shrawan Gautam

USN:
25MCAR0229

Email Address:
shrawansingh2230@gmail.com

Mobile Number:
6360450040

Gender:
Male

Specialization:
Software Engineering

Technical Skills:
Artificial Intelligence / ML
Web Development

Notifications:
Enabled

Terms and Conditions:
Accepted
```

### Expected Result

The application should successfully accept the entered student information and navigate from `MainActivity` to `ResultActivity`.

The result screen should display the student's name, USN, and other submitted profile information.

### Result

**PASS**

### Screenshot

![Test Case 1](Screenshots/test_case_1.jpeg)

---

## Test Case 2 – Registration with Artificial Intelligence / ML

### Input

```text
Full Name:
Ravi Kumar

USN:
25MCAR227

Email Address:
rk7@gmail.com

Mobile Number:
7599358080

Gender:
Male

Specialization:
Software Engineering

Technical Skills:
Artificial Intelligence / ML

Notifications:
Enabled

Terms and Conditions:
Accepted
```

### Expected Result

The application should successfully process the entered information and display the submitted profile information on the ResultActivity screen.

The selected technical skill should be displayed as:

```text
Artificial Intelligence / ML
```

### Result

**PASS**

### Screenshot

![Test case 2.jpeg](../Screenshots/Test%20case%202.jpeg)

---

## Test Case 3 – Registration with Multiple Technical Skills

### Input

```text
Full Name:
Rahul Kumar

USN:
25MCAR220

Email Address:
rk7@gmail.com

Mobile Number:
7599358080

Gender:
Male

Specialization:
Software Engineering

Technical Skills:
Artificial Intelligence / ML
Web Development

Notifications:
Enabled

Terms and Conditions:
Accepted
```

### Expected Result

The application should successfully process the registration and display the submitted profile information.

Both selected technical skills should be displayed in the Profile Summary.

### Result

**PASS**

### Screenshot

![Test Case 3](Screenshots/test_case_3.jpeg)

---

# Validation

The application performs validation before submitting the registration form.

The following information is checked:

- Full Name should not be empty.
- USN should not be empty.
- Email Address should not be empty.
- Mobile Number should not be empty.
- Gender should be selected.
- Specialization should be selected.
- At least one technical skill should be selected.
- Notification preference is handled using a Switch.
- Terms and Conditions should be accepted.

If required information is missing, the application prevents submission and displays an appropriate validation message.

---

# Output

The application successfully provides a complete student registration workflow.

The first screen collects student information using different Basic Android Views.

After successful submission, the application navigates to the result screen using an Explicit Intent and displays the submitted information in a Profile Summary.

The application maintains a consistent aesthetic design across both screens.

---

# Results

The Android application was successfully developed using **Basic Android Views**.

The experiment successfully demonstrates the practical implementation of:

- TextView
- EditText
- Button
- RadioButton
- RadioGroup
- Spinner
- CheckBox
- Switch
- LinearLayout
- ScrollView
- Explicit Intent

The student information was successfully collected from the registration form and displayed on the ResultActivity screen.

All three test cases were executed successfully and produced the expected output.

At least one test case contains the student's **Name and USN**, satisfying the experiment submission requirement.

---

# Conclusion

This experiment demonstrates how Basic Android Views can be combined to develop an interactive and user-friendly Android application.

The Student Profile Registration System successfully collects user information, validates the required fields, transfers the data between Activities using Explicit Intent, and displays the submitted information on a separate result screen.

The experiment provides practical understanding of Android UI components, user input handling, Activity navigation, and Intent-based data passing.
# Experiment 9: Demonstrate Data Persistence Using SharedPreferences and SQLite

## Student Details

- **Name:** Shrawan Gautam
- **USN:** 25MCAR0229
- **Course:** Master of Computer Applications (MCA)
- **Subject:** Mobile Application Development Lab
- **Experiment Number:** 9

---

## 1. Aim

To develop an Android application using Java that demonstrates data persistence through SharedPreferences and SQLite, allowing student information and study notes to be stored locally and retrieved even after the application is closed and reopened.

## 2. Experiment Description

This experiment demonstrates two local data storage mechanisms available in Android development: SharedPreferences and SQLite.

The application, named **Student Profile & Notes**, allows users to save their student profile and maintain personal study notes.

The student profile is stored using SharedPreferences, while study notes are stored in an SQLite database. The application retrieves previously saved information when it is launched again.

### Main Features

- Save the student's name and USN using SharedPreferences.
- Store study notes with a title and description using SQLite.
- Display saved notes in individual cards.
- Retrieve previously saved information when the application reopens.
- Display confirmation messages after saving data.
- Delete a saved note using the available delete option.
- Provide a modern, responsive interface with rounded cards, gradient headers, and colored buttons.

## 3. Concept and Technologies Used

### A. SharedPreferences

SharedPreferences is an Android storage mechanism used to save small amounts of data in key-value pairs.

In this experiment, SharedPreferences stores the student's name and USN.

**Example:**

```java
SharedPreferences preferences =
        getSharedPreferences("StudentPrefs", MODE_PRIVATE);

preferences.edit()
        .putString("student_name", "Shrawan Gautam")
        .putString("student_usn", "25MCAR0229")
        .apply();
```

To retrieve the saved information:

```java
String name = preferences.getString("student_name", "");
String usn = preferences.getString("student_usn", "");
```

**Applications:**
- Saving user preferences.
- Storing small amounts of profile information.
- Maintaining application settings.
- Remembering non-sensitive user selections.

### B. SQLite

SQLite is a lightweight relational database that stores structured data locally on an Android device.

In this experiment, SQLite stores study notes containing a unique ID, title, description, and creation timestamp.

**Example table structure:**

| Column | Data type | Purpose |
|---|---|---|
| id | INTEGER | Unique identifier for each note |
| title | TEXT | Title of the study note |
| description | TEXT | Content of the note |
| created_at | TEXT | Date and time the note was created |

Example SQL statement:

```sql
CREATE TABLE notes (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    title TEXT NOT NULL,
    description TEXT NOT NULL,
    created_at TEXT NOT NULL
);
```

SQLite supports operations such as:
- **INSERT:** Add a new note.
- **SELECT:** Retrieve saved notes.
- **DELETE:** Remove a selected note.
- **UPDATE:** Modify existing records when implemented.

**Applications:**
- Offline note-taking applications.
- Task management systems.
- Local record keeping.
- Applications requiring structured offline storage.

## 4. Scenario Used

A student wants to maintain personal information and study notes in an Android application.

The application provides the following workflow:

1. The student enters their name and USN.
2. The application saves the profile using SharedPreferences.
3. The student enters a note title and description.
4. The application stores the note in SQLite.
5. Saved notes appear in the Saved Notes section.
6. When the application is closed and reopened, the saved profile and notes are loaded from local storage.

This scenario demonstrates how Android applications can preserve data without requiring an internet connection.

## 5. Technologies Used

| Technology | Purpose |
|---|---|
| Java | Application logic and event handling |
| XML | Designing the Android user interface |
| SharedPreferences | Persisting student profile details |
| SQLite | Storing and retrieving study notes |
| SQLiteOpenHelper | Creating and managing the database |
| Android Studio | Application development and testing |
| Git and GitHub | Version control and project submission |

## 6. Application Workflow

```text
Launch Application
        |
        v
Load Student Profile
(SharedPreferences)
        |
        v
Load Saved Notes
(SQLite Database)
        |
        v
Display Student Profile & Notes
        |
        v
Save Student Details
        |
        v
SharedPreferences
        |
        v
Create Study Note
        |
        v
Save Note to SQLite
        |
        v
Refresh Saved Notes List
        |
        v
Close and Reopen Application
        |
        v
Verify Data Persistence
```

## 7. Project Folder Structure

```text
EXP9/
│
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/example/experiment9datapersistence/
│   │   │   │       ├── MainActivity.java
│   │   │   │       └── DatabaseHelper.java
│   │   │   │
│   │   │   ├── res/
│   │   │   │   ├── drawable/
│   │   │   │   │   ├── bg_header.xml
│   │   │   │   │   ├── bg_card.xml
│   │   │   │   │   ├── bg_input.xml
│   │   │   │   │   ├── bg_button_primary.xml
│   │   │   │   │   ├── bg_button_secondary.xml
│   │   │   │   │   ├── bg_note_card.xml
│   │   │   │   │   ├── bg_icon_blue.xml
│   │   │   │   │   ├── bg_icon_purple.xml
│   │   │   │   │   ├── bg_icon_orange.xml
│   │   │   │   │   └── bg_icon_pink.xml
│   │   │   │   │
│   │   │   │   ├── layout/
│   │   │   │   │   └── activity_main.xml
│   │   │   │   │
│   │   │   │   └── values/
│   │   │   │       ├── colors.xml
│   │   │   │       ├── strings.xml
│   │   │   │       └── themes.xml
│   │   │   │
│   │   │   └── AndroidManifest.xml
│   │   │
│   │   ├── androidTest/
│   │   └── test/
│   │
│   ├── build.gradle.kts
│   └── .gitignore
│
├── gradle/
│   └── wrapper/
│
├── Screenshots/
│   ├── Test 1.jpeg
│   ├── Test 2.jpeg
│   └── Test 3.jpeg
│
├── build.gradle.kts
├── settings.gradle.kts
├── gradle.properties
├── gradlew
├── gradlew.bat
└── Readme.md
```


## 8. Important Files and Their Functions

| File | Function |
|---|---|
| `MainActivity.java` | Handles profile saving, note creation, note display, and user interactions |
| `DatabaseHelper.java` | Creates the SQLite database and performs insert, retrieve, and delete operations |
| `activity_main.xml` | Defines the main screen layout |
| `bg_header.xml` | Defines the gradient header background |
| `bg_card.xml` | Defines the rounded white cards |
| `bg_input.xml` | Defines the input field background |
| `bg_button_primary.xml` | Defines the primary gradient button |
| `bg_button_secondary.xml` | Defines the secondary gradient button |
| `bg_note_card.xml` | Defines the saved-note card background |
| `colors.xml` | Defines the application's color palette |
| `themes.xml` | Defines the application theme |
| `AndroidManifest.xml` | Declares the application and launcher activity |

## 9. Output Screenshots

The following screenshots demonstrate the application's interface and the saving operations performed during testing.

### Screenshot 1: Initial Application UI

The application displays the Student Profile section, Add Study Note section, and Saved Notes area.

![Test Case 1 - Initial UI](Screenshots/Test%201.jpeg)

### Screenshot 2: SharedPreferences — Student Profile Saved

The student's name and USN are entered in the profile fields. The application displays a confirmation that the profile has been saved.

![Test Case 2 - SharedPreferences](Screenshots/Test%202.jpeg)

### Screenshot 3: SQLite — Study Note Saved

A study note is saved to the SQLite database and appears in the Saved Notes section with its title, description, and creation timestamp.

![Test Case 3 - SQLite](Screenshots/Test%203.jpeg)

## 10. Test Cases

### Test Case 1: Initial Application Interface

**Objective:** Verify that the application launches and displays all required sections.

**Procedure:**
1. Launch the application on an Android emulator or physical device.
2. Observe the Student Profile section.
3. Observe the Add Study Note section.
4. Verify that the Saved Notes section is displayed.

**Expected Result:** The application opens successfully and displays the profile fields, note input fields, save buttons, and saved-notes area.

**Screenshot:** `Screenshots/Test 1.jpeg`

### Test Case 2: SharedPreferences Persistence

**Objective:** Verify that student profile information can be saved using SharedPreferences.

**Test Data:**
- Name: Shrawan Gautam
- USN: 25MCAR0229

**Procedure:**
1. Enter the student's name and USN.
2. Click Save Student Profile.
3. Verify the confirmation message.
4. Close and reopen the application.
5. Verify that the saved name and USN are displayed.

**Expected Result:** The application stores and retrieves the student profile using SharedPreferences.

**Screenshot:** `Screenshots/Test 2.jpeg`

### Test Case 3: SQLite Data Persistence

**Objective:** Verify that a study note can be saved and displayed using SQLite.

**Test Data:**
- Title: Testing
- Description: Exp 9 app testing

**Procedure:**
1. Enter the note title.
2. Enter the note description.
3. Click Save Note to SQLite.
4. Verify the confirmation message.
5. Verify that the note appears in Saved Notes.
6. Close and reopen the application and verify that the note remains available.

**Expected Result:** The note is inserted into SQLite and retrieved for display. The note remains available after restarting the application.

**Screenshot:** `Screenshots/Test 3.jpeg`

## 11. Learning Outcomes

After completing this experiment, I learned:

1. How to implement SharedPreferences in an Android application using Java.
2. How to store and retrieve key-value data.
3. How to create and manage an SQLite database using SQLiteOpenHelper.
4. How to insert, retrieve, and delete database records.
5. How to maintain local data persistence across application restarts.
6. How to design an Android interface using XML and drawable resources.
7. How to test local storage functionality on an Android device.

## 12. Result

The Android application demonstrates local data persistence using SharedPreferences and SQLite. SharedPreferences is used to store student profile information, while SQLite is used to store and retrieve study notes. The application provides a modern user interface and demonstrates how locally stored information can remain available after the application is closed and reopened.


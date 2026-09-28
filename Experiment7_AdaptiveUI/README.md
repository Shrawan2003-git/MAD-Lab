# Experiment 7 — Adaptive UI using ListView and ImageView

## Mobile Application Development Lab

---

## Experiment Title

**Create an Adaptive UI using ListView and ImageView**

---

## Student Information

| Field | Details |
|---|---|
| **Name** | Shrawan Gautam |
| **USN** | 25MCAR0229 |
| **Course** | MCA |
| **Experiment** | Experiment 7 |
| **Programming Language** | Kotlin |
| **Platform** | Android |

---

# 1. Aim

To create a modern and adaptive Android user interface using **ListView and ImageView** in Kotlin, where multiple campus facilities are displayed dynamically using a custom adapter.

---

# 2. Experiment Description

This experiment demonstrates the development of an Android application using **ListView** and **ImageView**.

The application displays multiple campus facilities in a vertically scrollable list. Each ListView item contains an ImageView along with information about the facility, such as its name, category, and operating time.

A custom Kotlin adapter is used to connect the campus facility data with the individual ListView item layout.

The application also demonstrates adaptive Android UI design using XML layouts, flexible dimensions, drawable resources, and reusable components.

---

# 3. Scenario Used

## Campus Explorer

A **Campus Explorer** application has been developed to demonstrate the use of ListView and ImageView.

The application represents a campus information interface where students can view important facilities available on the campus.

The application displays the following campus facilities:

1. **Central Library**
2. **Computer Lab**
3. **Campus Cafeteria**
4. **Sports Complex**
5. **Administration Block**

Each facility is displayed as an individual ListView item containing:

- Facility icon
- Facility name
- Facility category
- Operating time
- Navigation indicator

The application also displays the student's name and USN in the welcome section.

---

# 4. Technologies Used

The following technologies and Android components were used:

- **Android Studio**
- **Kotlin**
- **XML**
- **Android SDK**
- **ListView**
- **ImageView**
- **Custom Adapter**
- **LinearLayout**
- **FrameLayout**
- **Drawable Resources**
- **Toast**
- **Git**
- **GitHub**

---

# 5. Concepts Demonstrated

## 5.1 ListView

`ListView` is used to display multiple campus facility items vertically in a scrollable list.

The ListView is connected to a custom adapter that provides the data and layout for each item.

Example:

```xml
<ListView
    android:id="@+id/campusListView"
    android:layout_width="match_parent"
    android:layout_height="0dp"
    android:layout_weight="1" />
```

---

## 5.2 ImageView

`ImageView` is used inside each ListView item to display the image or icon associated with a campus facility.

Example:

```xml
<ImageView
    android:id="@+id/imgCampus"
    android:layout_width="72dp"
    android:layout_height="72dp"
    android:scaleType="centerInside" />
```

Different drawable icons are used for the library, computer lab, cafeteria, sports complex, and administration block.

---

## 5.3 Custom Adapter

A custom Kotlin adapter is used to connect the campus facility data with the ListView.

The `CampusAdapter.kt` file is responsible for:

- Inflating the ListView item layout
- Setting the facility image
- Setting the facility name
- Setting the facility category
- Setting the facility timing
- Handling item interaction

---

## 5.4 Data Class

The `CampusItem.kt` file defines the data model for a campus facility.

Example:

```kotlin
data class CampusItem(
    val name: String,
    val category: String,
    val timing: String,
    val imageResource: Int
)
```

---

## 5.5 Adaptive UI

The application uses Android's flexible layout techniques to support different screen sizes.

The following attributes and concepts are used:

- `match_parent`
- `wrap_content`
- `layout_weight`
- `dp`
- `sp`
- Flexible ListView height
- Reusable XML layouts
- Responsive spacing and padding

---

# 6. Application Features

The application provides the following features:

- Modern Campus Explorer interface
- Student information section
- Student name and USN display
- Custom ListView
- ImageView-based facility icons
- Five campus facilities
- Scrollable facility list
- Facility selection interaction
- Toast feedback
- Card-based UI
- Custom drawable backgrounds
- Academic-themed application background
- Adaptive Android layout

---

# 7. Project Folder and File Structure

The complete project is organized as follows:

```text
Experiment7_AdaptiveUI/
│
├── .gitignore
├── README.md
├── build.gradle.kts
├── gradle.properties
├── gradlew
├── gradlew.bat
├── settings.gradle.kts
│
├── gradle/
│   └── wrapper/
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
│
├── app/
│   ├── .gitignore
│   ├── build.gradle.kts
│   │
│   └── src/
│       │
│       ├── androidTest/
│       │   └── java/
│       │       └── com/
│       │           └── example/
│       │               └── experiment7_adaptiveui/
│       │                   └── ExampleInstrumentedTest.kt
│       │
│       ├── test/
│       │   └── java/
│       │       └── com/
│       │           └── example/
│       │               └── experiment7_adaptiveui/
│       │                   └── ExampleUnitTest.kt
│       │
│       └── main/
│           │
│           ├── AndroidManifest.xml
│           │
│           ├── java/
│           │   └── com/
│           │       └── example/
│           │           └── experiment7_adaptiveui/
│           │               ├── MainActivity.kt
│           │               ├── CampusAdapter.kt
│           │               └── CampusItem.kt
│           │
│           └── res/
│               │
│               ├── drawable/
│               │   ├── bg_count.xml
│               │   ├── bg_list_item.xml
│               │   ├── bg_main.xml
│               │   ├── bg_profile.xml
│               │   ├── bg_student_card.xml
│               │   ├── bg_welcome.xml
│               │   ├── campus_background.png
│               │   ├── ic_admin.xml
│               │   ├── ic_cafeteria.xml
│               │   ├── ic_lab.xml
│               │   ├── ic_library.xml
│               │   ├── ic_sports.xml
│               │   ├── ic_launcher_background.xml
│               │   └── ic_launcher_foreground.xml
│               │
│               ├── layout/
│               │   ├── activity_main.xml
│               │   └── item_campus.xml
│               │
│               ├── mipmap-anydpi-v26/
│               │   ├── ic_launcher.xml
│               │   └── ic_launcher_round.xml
│               │
│               ├── mipmap-hdpi/
│               ├── mipmap-mdpi/
│               ├── mipmap-xhdpi/
│               ├── mipmap-xxhdpi/
│               ├── mipmap-xxxhdpi/
│               │
│               ├── values/
│               │   ├── colors.xml
│               │   ├── strings.xml
│               │   └── themes.xml
│               │
│               ├── values-night/
│               │   └── themes.xml
│               │
│               └── xml/
│                   ├── backup_rules.xml
│                   └── data_extraction_rules.xml
│
└── screenshots/
    ├── test_case_1.png
    └── test_case_2.png
```

---

# 8. Folder and File Description

| Folder/File | Description |
|---|---|
| `app/` | Main Android application module |
| `app/src/main/` | Contains the main application source code and resources |
| `MainActivity.kt` | Main activity that initializes the application and connects the ListView with the adapter |
| `CampusItem.kt` | Kotlin data class representing campus facility information |
| `CampusAdapter.kt` | Custom adapter used to display campus facilities in the ListView |
| `AndroidManifest.xml` | Contains application configuration and Android component declarations |
| `res/drawable/` | Contains application icons, backgrounds, and drawable resources |
| `res/layout/` | Contains XML layouts used by the application |
| `activity_main.xml` | Defines the main application screen |
| `item_campus.xml` | Defines the layout of an individual ListView item |
| `campus_background.png` | Academic-themed application background |
| `bg_list_item.xml` | Background design for each campus facility card |
| `bg_welcome.xml` | Background design for the welcome card |
| `bg_profile.xml` | Background design for the student profile badge |
| `bg_count.xml` | Background design for the facility count badge |
| `ic_library.xml` | Icon for Central Library |
| `ic_lab.xml` | Icon for Computer Lab |
| `ic_cafeteria.xml` | Icon for Campus Cafeteria |
| `ic_sports.xml` | Icon for Sports Complex |
| `ic_admin.xml` | Icon for Administration Block |
| `res/values/` | Contains colors, strings, and theme resources |
| `res/values-night/` | Contains resources for night mode |
| `res/xml/` | Contains Android configuration XML files |
| `androidTest/` | Contains Android instrumentation test files |
| `test/` | Contains local unit test files |
| `screenshots/` | Contains screenshots demonstrating the application and test cases |
| `build.gradle.kts` | Gradle build configuration |
| `settings.gradle.kts` | Gradle project settings |
| `gradle.properties` | Gradle configuration properties |
| `gradle/` | Gradle wrapper files |
| `gradlew` | Gradle wrapper script for Unix-based systems |
| `gradlew.bat` | Gradle wrapper script for Windows |

---

# 9. Important Application Files

## MainActivity.kt

`MainActivity.kt` is the main activity of the application.

It is responsible for:

- Loading the main XML layout
- Creating campus facility data
- Initializing the ListView
- Connecting the ListView with `CampusAdapter`
- Handling facility selection

---

## CampusItem.kt

`CampusItem.kt` defines the data structure used for each campus facility.

```kotlin
data class CampusItem(
    val name: String,
    val category: String,
    val timing: String,
    val imageResource: Int
)
```

---

## CampusAdapter.kt

`CampusAdapter.kt` is the custom adapter responsible for connecting the campus facility data to `item_campus.xml`.

It displays:

- Facility ImageView
- Facility name
- Facility category
- Facility timing

It also handles ListView item interaction.

---

## activity_main.xml

This XML file defines the main application interface.

It contains:

- Campus Explorer header
- Student profile section
- Student name
- USN
- Campus Facilities heading
- ListView

---

## item_campus.xml

This XML file defines the design of each individual ListView item.

Each item contains:

- ImageView
- Facility name
- Facility category
- Facility timing
- Navigation arrow

---

# 10. Test Cases

## Test Case 1 — Student Information Display

### Objective

To verify that the student's name and USN are displayed correctly on the application interface.

### Test Data

**Name:** Shrawan Gautam

**USN:** 25MCAR0229

### Steps

1. Launch the application.
2. Observe the Campus Explorer home screen.
3. Locate the welcome card.
4. Verify the student's name.
5. Verify the student's USN.

### Expected Result

The application should display:

```text
Shrawan Gautam
MCA • USN: 25MCAR0229
```

The information should be clearly visible on the welcome card.

### Screenshot

![Test Case 1](screenshots/test_case_1.png)

---

# 11. Test Case 2 — ListView and ImageView Display

### Objective

To verify that campus facilities are displayed correctly using ListView and ImageView.

### Steps

1. Launch the application.
2. Navigate to the Campus Facilities section.
3. Observe the ListView.
4. Verify the facility names.
5. Verify the ImageView icons.
6. Verify the category and operating time of each facility.

### Expected Result

The application should display the following campus facilities:

- Central Library
- Computer Lab
- Campus Cafeteria
- Sports Complex
- Administration Block

Each ListView item should contain an ImageView and the corresponding facility information.

### Screenshot

![Test Case 2](screenshots/test_case_2.png)

---

# 12. Test Case 3 — ListView Item Interaction

### Objective

To verify that the ListView responds when a campus facility is selected.

### Steps

1. Launch the application.
2. Tap on a campus facility such as **Central Library**.
3. Observe the response from the application.

### Expected Result

The selected ListView item should respond to the user interaction and display a Toast message indicating the selected facility.

Example:

```text
Central Library selected
```

### Screenshot Evidence

The interaction is demonstrated in the application screenshot included with the experiment.

---

# 13. Application Output

The final application provides a modern and adaptive campus interface.

The interface contains:

- Campus Explorer header
- Student information
- Student name and USN
- Campus facility ListView
- ImageView icons
- Facility information
- Interactive ListView items
- Academic-themed background
- Responsive and adaptive layout

The final UI is designed using XML layouts and Kotlin and provides a visually appealing campus exploration interface.

---

# 14. Result

The adaptive UI using **ListView and ImageView** was successfully implemented using **Kotlin and XML**.

The application successfully displays multiple campus facilities using a custom adapter and provides an interactive and visually appealing user interface.

The application also demonstrates adaptive layout techniques and proper use of Android UI components.

---

# 15. Conclusion

This experiment demonstrates the practical implementation of **ListView and ImageView** in Android application development.

A custom adapter was used to dynamically populate the ListView with campus facility information. ImageView components were used to visually represent each facility.

The application also demonstrates:

- Kotlin data classes
- Custom adapters
- XML layouts
- Drawable resources
- ListView item interaction
- Toast messages
- Adaptive Android UI design
- Responsive layout techniques

Thus, the objective of creating an adaptive UI using **ListView and ImageView** was successfully achieved.

---


## Student

**Name:** Shrawan Gautam

**USN:** 25MCAR0229

**Experiment:** 7 — Adaptive UI using ListView and ImageView

# ArtGallery – Digital Exhibition

## Experiment: Android Application using Activities, Fragments, Views, Intents and Notifications

### Scenario

An art gallery wants to create an Android application called **ArtGallery** to allow visitors to explore its digital exhibitions.

The application provides different exhibition themes:

- Paintings
- Sculptures
- Photography

When the user selects an exhibition theme, the application displays the corresponding artwork using an **Exhibition Fragment**.

The user can view the selected artwork in detail. The artwork details are displayed in a separate **Artwork Activity** using an **Intent**.

The Artwork Activity provides:

- Large artwork image
- Artist information
- Artwork title
- Complete artwork description
- Add to Favorites button

When the user selects **Add to Favorites**, the application generates an Android notification containing the artwork title.

The application also demonstrates Activity and Fragment lifecycle callbacks through Logcat.

---

# Aim

To develop an Android application named **ArtGallery** using Activities, Fragments, Views, Intents and Notifications to provide an interactive digital art exhibition experience.

---

# Objectives

The main objectives of this experiment are:

1. To understand Android Activities.
2. To implement Fragments for displaying exhibition content.
3. To use Android Views for designing the user interface.
4. To use Intents for communication between Activities.
5. To pass artwork information between screens.
6. To implement Android Notifications.
7. To demonstrate Activity and Fragment lifecycle callbacks.
8. To design an attractive and user-friendly Android interface.

---

# Concepts and Technologies Used

## 1. Activity

An Activity represents a single screen of an Android application.

In this application:

- `MainActivity` displays the main ArtGallery interface.
- `ArtworkActivity` displays complete information about the selected artwork.

---

## 2. Fragment

A Fragment represents a reusable portion of the user interface.

The application uses:

`ExhibitionFragment`

The Fragment displays the artwork corresponding to the selected exhibition theme.

The three exhibition themes are:

- Paintings
- Sculptures
- Photography

Selecting an exhibition replaces the current exhibition content with the corresponding artwork.

---

## 3. Views

Android Views are used to create the user interface.

The application uses views such as:

- TextView
- ImageView
- Button
- LinearLayout
- ScrollView
- Card-style layouts

These views are arranged to create an attractive and user-friendly interface.

---

## 4. Intent

An Intent is used to communicate between Android components.

In this application, an Intent is used when the user selects:

**View Artwork**

The Intent opens:

`ArtworkActivity`

and passes the selected artwork information to the new Activity.

---

## 5. Notification

Android Notification is used to provide feedback to the user.

When the user selects:

**Add to Favorites**

the application creates an Android notification containing the artwork title.

The notification is displayed using Android's notification system.

---

## 6. Activity and Fragment Lifecycle

The application demonstrates lifecycle callbacks of Activities and Fragments.

Important lifecycle methods include:

- `onCreate()`
- `onStart()`
- `onResume()`
- `onPause()`
- `onStop()`
- `onDestroy()`

The lifecycle execution can be observed through Android Studio Logcat.

---

# Application Flow

The application follows this flow:

```text
Start Application
       |
       v
   MainActivity
       |
       v
Select Exhibition Theme
       |
       +-------------------+
       |         |         |
       v         v         v
  Paintings  Sculptures  Photography
       |
       v
ExhibitionFragment
       |
       v
   View Artwork
       |
       v
 ArtworkActivity
       |
       +----------------------+
       |                      |
       v                      v
Artwork Details        Add to Favorites
                              |
                              v
                       Android Notification
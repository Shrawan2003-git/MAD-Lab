# SmartExpenseTracker — Expense Reminder and Balance Management Application

**Manage expenses through scheduled ringtone reminders and daily balance updates.**  
**Student:** Shrawan Gautam  
**USN:** 25MCAR0229  
**Language:** Kotlin · Native Android · XML layouts · Material 3

## Aim

Develop an Android application called **SmartExpenseTracker** that helps users manage their expenses through scheduled reminders, continuous ringtone alerts, and daily account balance updates persisted in a local SQLite database.

## Concept and technology

SmartExpenseTracker integrates core Android components and APIs:

1. **AlarmManager**: Schedules daily recurring reminder alarms (`setExactAndAllowWhileIdle` / `setAndAllowWhileIdle`).
2. **BroadcastReceiver**: [`ReminderReceiver`](app/src/main/java/com/example/test2smartexpencetracker/receiver/ReminderReceiver.kt) intercepts scheduled alarm broadcasts, fires system notifications, and plays a continuous looping alarm ringtone using [`RingtoneHelper`](app/src/main/java/com/example/test2smartexpencetracker/receiver/RingtoneHelper.kt).
3. **NotificationManager**: Posts high-priority persistent notifications prompting the user to update their account balance. Tapping the notification navigates directly to the balance entry form in [`MainActivity`](app/src/main/java/com/example/test2smartexpencetracker/MainActivity.kt).
4. **SharedPreferences**: Persists user reminder settings (`reminder_hour`, `reminder_minute`, `reminder_enabled`).
5. **SQLite (`SQLiteOpenHelper`)**: [`DatabaseHelper`](app/src/main/java/com/example/test2smartexpencetracker/data/DatabaseHelper.kt) maintains historical balance records (`balance_history` table storing `_id`, `balance`, `timestamp`, `note`).
6. **Alarm & Ringtone Management**: Saving a balance entry automatically terminates ongoing ringtone audio playback (`RingtoneHelper.stopRingtone()`), dismisses the active notification, and schedules the next daily reminder.

## Features

- Modern Material 3 aesthetic UI with indigo and teal palette.
- Persistent **Shrawan Gautam · 25MCAR0229** student identity header.
- Hero Current Balance summary card with last updated timestamp and entry count.
- Customizable reminder time picker dialog with enable/disable toggle.
- Continuous looping alarm ringtone when reminder triggers.
- Direct notification click navigation focusing the balance entry form.
- Automatic ringtone and notification termination upon saving balance update.
- SQLite balance history RecyclerView with timestamp badges and delete action.

## Folder and file structure

```text
Test2SmartExpenceTracker/
├── README.md                           Explanation, setup and architecture overview
├── .gitignore                          Generated and machine-local exclusions
├── settings.gradle.kts                 Repositories and app module configuration
├── build.gradle.kts                    Android Gradle Plugin version
├── gradle.properties                   JVM and AndroidX configuration
├── gradlew / gradlew.bat                Portable Gradle launchers
├── gradle/
│   ├── gradle-daemon-jvm.properties     Java toolchain configuration
│   └── wrapper/                        Wrapper JAR and distribution properties
├── app/
│   ├── build.gradle.kts                SDK, application and dependency configuration
│   └── src/
│       ├── main/
│       │   ├── AndroidManifest.xml     Permissions (POST_NOTIFICATIONS, ALARM) and receiver
│       │   ├── java/com/example/test2smartexpencetracker/
│       │   │   ├── MainActivity.kt     Dashboard, time picker, balance input, history list
│       │   │   ├── data/
│       │   │   │   ├── BalanceRecord.kt Data model for balance history
│       │   │   │   └── DatabaseHelper.kt SQLiteOpenHelper for balance_history
│       │   │   └── receiver/
│       │   │       ├── ReminderManager.kt AlarmManager & SharedPreferences controller
│       │   │       ├── ReminderReceiver.kt BroadcastReceiver for alarms & notifications
│       │   │       └── RingtoneHelper.kt MediaPlayer looping ringtone player & stopper
│       │   └── res/
│       │       ├── layout/             Dashboard and history card XML layouts
│       │       ├── drawable/           Vector icons (wallet, alarm, history, delete, bell)
│       │       └── values/             Colors, strings, and Material 3 themes
```

## Procedure

1. Create a Kotlin Android project and configure Gradle dependencies for Material Components and Core KTX.
2. Define the SQLite database helper (`DatabaseHelper`) with `balance_history` table.
3. Implement `ReminderManager` using `SharedPreferences` and `AlarmManager` for scheduling daily reminders.
4. Implement `ReminderReceiver` (BroadcastReceiver) to trigger high-priority notifications and play continuous ringtone audio via `RingtoneHelper`.
5. Design the Material 3 UI (`activity_main.xml` and `item_balance_record.xml`) with hero balance summary, reminder controls, and balance entry form.
6. Wire up `MainActivity` to handle time picking, SQLite insertions, notification intent launching, and automatic ringtone stopping upon saving.

## Main Implementation Code

### Ringtone & Alarm Management (`RingtoneHelper.kt`)
```kotlin
object RingtoneHelper {
    private var mediaPlayer: MediaPlayer? = null

    fun playRingtone(context: Context) {
        stopRingtone()
        try {
            val soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
                ?: RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)
            mediaPlayer = MediaPlayer().apply {
                setDataSource(context, soundUri)
                setAudioAttributes(
                    AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_ALARM)
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .build()
                )
                isLooping = true
                prepare()
                start()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun stopRingtone() {
        try {
            mediaPlayer?.let {
                if (it.isPlaying) it.stop()
                it.release()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            mediaPlayer = null
        }
    }
}
```

### BroadcastReceiver & Notification (`ReminderReceiver.kt`)
```kotlin
class ReminderReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        val reminderManager = ReminderManager(context)
        RingtoneHelper.playRingtone(context)
        createNotificationChannel(context)
        showNotification(context)
        if (reminderManager.isReminderEnabled) {
            reminderManager.scheduleDailyReminder()
        }
    }

    companion object {
        fun cancelNotification(context: Context) {
            RingtoneHelper.stopRingtone()
            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.cancel(1002)
        }
    }
}
```

## Verification Record

- **App Build**: Gradle assembleDebug build completed successfully (`PASS`).
- **Alarm & Ringtone**: Continuous ringtone playback starts upon alarm broadcast and immediately stops when user saves a balance entry.
- **SQLite Database**: Balance records correctly persist across app sessions with timestamps and optional notes.
- **Student Identity**: Shrawan Gautam (25MCAR0229).

package com.example.experiment5_notifications

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MainActivity : AppCompatActivity() {

    private lateinit var spinnerNotificationType: Spinner
    private lateinit var spinnerPriority: Spinner
    private lateinit var etNotificationTitle: EditText
    private lateinit var etNotificationMessage: EditText
    private lateinit var btnSendNotification: Button

    private val channelId = "academic_notification_channel"
    private val notificationPermissionCode = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        // Connect XML views with Kotlin
        spinnerNotificationType =
            findViewById(R.id.spinnerNotificationType)

        spinnerPriority =
            findViewById(R.id.spinnerPriority)

        etNotificationTitle =
            findViewById(R.id.etNotificationTitle)

        etNotificationMessage =
            findViewById(R.id.etNotificationMessage)

        btnSendNotification =
            findViewById(R.id.btnSendNotification)

        // Notification type options
        val notificationTypes = arrayOf(
            "Academic",
            "Assignment",
            "Examination",
            "Attendance",
            "Announcement",
            "General"
        )

        val typeAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            notificationTypes
        )

        spinnerNotificationType.adapter = typeAdapter

        // Notification priority options
        val priorities = arrayOf(
            "High",
            "Medium",
            "Low"
        )

        val priorityAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            priorities
        )

        spinnerPriority.adapter = priorityAdapter

        // Create notification channel
        createNotificationChannel()

        // Request notification permission for Android 13+
        requestNotificationPermission()

        // Send notification button
        btnSendNotification.setOnClickListener {

            val notificationType =
                spinnerNotificationType.selectedItem.toString()

            val priority =
                spinnerPriority.selectedItem.toString()

            val title =
                etNotificationTitle.text.toString().trim()

            val message =
                etNotificationMessage.text.toString().trim()

            // Validation
            if (title.isEmpty()) {

                etNotificationTitle.error =
                    "Please enter notification title"

                etNotificationTitle.requestFocus()

                return@setOnClickListener
            }

            if (message.isEmpty()) {

                etNotificationMessage.error =
                    "Please enter notification message"

                etNotificationMessage.requestFocus()

                return@setOnClickListener
            }

            // Display notification
            showNotification(
                notificationType,
                priority,
                title,
                message
            )
        }
    }

    private fun createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val channelName = "Academic Notifications"

            val channelDescription =
                "Notifications for academic activities and announcements"

            val importance =
                NotificationManager.IMPORTANCE_HIGH

            val channel = NotificationChannel(
                channelId,
                channelName,
                importance
            )

            channel.description = channelDescription

            val notificationManager =
                getSystemService(NotificationManager::class.java)

            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun showNotification(
        notificationType: String,
        priority: String,
        title: String,
        message: String
    ) {

        if (
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
            ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            Toast.makeText(
                this,
                "Please allow notification permission",
                Toast.LENGTH_LONG
            ).show()

            return
        }

        val notificationPriority =
            when (priority) {

                "High" ->
                    NotificationCompat.PRIORITY_HIGH

                "Low" ->
                    NotificationCompat.PRIORITY_LOW

                else ->
                    NotificationCompat.PRIORITY_DEFAULT
            }

        val notification = NotificationCompat.Builder(
            this,
            channelId
        )
            .setSmallIcon(
                android.R.drawable.ic_dialog_info
            )
            .setContentTitle(
                "$notificationType • $title"
            )
            .setContentText(message)
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText(message)
            )
            .setPriority(notificationPriority)
            .setAutoCancel(true)
            .build()

        NotificationManagerCompat.from(this)
            .notify(
                System.currentTimeMillis().toInt(),
                notification
            )

        Toast.makeText(
            this,
            "Notification sent successfully",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun requestNotificationPermission() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

            if (
                ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {

                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(
                        Manifest.permission.POST_NOTIFICATIONS
                    ),
                    notificationPermissionCode
                )
            }
        }
    }
}
package com.example.artgallery

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class ArtworkActivity : AppCompatActivity() {

    private val channelId = "artgallery_channel"
    private val notificationId = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("ArtGalleryLifecycle", "ArtworkActivity: onCreate()")

        setContentView(R.layout.activity_artwork)

        val title = intent.getStringExtra("TITLE") ?: "Artwork"
        val artist = intent.getStringExtra("ARTIST") ?: "Unknown Artist"
        val description =
            intent.getStringExtra("DESCRIPTION") ?: "No description available."
        val image = intent.getIntExtra("IMAGE", R.drawable.artwork_painting)

        val artworkImage =
            findViewById<android.widget.ImageView>(R.id.largeArtworkImage)

        val artworkTitle =
            findViewById<android.widget.TextView>(R.id.largeArtworkTitle)

        val artistName =
            findViewById<android.widget.TextView>(R.id.largeArtistName)

        val artworkDescription =
            findViewById<android.widget.TextView>(R.id.largeDescription)

        val favoriteButton =
            findViewById<android.widget.Button>(R.id.favoriteButton)

        artworkImage.setImageResource(image)
        artworkTitle.text = title
        artistName.text = artist
        artworkDescription.text = description

        createNotificationChannel()

        favoriteButton.setOnClickListener {
            sendFavoriteNotification(title)
        }
    }

    private fun createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val channel = NotificationChannel(
                channelId,
                "ArtGallery Favorites",
                NotificationManager.IMPORTANCE_DEFAULT
            )

            channel.description = "Notifications for favorite artworks"

            val manager = getSystemService(NotificationManager::class.java)

            manager.createNotificationChannel(channel)
        }
    }

    private fun sendFavoriteNotification(title: String) {

        if (
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
            ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                200
            )
            return
        }

        val notification = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(android.R.drawable.ic_menu_gallery)
            .setContentTitle("Added to Favorites")
            .setContentText("$title has been added to your favorites.")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .build()

        NotificationManagerCompat.from(this)
            .notify(notificationId, notification)
    }

    override fun onStart() {
        super.onStart()
        Log.d("ArtGalleryLifecycle", "ArtworkActivity: onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d("ArtGalleryLifecycle", "ArtworkActivity: onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.d("ArtGalleryLifecycle", "ArtworkActivity: onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.d("ArtGalleryLifecycle", "ArtworkActivity: onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("ArtGalleryLifecycle", "ArtworkActivity: onDestroy()")
    }
}

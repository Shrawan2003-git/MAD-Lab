package com.example.experiment4_intent

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val tvUsername = findViewById<TextView>(R.id.tvUsername)
        val tvPassword = findViewById<TextView>(R.id.tvPassword)

        val username = intent.getStringExtra("USERNAME")
        val password = intent.getStringExtra("PASSWORD")

        tvUsername.text = "Username: $username"
        tvPassword.text = "Password: $password"

        findViewById<android.widget.Button>(R.id.btnBack).setOnClickListener {
            finish()
        }
    }
}
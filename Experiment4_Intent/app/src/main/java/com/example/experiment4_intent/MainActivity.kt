package com.example.experiment4_intent

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val username = findViewById<EditText>(R.id.etUsername)
        val password = findViewById<EditText>(R.id.etPassword)
        val loginButton = findViewById<Button>(R.id.btnLogin)

        loginButton.setOnClickListener {

            val usernameValue = username.text.toString()
            val passwordValue = password.text.toString()

            val intent = Intent(this, SecondActivity::class.java)

            intent.putExtra("USERNAME", usernameValue)
            intent.putExtra("PASSWORD", passwordValue)

            startActivity(intent)
        }
    }
}
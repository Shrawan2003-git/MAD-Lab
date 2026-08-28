package com.example.experiment6_basicviews

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val tvName = findViewById<TextView>(R.id.tvResultName)
        val tvUsn = findViewById<TextView>(R.id.tvResultUsn)
        val tvEmail = findViewById<TextView>(R.id.tvResultEmail)
        val tvMobile = findViewById<TextView>(R.id.tvResultMobile)
        val tvGender = findViewById<TextView>(R.id.tvResultGender)
        val tvSpecialization = findViewById<TextView>(R.id.tvResultSpecialization)
        val tvSkills = findViewById<TextView>(R.id.tvResultSkills)
        val tvNotifications = findViewById<TextView>(R.id.tvResultNotifications)

        tvName.text = intent.getStringExtra("NAME") ?: "-"
        tvUsn.text = intent.getStringExtra("USN") ?: "-"
        tvEmail.text = intent.getStringExtra("EMAIL") ?: "-"
        tvMobile.text = intent.getStringExtra("MOBILE") ?: "-"
        tvGender.text = intent.getStringExtra("GENDER") ?: "-"
        tvSpecialization.text = intent.getStringExtra("SPECIALIZATION") ?: "-"
        tvSkills.text = intent.getStringExtra("SKILLS") ?: "-"
        tvNotifications.text = intent.getStringExtra("NOTIFICATIONS") ?: "-"

        findViewById<Button>(R.id.btnBack).setOnClickListener {
            finish()
        }
    }
}
package com.example.experiment6_basicviews

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etName = findViewById<EditText>(R.id.etName)
        val etUsn = findViewById<EditText>(R.id.etUsn)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etMobile = findViewById<EditText>(R.id.etMobile)

        val radioGender = findViewById<RadioGroup>(R.id.radioGender)
        val spinnerSpecialization = findViewById<Spinner>(R.id.spinnerSpecialization)

        val cbAi = findViewById<CheckBox>(R.id.cbAi)
        val cbWeb = findViewById<CheckBox>(R.id.cbWeb)

        val switchNotifications = findViewById<Switch>(R.id.switchNotifications)
        val cbTerms = findViewById<CheckBox>(R.id.cbTerms)

        val btnRegister = findViewById<Button>(R.id.btnRegister)

        val specializations = arrayOf(
            "Select Specialization",
            "Artificial Intelligence & Machine Learning",
            "Data Science",
            "Cyber Security",
            "Cloud Computing",
            "Software Engineering"
        )

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            specializations
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerSpecialization.adapter = adapter

        btnRegister.setOnClickListener {

            val name = etName.text.toString().trim()
            val usn = etUsn.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val mobile = etMobile.text.toString().trim()

            if (name.isEmpty()) {
                etName.error = "Enter your name"
                etName.requestFocus()
                return@setOnClickListener
            }

            if (usn.isEmpty()) {
                etUsn.error = "Enter your USN"
                etUsn.requestFocus()
                return@setOnClickListener
            }

            if (email.isEmpty()) {
                etEmail.error = "Enter your email"
                etEmail.requestFocus()
                return@setOnClickListener
            }

            if (mobile.isEmpty()) {
                etMobile.error = "Enter mobile number"
                etMobile.requestFocus()
                return@setOnClickListener
            }

            val genderId = radioGender.checkedRadioButtonId

            if (genderId == -1) {
                Toast.makeText(
                    this,
                    "Please select your gender",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            val gender = when (genderId) {
                R.id.rbMale -> "Male"
                R.id.rbFemale -> "Female"
                R.id.rbOther -> "Other"
                else -> "Not Specified"
            }

            val specialization =
                spinnerSpecialization.selectedItem.toString()

            if (spinnerSpecialization.selectedItemPosition == 0) {
                Toast.makeText(
                    this,
                    "Please select a specialization",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            val skillsList = mutableListOf<String>()

            if (cbAi.isChecked) {
                skillsList.add("Artificial Intelligence / ML")
            }

            if (cbWeb.isChecked) {
                skillsList.add("Web Development")
            }

            val skills = if (skillsList.isEmpty()) {
                "No skills selected"
            } else {
                skillsList.joinToString(", ")
            }

            if (!cbTerms.isChecked) {
                Toast.makeText(
                    this,
                    "Please accept the terms and conditions",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            val notificationStatus =
                if (switchNotifications.isChecked) {
                    "Enabled"
                } else {
                    "Disabled"
                }

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
        }
    }
}
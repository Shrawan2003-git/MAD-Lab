package com.example.experiment7_adaptiveui

import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var campusListView: ListView
    private lateinit var campusItems: ArrayList<CampusItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        campusListView = findViewById(R.id.campusListView)

        createCampusData()

        val adapter = CampusAdapter(
            this,
            campusItems
        )

        campusListView.adapter = adapter

        campusListView.setOnItemClickListener { _, _, position, _ ->

            val selectedItem = campusItems[position]

            Toast.makeText(
                this,
                "${selectedItem.name} selected",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun createCampusData() {

        campusItems = arrayListOf(

            CampusItem(
                name = "Central Library",
                category = "Books • Study Area",
                timing = "● Open until 8:00 PM",
                imageResource = R.drawable.ic_library
            ),

            CampusItem(
                name = "Computer Lab",
                category = "Technology • Practical",
                timing = "● Open until 6:00 PM",
                imageResource = R.drawable.ic_lab
            ),

            CampusItem(
                name = "Campus Cafeteria",
                category = "Food • Refreshments",
                timing = "● Open until 8:00 PM",
                imageResource = R.drawable.ic_cafeteria
            ),

            CampusItem(
                name = "Sports Complex",
                category = "Fitness • Recreation",
                timing = "● Open until 7:00 PM",
                imageResource = R.drawable.ic_sports
            ),

            CampusItem(
                name = "Administration Block",
                category = "Office • Student Services",
                timing = "● Open until 5:00 PM",
                imageResource = R.drawable.ic_admin
            )
        )
    }
}
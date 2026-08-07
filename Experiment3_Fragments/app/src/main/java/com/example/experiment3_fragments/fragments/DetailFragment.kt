package com.example.experiment3_fragments.fragments

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.experiment3_fragments.R

class DetailFragment : Fragment(R.layout.fragment_detail) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val txtDetails = view.findViewById<TextView>(R.id.txtDetails)

        val course = arguments?.getString("course")

        val details = when (course) {
            "Android" -> "Android is Google's mobile operating system used to build smartphone applications."
            "Java" -> "Java is an object-oriented programming language widely used for Android development."
            "Python" -> "Python is a simple and powerful programming language used in AI, Data Science, and Web Development."
            "Artificial Intelligence" -> "Artificial Intelligence enables computers to perform tasks that normally require human intelligence."
            else -> "No details available."
        }

        txtDetails.text = details
    }
}
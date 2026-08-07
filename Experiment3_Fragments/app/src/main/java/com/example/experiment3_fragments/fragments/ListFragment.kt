package com.example.experiment3_fragments.fragments

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.example.experiment3_fragments.R

class ListFragment : Fragment(R.layout.fragment_list) {

    private val courses = arrayOf(
        "Android",
        "Java",
        "Python",
        "Artificial Intelligence",
        "25MCAR0229 Shrawan Gautam"
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listView = view.findViewById<ListView>(R.id.listViewCourses)

        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            courses
        )

        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, position, _ ->

            val detailFragment = DetailFragment()

            val bundle = Bundle()
            bundle.putString("course", courses[position])

            detailFragment.arguments = bundle

            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, detailFragment)
                .addToBackStack(null)
                .commit()
        }
    }
}
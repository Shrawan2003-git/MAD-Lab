package com.example.experiment7_adaptiveui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class CampusAdapter(
    context: Context,
    private val campusItems: List<CampusItem>
) : ArrayAdapter<CampusItem>(context, 0, campusItems) {

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup
    ): View {

        val view = convertView
            ?: LayoutInflater.from(context)
                .inflate(
                    R.layout.item_campus,
                    parent,
                    false
                )

        val imageView = view.findViewById<ImageView>(
            R.id.imgCampus
        )

        val nameText = view.findViewById<TextView>(
            R.id.txtCampusName
        )

        val categoryText = view.findViewById<TextView>(
            R.id.txtCampusCategory
        )

        val timingText = view.findViewById<TextView>(
            R.id.txtCampusTime
        )

        val item = campusItems[position]

        imageView.setImageResource(item.imageResource)
        when (position) {
            0 -> imageView.setBackgroundColor(
                context.getColor(R.color.library_bg)
            )

            1 -> imageView.setBackgroundColor(
                context.getColor(R.color.lab_bg)
            )

            2 -> imageView.setBackgroundColor(
                context.getColor(R.color.cafeteria_bg)
            )

            3 -> imageView.setBackgroundColor(
                context.getColor(R.color.sports_bg)
            )

            4 -> imageView.setBackgroundColor(
                context.getColor(R.color.admin_bg)
            )
        }

        nameText.text = item.name
        categoryText.text = item.category
        timingText.text = item.timing

        return view
    }
}
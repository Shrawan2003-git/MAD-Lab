package com.example.artgallery

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class ExhibitionFragment : Fragment() {

    private var theme = "Paintings"
    private var artworkTitle = ""
    private var artistName = ""
    private var description = ""
    private var imageResId = 0

    companion object {
        private const val ARG_THEME = "theme"

        fun newInstance(theme: String): ExhibitionFragment {
            val fragment = ExhibitionFragment()
            val bundle = Bundle().apply {
                putString(ARG_THEME, theme)
            }
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("ArtGalleryLifecycle", "ExhibitionFragment: onCreate()")

        arguments?.let {
            theme = it.getString(ARG_THEME, "Paintings")
        }

        loadDataForTheme(theme)
    }

    private fun loadDataForTheme(selectedTheme: String) {
        when (selectedTheme) {
            "Sculptures" -> {
                artworkTitle = getString(R.string.sculpture_title)
                artistName = getString(R.string.sculpture_artist)
                description = getString(R.string.sculpture_description)
                imageResId = R.drawable.artwork_sculpture
            }
            "Photography" -> {
                artworkTitle = getString(R.string.photography_title)
                artistName = getString(R.string.photography_artist)
                description = getString(R.string.photography_description)
                imageResId = R.drawable.artwork_photography
            }
            else -> { // "Paintings" or default
                artworkTitle = getString(R.string.painting_title)
                artistName = getString(R.string.painting_artist)
                description = getString(R.string.painting_description)
                imageResId = R.drawable.artwork_painting
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("ArtGalleryLifecycle", "ExhibitionFragment: onCreateView()")
        return inflater.inflate(
            R.layout.fragment_exhibition,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("ArtGalleryLifecycle", "ExhibitionFragment: onViewCreated()")

        val image = view.findViewById<android.widget.ImageView>(R.id.artworkImage)
        val title = view.findViewById<android.widget.TextView>(R.id.artworkTitle)
        val artist = view.findViewById<android.widget.TextView>(R.id.artistName)
        val categoryText = view.findViewById<android.widget.TextView>(R.id.categoryText)
        val descriptionText = view.findViewById<android.widget.TextView>(R.id.descriptionText)
        val viewButton = view.findViewById<android.widget.Button>(R.id.viewArtworkButton)

        image.setImageResource(imageResId)
        title.text = artworkTitle
        artist.text = artistName
        categoryText.text = theme.uppercase()
        descriptionText.text = description

        viewButton.setOnClickListener {
            val intent = Intent(requireContext(), ArtworkActivity::class.java).apply {
                putExtra("TITLE", artworkTitle)
                putExtra("ARTIST", artistName)
                putExtra("DESCRIPTION", description)
                putExtra("IMAGE", imageResId)
            }
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("ArtGalleryLifecycle", "ExhibitionFragment: onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d("ArtGalleryLifecycle", "ExhibitionFragment: onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.d("ArtGalleryLifecycle", "ExhibitionFragment: onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.d("ArtGalleryLifecycle", "ExhibitionFragment: onStop()")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("ArtGalleryLifecycle", "ExhibitionFragment: onDestroyView()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("ArtGalleryLifecycle", "ExhibitionFragment: onDestroy()")
    }
}

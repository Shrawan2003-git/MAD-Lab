package com.example.artgallery

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "ArtGallery"
        private const val FRAGMENT_CONTAINER_ID = 1001
    }

    private lateinit var titleText: TextView
    private lateinit var subtitleText: TextView
    private lateinit var paintingsButton: Button
    private lateinit var sculpturesButton: Button
    private lateinit var photographyButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG, "MainActivity: onCreate()")

        createMainUI()

        // Display Paintings by default
        if (savedInstanceState == null) {
            showExhibition("Paintings")
        }
    }

    /**
     * Creates the main ArtGallery screen programmatically.
     */
    private fun createMainUI() {

        val rootLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(24, 40, 24, 20)
            setBackgroundColor(
                ContextCompat.getColor(
                    this@MainActivity,
                    R.color.gallery_background
                )
            )
        }

        // -------------------------------
        // App Title
        // -------------------------------

        titleText = TextView(this).apply {
            text = "ArtGallery"
            textSize = 32f
            setTextColor(
                ContextCompat.getColor(
                    this@MainActivity,
                    R.color.gallery_text_primary
                )
            )
            gravity = Gravity.CENTER
            setTypeface(null, android.graphics.Typeface.BOLD)
        }

        rootLayout.addView(
            titleText,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        )

        // -------------------------------
        // Subtitle
        // -------------------------------

        subtitleText = TextView(this).apply {
            text = "Explore Curated Digital Exhibitions"
            textSize = 15f
            setTextColor(
                ContextCompat.getColor(
                    this@MainActivity,
                    R.color.gallery_text_secondary
                )
            )
            gravity = Gravity.CENTER
            setPadding(0, 6, 0, 28)
        }

        rootLayout.addView(
            subtitleText,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        )

        // -------------------------------
        // Exhibition Buttons (Tabs)
        // -------------------------------

        val buttonLayout = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER
            setPadding(0, 0, 0, 10)
        }

        paintingsButton = createExhibitionButton("Paintings")
        sculpturesButton = createExhibitionButton("Sculptures")
        photographyButton = createExhibitionButton("Photography")

        paintingsButton.setOnClickListener {
            Log.d(TAG, "Paintings selected")
            showExhibition("Paintings")
        }

        sculpturesButton.setOnClickListener {
            Log.d(TAG, "Sculptures selected")
            showExhibition("Sculptures")
        }

        photographyButton.setOnClickListener {
            Log.d(TAG, "Photography selected")
            showExhibition("Photography")
        }

        buttonLayout.addView(
            paintingsButton,
            createButtonParams()
        )

        buttonLayout.addView(
            sculpturesButton,
            createButtonParams()
        )

        buttonLayout.addView(
            photographyButton,
            createButtonParams()
        )

        rootLayout.addView(
            buttonLayout,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        )

        // -------------------------------
        // Fragment Container
        // -------------------------------

        val fragmentContainer = android.widget.FrameLayout(this).apply {
            id = FRAGMENT_CONTAINER_ID
            setBackgroundColor(
                ContextCompat.getColor(
                    this@MainActivity,
                    R.color.gallery_background
                )
            )
        }

        val containerParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            0
        )

        containerParams.weight = 1f
        containerParams.topMargin = 16

        rootLayout.addView(
            fragmentContainer,
            containerParams
        )

        setContentView(rootLayout)
    }

    /**
     * Creates an exhibition category tab button.
     */
    private fun createExhibitionButton(text: String): Button {
        return Button(this).apply {
            this.text = text
            textSize = 13f
            isAllCaps = false
            elevation = 2f
            setBackgroundResource(R.drawable.bg_button)
        }
    }

    /**
     * Button layout parameters with margins.
     */
    private fun createButtonParams(): LinearLayout.LayoutParams {
        return LinearLayout.LayoutParams(
            0,
            LinearLayout.LayoutParams.WRAP_CONTENT
        ).apply {
            weight = 1f
            marginStart = 6
            marginEnd = 6
        }
    }

    /**
     * Replaces the current fragment with the selected exhibition and updates tab states.
     */
    private fun showExhibition(theme: String) {
        Log.d(TAG, "Opening ExhibitionFragment: $theme")

        // Update Tab States
        updateTabStyles(theme)

        val fragment = ExhibitionFragment.newInstance(theme)

        supportFragmentManager
            .beginTransaction()
            .replace(FRAGMENT_CONTAINER_ID, fragment)
            .commit()
    }

    private fun updateTabStyles(selectedTheme: String) {
        val selectedBg = ContextCompat.getColor(this, R.color.gallery_selected_tab)
        val unselectedBg = ContextCompat.getColor(this, R.color.gallery_unselected_tab)
        val selectedText = ContextCompat.getColor(this, R.color.gallery_selected_tab_text)
        val unselectedText = ContextCompat.getColor(this, R.color.gallery_unselected_tab_text)

        // Paintings
        paintingsButton.setBackgroundColor(if (selectedTheme == "Paintings") selectedBg else unselectedBg)
        paintingsButton.setTextColor(if (selectedTheme == "Paintings") selectedText else unselectedText)

        // Sculptures
        sculpturesButton.setBackgroundColor(if (selectedTheme == "Sculptures") selectedBg else unselectedBg)
        sculpturesButton.setTextColor(if (selectedTheme == "Sculptures") selectedText else unselectedText)

        // Photography
        photographyButton.setBackgroundColor(if (selectedTheme == "Photography") selectedBg else unselectedBg)
        photographyButton.setTextColor(if (selectedTheme == "Photography") selectedText else unselectedText)
    }

    // ---------------------------------------
    // Activity Lifecycle Methods
    // ---------------------------------------

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "MainActivity: onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "MainActivity: onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "MainActivity: onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "MainActivity: onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "MainActivity: onDestroy()")
    }
}

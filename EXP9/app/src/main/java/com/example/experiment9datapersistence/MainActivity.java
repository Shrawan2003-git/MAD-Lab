
package com.example.experiment9datapersistence;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Typeface;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends Activity {

    private EditText etName, etUsn;
    private EditText etTitle, etDescription;

    private TextView tvProfileStatus;
    private TextView tvNoteStatus;
    private TextView tvNoteCount;

    private LinearLayout notesContainer;
    private LinearLayout emptyState;

    private SharedPreferences preferences;
    private DatabaseHelper databaseHelper;

    private final int navy = Color.rgb(23, 37, 84);
    private final int muted = Color.rgb(100, 116, 139);
    private final int blue = Color.rgb(59, 130, 246);
    private final int purple = Color.rgb(147, 51, 234);
    private final int pink = Color.rgb(236, 72, 153);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etUsn = findViewById(R.id.etUsn);
        etTitle = findViewById(R.id.etTitle);
        etDescription = findViewById(R.id.etDescription);

        tvProfileStatus = findViewById(R.id.tvProfileStatus);
        tvNoteStatus = findViewById(R.id.tvNoteStatus);
        tvNoteCount = findViewById(R.id.tvNoteCount);

        notesContainer = findViewById(R.id.notesContainer);
        emptyState = findViewById(R.id.emptyState);

        preferences = getSharedPreferences(
                "StudentPrefs", MODE_PRIVATE
        );

        databaseHelper = new DatabaseHelper(this);

        loadProfile();
        loadNotes();

        Button btnSaveProfile = findViewById(
                R.id.btnSaveProfile
        );
        Button btnSaveNote = findViewById(
                R.id.btnSaveNote
        );

        btnSaveProfile.setOnClickListener(v -> saveProfile());
        btnSaveNote.setOnClickListener(v -> saveNote());
    }

    private void saveProfile() {
        String name = etName.getText().toString().trim();
        String usn = etUsn.getText().toString()
                .trim().toUpperCase(Locale.ROOT);

        if (name.isEmpty() || usn.isEmpty()) {
            Toast.makeText(
                    this,
                    "Please enter your name and USN",
                    Toast.LENGTH_SHORT
            ).show();
            return;
        }

        preferences.edit()
                .putString("student_name", name)
                .putString("student_usn", usn)
                .apply();

        etUsn.setText(usn);

        tvProfileStatus.setText(
                "✓  Profile saved successfully on this device"
        );
        tvProfileStatus.setTextColor(
                Color.rgb(22, 101, 52)
        );
        tvProfileStatus.setBackgroundResource(
                R.drawable.bg_icon_blue
        );

        Toast.makeText(
                this,
                "Student profile saved!",
                Toast.LENGTH_SHORT
        ).show();
    }

    private void loadProfile() {
        etName.setText(
                preferences.getString("student_name", "")
        );

        etUsn.setText(
                preferences.getString("student_usn", "")
        );
    }

    private void saveNote() {
        String title = etTitle.getText().toString().trim();
        String description = etDescription.getText()
                .toString().trim();

        if (title.isEmpty() || description.isEmpty()) {
            Toast.makeText(
                    this,
                    "Please enter a title and description",
                    Toast.LENGTH_SHORT
            ).show();
            return;
        }

        long id = databaseHelper.insertNote(
                title, description
        );

        if (id != -1) {
            etTitle.setText("");
            etDescription.setText("");

            tvNoteStatus.setText(
                    "✓  Note saved successfully to SQLite"
            );
            tvNoteStatus.setTextColor(
                    Color.rgb(88, 28, 135)
            );

            loadNotes();

            Toast.makeText(
                    this,
                    "Note saved successfully!",
                    Toast.LENGTH_SHORT
            ).show();
        } else {
            Toast.makeText(
                    this,
                    "Unable to save note",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }

    private void loadNotes() {
        notesContainer.removeAllViews();

        Cursor cursor = databaseHelper.getAllNotes();
        int count = 0;

        try {
            if (cursor.moveToFirst()) {
                do {
                    long id = cursor.getLong(
                            cursor.getColumnIndexOrThrow(
                                    DatabaseHelper.COL_ID
                            )
                    );

                    String title = cursor.getString(
                            cursor.getColumnIndexOrThrow(
                                    DatabaseHelper.COL_TITLE
                            )
                    );

                    String description = cursor.getString(
                            cursor.getColumnIndexOrThrow(
                                    DatabaseHelper.COL_DESCRIPTION
                            )
                    );

                    String date = cursor.getString(
                            cursor.getColumnIndexOrThrow(
                                    DatabaseHelper.COL_DATE
                            )
                    );

                    addNoteCard(id, title, description, date);
                    count++;

                } while (cursor.moveToNext());
            }
        } finally {
            cursor.close();
        }

        emptyState.setVisibility(
                count == 0 ? View.VISIBLE : View.GONE
        );

        tvNoteCount.setText(
                count == 1
                        ? "1 note in your study collection"
                        : count + " notes in your study collection"
        );
    }

    private void addNoteCard(
            long id,
            String title,
            String description,
            String date) {

        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setPadding(dp(15), dp(15), dp(15), dp(15));
        card.setBackgroundResource(R.drawable.bg_note_card);
        card.setElevation(dp(2));

        LinearLayout.LayoutParams cardParams =
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );

        cardParams.bottomMargin = dp(12);
        notesContainer.addView(card, cardParams);

        LinearLayout topRow = new LinearLayout(this);
        topRow.setOrientation(LinearLayout.HORIZONTAL);
        topRow.setGravity(Gravity.CENTER_VERTICAL);
        card.addView(topRow);

        TextView icon = new TextView(this);
        icon.setText("📄");
        icon.setTextSize(22);
        icon.setGravity(Gravity.CENTER);
        icon.setBackgroundResource(R.drawable.bg_icon_purple);

        LinearLayout.LayoutParams iconParams =
                new LinearLayout.LayoutParams(dp(46), dp(46));
        topRow.addView(icon, iconParams);

        LinearLayout titleArea = new LinearLayout(this);
        titleArea.setOrientation(LinearLayout.VERTICAL);
        titleArea.setPadding(dp(12), 0, 0, 0);

        LinearLayout.LayoutParams titleParams =
                new LinearLayout.LayoutParams(
                        0,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        1
                );
        topRow.addView(titleArea, titleParams);

        TextView titleView = new TextView(this);
        titleView.setText(title);
        titleView.setTextColor(navy);
        titleView.setTextSize(16);
        titleView.setTypeface(null, Typeface.BOLD);
        titleArea.addView(titleView);

        TextView dateView = new TextView(this);
        dateView.setText(formatDate(date));
        dateView.setTextColor(muted);
        dateView.setTextSize(11);
        dateView.setPadding(0, dp(4), 0, 0);
        titleArea.addView(dateView);

        Button deleteButton = new Button(this);
        deleteButton.setText("⋮");
        deleteButton.setTextColor(navy);
        deleteButton.setTextSize(22);
        deleteButton.setAllCaps(false);
        deleteButton.setMinWidth(0);
        deleteButton.setMinimumWidth(0);
        deleteButton.setMinHeight(0);
        deleteButton.setMinimumHeight(0);
        deleteButton.setPadding(dp(8), 0, dp(8), 0);
        deleteButton.setBackgroundColor(Color.TRANSPARENT);
        topRow.addView(deleteButton);

        deleteButton.setOnClickListener(v ->
                new AlertDialog.Builder(this)
                        .setTitle("Delete note?")
                        .setMessage(
                                "This note will be permanently deleted."
                        )
                        .setNegativeButton("Cancel", null)
                        .setPositiveButton("Delete", (dialog, which) -> {
                            if (databaseHelper.deleteNote(id)) {
                                loadNotes();
                                Toast.makeText(
                                        this,
                                        "Note deleted",
                                        Toast.LENGTH_SHORT
                                ).show();
                            }
                        })
                        .show()
        );

        TextView descriptionView = new TextView(this);
        descriptionView.setText(description);
        descriptionView.setTextColor(navy);
        descriptionView.setTextSize(14);
        descriptionView.setLineSpacing(dp(3), 1f);
        descriptionView.setPadding(0, dp(12), 0, 0);
        card.addView(descriptionView);
    }

    private String formatDate(String rawDate) {
        try {
            long timestamp = Long.parseLong(rawDate);
            return new SimpleDateFormat(
                    "dd MMM yyyy, hh:mm a",
                    Locale.getDefault()
            ).format(new Date(timestamp));
        } catch (Exception e) {
            return "Saved study note";
        }
    }

    private int dp(float value) {
        return (int) (value * getResources()
                .getDisplayMetrics().density + 0.5f);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (databaseHelper != null) {
            databaseHelper.close();
        }
    }
}

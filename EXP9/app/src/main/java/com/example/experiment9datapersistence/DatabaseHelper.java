
package com.example.experiment9datapersistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "StudentNotes.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NOTES = "notes";
    public static final String COL_ID = "id";
    public static final String COL_TITLE = "title";
    public static final String COL_DESCRIPTION = "description";
    public static final String COL_DATE = "created_at";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NOTES + " ("
                + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_TITLE + " TEXT NOT NULL, "
                + COL_DESCRIPTION + " TEXT NOT NULL, "
                + COL_DATE + " TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP"
                + ")";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(
            SQLiteDatabase db,
            int oldVersion,
            int newVersion) {
        // Add database migrations here when the schema changes.
    }

    public long insertNote(String title, String description) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_TITLE, title);
        values.put(COL_DESCRIPTION, description);
        values.put(COL_DATE, String.valueOf(
                System.currentTimeMillis()
        ));

        return db.insert(TABLE_NOTES, null, values);
    }

    public Cursor getAllNotes() {
        SQLiteDatabase db = getReadableDatabase();

        return db.query(
                TABLE_NOTES,
                null,
                null,
                null,
                null,
                null,
                COL_ID + " DESC"
        );
    }

    public boolean deleteNote(long id) {
        SQLiteDatabase db = getWritableDatabase();

        return db.delete(
                TABLE_NOTES,
                COL_ID + " = ?",
                new String[]{String.valueOf(id)}
        ) > 0;
    }
}

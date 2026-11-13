package com.example.test2smartexpencetracker.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "smart_expense_tracker.db"
        private const val DATABASE_VERSION = 1

        const val TABLE_BALANCE = "balance_history"
        const val COLUMN_ID = "_id"
        const val COLUMN_BALANCE = "balance"
        const val COLUMN_TIMESTAMP = "timestamp"
        const val COLUMN_NOTE = "note"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableQuery = """
            CREATE TABLE $TABLE_BALANCE (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_BALANCE REAL NOT NULL,
                $COLUMN_TIMESTAMP TEXT NOT NULL,
                $COLUMN_NOTE TEXT
            )
        """.trimIndent()
        db.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_BALANCE")
        onCreate(db)
    }

    fun insertBalanceRecord(balance: Double, timestamp: String, note: String = ""): Long {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_BALANCE, balance)
            put(COLUMN_TIMESTAMP, timestamp)
            put(COLUMN_NOTE, note)
        }
        val id = db.insert(TABLE_BALANCE, null, values)
        db.close()
        return id
    }

    fun getAllBalanceRecords(): List<BalanceRecord> {
        val list = mutableListOf<BalanceRecord>()
        val db = this.readableDatabase
        val cursor = db.query(
            TABLE_BALANCE,
            null,
            null,
            null,
            null,
            null,
            "$COLUMN_ID DESC"
        )

        if (cursor.moveToFirst()) {
            val idIndex = cursor.getColumnIndexOrThrow(COLUMN_ID)
            val balanceIndex = cursor.getColumnIndexOrThrow(COLUMN_BALANCE)
            val timestampIndex = cursor.getColumnIndexOrThrow(COLUMN_TIMESTAMP)
            val noteIndex = cursor.getColumnIndexOrThrow(COLUMN_NOTE)

            do {
                val record = BalanceRecord(
                    id = cursor.getLong(idIndex),
                    balance = cursor.getDouble(balanceIndex),
                    timestamp = cursor.getString(timestampIndex),
                    note = cursor.getString(noteIndex) ?: ""
                )
                list.add(record)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return list
    }

    fun getLatestBalanceRecord(): BalanceRecord? {
        val db = this.readableDatabase
        val cursor = db.query(
            TABLE_BALANCE,
            null,
            null,
            null,
            null,
            null,
            "$COLUMN_ID DESC",
            "1"
        )

        var record: BalanceRecord? = null
        if (cursor.moveToFirst()) {
            val idIndex = cursor.getColumnIndexOrThrow(COLUMN_ID)
            val balanceIndex = cursor.getColumnIndexOrThrow(COLUMN_BALANCE)
            val timestampIndex = cursor.getColumnIndexOrThrow(COLUMN_TIMESTAMP)
            val noteIndex = cursor.getColumnIndexOrThrow(COLUMN_NOTE)

            record = BalanceRecord(
                id = cursor.getLong(idIndex),
                balance = cursor.getDouble(balanceIndex),
                timestamp = cursor.getString(timestampIndex),
                note = cursor.getString(noteIndex) ?: ""
            )
        }
        cursor.close()
        db.close()
        return record
    }

    fun deleteBalanceRecord(id: Long): Boolean {
        val db = this.writableDatabase
        val rows = db.delete(TABLE_BALANCE, "$COLUMN_ID = ?", arrayOf(id.toString()))
        db.close()
        return rows > 0
    }
}

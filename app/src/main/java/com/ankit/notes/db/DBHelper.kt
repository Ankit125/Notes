package com.ankit.notes.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper (context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_NAME = "NotesDB.db"
        private const val DATABASE_VERSION = 1

        private const val CREATE_TABLE_NOTES = """
            CREATE TABLE notes (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                notes TEXT,
                date TEXT
            )
        """
    }

    override fun onCreate(db: SQLiteDatabase) {
        // create the employee table
        db.execSQL(CREATE_TABLE_NOTES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // drop the employee table if it exists
        db.execSQL("DROP TABLE IF EXISTS notes")

        // create the new employee table
        onCreate(db)
    }
}
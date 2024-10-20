package com.ankit.notes.db

import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.ankit.notes.NotesModel

class NotesDB (context: Context) {

    private val databaseHelper = DBHelper(context)

    fun insert(notes: String, dateTime: String){
        val db = databaseHelper.writableDatabase
        val values = ContentValues().apply {
            put("notes",notes)
            put("date",dateTime)
        }
        val test: Long = db.insert("notes",null, values)
        Log.d("asasdsddfe",test.toString())
        db.close()
    }

    fun getNotes(): List<NotesModel> {
        val list = mutableListOf<NotesModel>()

        val db = databaseHelper.writableDatabase
        val cursor = db.rawQuery("SELECT * from notes",null)

        while(cursor.moveToNext()) {

            val id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
            val notes = cursor.getString(cursor.getColumnIndexOrThrow("notes"))
            val date = cursor.getString(cursor.getColumnIndexOrThrow("date"))

            val notesModel = NotesModel()
            notesModel.id = id
            notesModel.notes = notes
            notesModel.dateTime = date
            list.add(notesModel)
        }
        cursor.close()
        db.close()
        return list
    }

    fun update (id: Int, notes: String, dateTime: String){
        val db = databaseHelper.writableDatabase
        val values = ContentValues().apply {
            put("notes", notes)
            put("date", dateTime)
        }
        db.update("notes",values, "id = ?", arrayOf(id.toString()))

        db.close()
    }

    fun delete(id: Int) {
        val db = databaseHelper.writableDatabase
        db.delete("notes", "id = ?", arrayOf(id.toString()))
        db.close()
    }

}
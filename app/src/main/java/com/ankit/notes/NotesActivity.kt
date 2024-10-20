package com.ankit.notes

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ankit.notes.databinding.ActivityNotesBinding
import com.ankit.notes.db.NotesDB
import com.google.android.material.floatingactionbutton.FloatingActionButton


class NotesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNotesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener { view ->
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        super.onBackPressed()
        //Toast.makeText(application,"Good By!", Toast.LENGTH_LONG).show()
    }

    override fun onStart() {
        super.onStart()

        val recyclerViewNotes = findViewById<RecyclerView>(R.id.recNotes)
        recyclerViewNotes.layoutManager = LinearLayoutManager(this)
        val adapter = CustomAdapter(loadNotes())
        recyclerViewNotes.adapter = adapter
        adapter!!.notifyDataSetChanged()
    }

    fun loadNotes(): List<NotesModel>{
        val db : NotesDB = NotesDB(this)
        val notesList = db.getNotes()
        return notesList
    }
}
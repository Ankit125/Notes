package com.ankit.notes

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ankit.notes.databinding.ActivityNotesBinding
import com.ankit.notes.db.NotesDB
import com.google.android.material.floatingactionbutton.FloatingActionButton


class NotesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNotesBinding
    private lateinit var recyclerViewNotes: RecyclerView
    private lateinit var adapter: CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        recyclerViewNotes = findViewById<RecyclerView>(R.id.recNotes)

        recyclerViewNotes.layoutManager = LinearLayoutManager(this)


        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener { view ->
            startActivity(Intent(this, MainActivity::class.java))
        }

    }

    private fun onListItemClick(item:NotesModel){
        Toast.makeText(application, item.notes, Toast.LENGTH_SHORT).show()
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        super.onBackPressed()
        //Toast.makeText(application,"Good By!", Toast.LENGTH_LONG).show()
    }

    override fun onStart() {
        super.onStart()
        loadNotes()
    }

    private fun loadNotes(){
        val db : NotesDB = NotesDB(this)
        val notesList = db.getNotes()
        val adapter = CustomAdapter(notesList) { item ->
            onListItemClick(item)
        }

        recyclerViewNotes.adapter = adapter
        adapter!!.notifyDataSetChanged()

    }
}
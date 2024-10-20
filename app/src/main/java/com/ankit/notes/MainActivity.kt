package com.ankit.notes

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.ankit.notes.databinding.ActivityMainBinding
import com.ankit.notes.db.NotesDB
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        val db : NotesDB = NotesDB(this)
        val button = findViewById<Button>(R.id.btnSave)
        val txtNotes = findViewById<EditText>(R.id.txtNotes)

        button.setOnClickListener {
            if(txtNotes.text.isEmpty()){
                Toast.makeText(application,"Please enter your Notes.",Toast.LENGTH_LONG).show()
            } else {
                val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
                val current = LocalDateTime.now().format(formatter)
                db.insert(txtNotes.text.toString(),current)
                Toast.makeText(application,"Your Notes is saved.",Toast.LENGTH_LONG).show()
            }
        }
    }
}
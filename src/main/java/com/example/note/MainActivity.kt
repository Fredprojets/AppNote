package com.example.note

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        NoteList.adapter = ArrayAdapter<InfoNote>(this,android.R.layout.simple_list_item_1, DataManager.LesNotes.toList())

        NoteList.setOnItemClickListener { parent, view, position, id ->
            var ouvreur = Intent(this, NoteTaking::class.java)
            ouvreur.putExtra(NoteSelectionnee,  position)
            startActivity(ouvreur)
        }

        addNote.setOnClickListener {
            startActivity(Intent(this, NoteTaking::class.java))

        }
    }
    override fun onResume() {
        super.onResume()
        (NoteList.adapter as ArrayAdapter<InfoCours>).notifyDataSetChanged()
    }

}

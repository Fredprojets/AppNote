package com.example.note

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.core.view.get
import kotlinx.android.synthetic.main.activity_note_taking.*
import org.jetbrains.annotations.Nullable

class NoteTaking : AppCompatActivity() {
    private var NoteCourante = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_taking)

        val adaptateur =  ArrayAdapter<InfoCours>(this,android.R.layout.simple_spinner_item, DataManager.LesCours.values.toList())
        adaptateur.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCours.adapter = adaptateur


        NoteCourante = savedInstanceState?.getInt(NoteSelectionnee) ?: intent.getIntExtra(NoteSelectionnee, -1)

        if (NoteCourante != -1){
            displayNote()
        }
         else {
            DataManager.LesNotes.add(InfoNote())
            NoteCourante = DataManager.LesNotes.lastIndex
        }

        SaveNote.setOnClickListener {
            saveNote()
            startActivity(Intent(this,MainActivity::class.java))
        }
    }

    private fun displayNote() {
        val note = DataManager.LesNotes[NoteCourante]
        Titre.setText(note.titre)
        Note.setText(note.contenu)

        val positionCours = DataManager.LesCours.values.indexOf(note.cours)
        spinnerCours.setSelection(positionCours)
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            R.id.action_precedent -> {
                movePrecedent()
                true
            }
            R.id.action_suivant -> {
                moveSuivant()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun moveSuivant() {
        ++NoteCourante
        displayNote()
        invalidateOptionsMenu()
    }

    private fun movePrecedent() {
        --NoteCourante
        displayNote()
        invalidateOptionsMenu()
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        if (NoteCourante >= DataManager.LesNotes.lastIndex){
            val menuItem = menu?.findItem(R.id.action_suivant)
            if (menuItem != null) {
                menuItem.icon = getDrawable(R.drawable.ic_block_white_24dp)
                menuItem.isEnabled = false
            }
        }
        if (NoteCourante == 0){
            val menuItem = menu?.findItem(R.id.action_precedent)
            if (menuItem != null) {
                menuItem.icon = getDrawable(R.drawable.ic_block_white_24dp)
                menuItem.isEnabled = false
            }
        }
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onPause() {
        super.onPause()
        saveNote()
    }

    private fun saveNote() {
        val note =   DataManager.LesNotes[NoteCourante]
        note.cours = spinnerCours.selectedItem as InfoCours
        note.titre = Titre.text.toString()
        note.contenu = Note.text.toString()
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putInt(NoteSelectionnee,NoteCourante)
    }

}

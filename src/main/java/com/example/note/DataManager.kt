package com.example.note

object DataManager {
    var LesCours = HashMap<Int, InfoCours>()
    var LesNotes = ArrayList<InfoNote>()

    init {
        population()

    }

    private fun population(){
        val cours1 = InfoCours(1, "cours 1")
        LesCours[cours1.id] = cours1

        val cours2 = InfoCours(2, "cours 2")
        LesCours[cours2.id] = cours2

        val cours3 = InfoCours(3, "cours 3")
        LesCours[cours3.id] = cours3

        val cours4 = InfoCours(4, "cours 4")
        LesCours[cours4.id] = cours4

        val note1 = InfoNote(cours1, "titre1", "À remplir 1")
        LesNotes.add(note1)

        val note2 = InfoNote(cours2, "titre2", "À remplir 2")
        LesNotes.add(note2)

        val note3 = InfoNote(cours3, "titre3", "À remplir 3")
        LesNotes.add(note3)

        val note4 = InfoNote(cours4, "titre4", "À remplir 4")
        LesNotes.add(note4)
    }
}
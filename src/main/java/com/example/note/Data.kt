package com.example.note

data class InfoCours(val id:Int, val nom:String){
    override fun toString(): String {
        return this.nom
    }
}

data class InfoNote (var cours:InfoCours? = null, var titre: String? = null, var contenu:String? = null)

const val NoteSelectionnee = "NoteSelectionnée"
const val NoteCourante = "NoteCourante"
val NoteVide = null
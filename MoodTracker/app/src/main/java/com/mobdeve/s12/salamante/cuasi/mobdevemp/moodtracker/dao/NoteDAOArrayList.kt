package com.mobdeve.s12.salamante.cuasi.mobdevemp.moodtracker.dao

import com.mobdeve.s12.salamante.cuasi.mobdevemp.moodtracker.model.Note
import java.util.ArrayList

class NoteDAOArrayList: NoteDAO {
    var noteList = ArrayList<Note?>()

    init{
//        userList.add(User("Marco", "Valmores", "10114246"))
//        userList.add(User("Miguel", "Ruelos", "11828269"))
//        userList.add(User("Aki", "Punzalan", "11832711"))
//        userList.add(User("Czarina", "Tiu", "11938099"))
//        userList.add(User("Angel", "Dorde", "11927208"))
    }

    override fun addNote(note: Note?): Long {
        noteList.add(note!!)
        return 1L
    }

    override fun getNotes(): ArrayList<Note?>? = noteList

    override fun getNote(noteid: Int): Note? {
        TODO("Not yet implemented")
    }

    override fun updateNote(note: Note?): Int {
        TODO("Not yet implemented")
    }
}
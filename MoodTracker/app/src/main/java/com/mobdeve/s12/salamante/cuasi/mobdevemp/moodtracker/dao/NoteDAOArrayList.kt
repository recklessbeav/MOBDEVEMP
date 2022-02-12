package com.mobdeve.s12.salamante.cuasi.mobdevemp.moodtracker.dao

import com.mobdeve.s12.salamante.cuasi.mobdevemp.moodtracker.model.Note
import java.util.ArrayList

class NoteDAOArrayList: NoteDAO {
    var noteList = ArrayList<Note?>()

    override fun addNote(note: Note?): Long {
        noteList.add(note!!)
        return 1L
    }

    override fun getNotes(): ArrayList<Note?>? = noteList

    override fun getNote(noteid: Int): Note? {
        val note: Note? = noteList.find { it!!.id == noteid }
        return note
    }


    override fun updateNote(note: Note?): Int {
        TODO("Not yet implemented")
    }
}
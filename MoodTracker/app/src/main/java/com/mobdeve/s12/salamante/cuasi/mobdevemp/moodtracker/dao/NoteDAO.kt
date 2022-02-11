package com.mobdeve.s12.salamante.cuasi.mobdevemp.moodtracker.dao

import com.mobdeve.s12.salamante.cuasi.mobdevemp.moodtracker.model.Note
import java.util.ArrayList

interface NoteDAO {
    fun addNote(note: Note?): Long
    fun getNote(noteid: Int): Note?
    fun getNotes(): ArrayList<Note?>?
    fun updateNote(note: Note?): Int
    // fun deleteNote(noteid: Int): Int
}
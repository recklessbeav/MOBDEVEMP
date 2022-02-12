package com.mobdeve.s12.salamante.cuasi.mobdevemp.moodtracker.dao

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.mobdeve.s12.salamante.cuasi.mobdevemp.moodtracker.model.Note
import java.util.ArrayList

class NoteDAODatabase : NoteDAO {
    private var notedatabase: NoteDatabase? = null
    private var database: SQLiteDatabase? = null

    constructor(context: Context?) {
        notedatabase = NoteDatabase(context)
    }

    override fun addNote(note: Note?): Long {
        val values = ContentValues()

        values.put(NoteDatabase.NOTESMOOD, note!!.mood)
        values.put(NoteDatabase.NOTESREASON, note!!.reason)
        values.put(NoteDatabase.NOTESLOCATION, note!!.location)
        values.put(NoteDatabase.NOTESDATE, note!!.date)
        database = notedatabase!!.writableDatabase

        val id: Long = database!!.insert(
            NoteDatabase.TABLENOTES,
            null,
            values
        )

        if (database != null) {
            notedatabase!!.close()
        }

        return id
    }

    override fun getNote(noteid: Int): Note? {
        TODO("Not yet implemented")
    }

    override fun getNotes(): ArrayList<Note?>? {
        val result = ArrayList<Note?>()
        val columns = arrayOf<String>(
            NoteDatabase.NOTESID,
            NoteDatabase.NOTESMOOD,
            NoteDatabase.NOTESREASON,
            NoteDatabase.NOTESLOCATION,
            NoteDatabase.NOTESDATE
        )

        database = notedatabase!!.readableDatabase

        val cursor = database!!.query(
            NoteDatabase.TABLENOTES,
            columns,
            null,
            null,
            null,
            null,
            null
        )

        cursor!!.moveToFirst()
        while (!cursor!!.isAfterLast) {
            val temp = Note()
            temp.id = cursor!!.getInt(0)
            temp.mood = cursor!!.getString(1)
            temp.reason = cursor!!.getString(2)
            temp.location = cursor!!.getString(3)
            temp.date = cursor!!.getString(4)
            result.add(temp)
            cursor!!.moveToNext()
        }

        if (cursor != null) {
            cursor.close()
        }

        if (database != null) {
            notedatabase!!.close()
        }

        return result
    }

    override fun updateNote(note: Note?): Int {
        TODO("Not yet implemented")
    }
}
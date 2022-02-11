package com.mobdeve.s12.salamante.cuasi.mobdevemp.moodtracker.dao

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class NoteDatabase (context: Context?) :
    SQLiteOpenHelper(context, DATABASENAME, null, DATABASEVERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_USER_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLENOTES)
        onCreate(db)
    }

    companion object {
        private const val DATABASENAME = "note.db"
        private const val DATABASEVERSION = 1

        //column names
        const val TABLENOTES = "users"
        const val NOTESID = "id"
        const val NOTESMOOD = "mood"
        const val NOTESREASON = "reason"
        const val NOTESLOCATION = "location"
        const val NOTESDATE = "date"
        private const val CREATE_USER_TABLE = ("create table " + TABLENOTES + " ( "
                + NOTESID + " integer primary key autoincrement, "
                + NOTESMOOD + " text, "
                + NOTESREASON + " text, "
                + NOTESLOCATION + " text, "
                + NOTESDATE + " text ); ")
        //INTEGER, REAL, TEXT, BLOB, NULL
    }
}
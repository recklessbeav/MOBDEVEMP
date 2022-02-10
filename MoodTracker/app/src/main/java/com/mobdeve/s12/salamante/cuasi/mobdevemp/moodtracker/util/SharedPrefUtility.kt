package com.mobdeve.s12.salamante.cuasi.mobdevemp.moodtracker.util

import android.content.Context
import android.content.SharedPreferences

class SharedPrefUtility {
    private var appPreferences: SharedPreferences? = null
    private val PREFS = "appPreferences"

    constructor(context: Context) {
        appPreferences = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
    }

    fun saveInt (key: String, value: Int) {
        val prefsEditor = appPreferences!!.edit()
        prefsEditor.putInt(key, value).commit()
    }

    fun getInt (key: String) : Int
            = appPreferences!!.getInt(key, 0)

    fun saveString (key: String?, value: String?) {
        val prefsEditor = appPreferences!!.edit()
        prefsEditor.putString(key, value).commit()
    }

    fun getString (key: String?): String?
            = appPreferences!!.getString(key, "Nothing Saved")

    fun removeString (key: String?){
        val prefsEditor = appPreferences!!.edit()
        prefsEditor.remove(key).commit()
    }

    fun removeAllPreferences(){
        appPreferences!!.edit().clear().commit()
    }
}
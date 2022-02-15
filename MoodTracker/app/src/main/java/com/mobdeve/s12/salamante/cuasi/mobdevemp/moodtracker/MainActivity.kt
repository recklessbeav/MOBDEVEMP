package com.mobdeve.s12.salamante.cuasi.mobdevemp.moodtracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mobdeve.s12.salamante.cuasi.mobdevemp.moodtracker.dao.NoteDAO
import com.mobdeve.s12.salamante.cuasi.mobdevemp.moodtracker.dao.NoteDAODatabase
import com.mobdeve.s12.salamante.cuasi.mobdevemp.moodtracker.databinding.ActivityMainBinding
import com.mobdeve.s12.salamante.cuasi.mobdevemp.moodtracker.model.Note
import com.mobdeve.s12.salamante.cuasi.mobdevemp.moodtracker.util.SharedPrefUtility
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPref: SharedPrefUtility
    lateinit var noteDAO: NoteDAO
    var noteList: ArrayList<Note?> = ArrayList<Note?>()
    var binding: ActivityMainBinding? = null

    val sdf = SimpleDateFormat("dd/M/yyyy")
    val sdfDatabase = SimpleDateFormat("MMM d yyyy")
    val currentDate = sdf.format(Date())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initPrefs()
        noteDAO = NoteDAODatabase(applicationContext)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        noteList = noteDAO.getNotes()!!
        val gotoLogActivity = Intent(applicationContext, LogActivity::class.java)

        binding!!.ivSad.setOnClickListener {
            sharedPref.saveString("mood", "Sad")
            gotoLogActivity.putExtra("mood", binding!!.tvFeelingSad.text.toString())
            startActivity(gotoLogActivity)
            finish()
        }

        binding!!.ivHappy.setOnClickListener {
            sharedPref.saveString("mood", "Happy")
            gotoLogActivity.putExtra("mood", binding!!.tvFeelingHappy.text.toString())
            startActivity(gotoLogActivity)
            finish()
        }

        binding!!.ivNeutral.setOnClickListener {
            sharedPref.saveString("mood", "Neutral")
            gotoLogActivity.putExtra("mood", binding!!.tvFeelingNeutral.text.toString())
            startActivity(gotoLogActivity)
            finish()
        }
    }

    fun initPrefs() {
        sharedPref = SharedPrefUtility(this)
    }

    override fun onResume() {
        super.onResume()

        sharedPref.saveString("app_loaded", currentDate.toString())

        // erase sharedprefs content when new day has elapsed
        var opened = sharedPref.getString("app_loaded")
        var closed = sharedPref.getString("app_closed")

        if (opened != closed && closed != "") {
            sharedPref.removeAllPreferences()
        }

        // check if user already added all needed datas
        for (note in noteList) {
            if (note!!.date.toString() == sdfDatabase.format(Date())) {
                val gotoWeekActivity = Intent(applicationContext, WeekActivity::class.java)
                startActivity(gotoWeekActivity)
                finish()
                break
            }
        }
    }

    override fun onPause() {
        super.onPause()
        sharedPref.saveString("app_closed", currentDate.toString())
    }
}
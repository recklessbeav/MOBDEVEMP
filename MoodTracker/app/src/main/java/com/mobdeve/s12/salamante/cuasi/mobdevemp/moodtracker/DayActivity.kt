package com.mobdeve.s12.salamante.cuasi.mobdevemp.moodtracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.mobdeve.s12.salamante.cuasi.mobdevemp.moodtracker.dao.NoteDAO
import com.mobdeve.s12.salamante.cuasi.mobdevemp.moodtracker.dao.NoteDAODatabase
import com.mobdeve.s12.salamante.cuasi.mobdevemp.moodtracker.databinding.ActivityDayBinding
import com.mobdeve.s12.salamante.cuasi.mobdevemp.moodtracker.model.Note
import com.mobdeve.s12.salamante.cuasi.mobdevemp.moodtracker.util.SharedPrefUtility
import java.text.SimpleDateFormat
import java.util.ArrayList

class DayActivity : AppCompatActivity() {

    private lateinit var sharedPref: SharedPrefUtility
    lateinit var noteDAO: NoteDAO
    var noteList: ArrayList<Note?> = ArrayList<Note?>()
    var binding: ActivityDayBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initPrefs()
        noteDAO = NoteDAODatabase(applicationContext)
        binding = ActivityDayBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        // get notes db
        noteList = noteDAO.getNotes()!!

        var date = intent.getStringExtra("date")
        var day = intent.getStringExtra("day")
        binding!!.tvDayDay.text = day

            for (note in noteList) {
            if (note!!.date.toString() == date) {
                setMood(note.mood.toString())
                binding!!.tvDayDate.text = note.date.toString()
                binding!!.tvDayContent.text = note.reason.toString()
                binding!!.tvDayLocation.text = note.location.toString()
                break
            }
        }
    }

    fun setMood(mood: String) {
        if (mood == "Sad") {
            binding!!.ivDay.setImageResource(R.drawable.sad_face)
            binding!!.tvDayDate.setTextColor(getResources().getColor(R.color.sad));
            binding!!.tvDayDay.setTextColor(getResources().getColor(R.color.sad));
            binding!!.tvDayLocation.setTextColor(getResources().getColor(R.color.sad));
        } else if (mood == "Neutral") {
            binding!!.ivDay.setImageResource(R.drawable.neutral_face)
            binding!!.tvDayDate.setTextColor(getResources().getColor(R.color.neutral));
            binding!!.tvDayDay.setTextColor(getResources().getColor(R.color.neutral));
            binding!!.tvDayLocation.setTextColor(getResources().getColor(R.color.neutral));
        } else {
            binding!!.ivDay.setImageResource(R.drawable.happy_face)
            binding!!.tvDayDate.setTextColor(getResources().getColor(R.color.happy));
            binding!!.tvDayDay.setTextColor(getResources().getColor(R.color.happy));
            binding!!.tvDayLocation.setTextColor(getResources().getColor(R.color.happy));
        }
    }

    fun initPrefs() {
        sharedPref = SharedPrefUtility(this)
    }
}
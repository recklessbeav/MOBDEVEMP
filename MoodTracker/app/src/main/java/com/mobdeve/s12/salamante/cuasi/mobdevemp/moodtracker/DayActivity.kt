package com.mobdeve.s12.salamante.cuasi.mobdevemp.moodtracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mobdeve.s12.salamante.cuasi.mobdevemp.moodtracker.databinding.ActivityDayBinding
import com.mobdeve.s12.salamante.cuasi.mobdevemp.moodtracker.util.SharedPrefUtility
import java.text.SimpleDateFormat

class DayActivity : AppCompatActivity() {

    private lateinit var sharedPref: SharedPrefUtility
    var binding: ActivityDayBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initPrefs()
        binding = ActivityDayBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        var mood = sharedPref.getString("mood")
        var date = intent.getStringExtra("date")

        if (mood == "Sad") {
            binding!!.ivDay.setImageResource(R.drawable.sad_face)
        } else if (mood == "Neutral") {
            binding!!.ivDay.setImageResource(R.drawable.neutral_face)
        } else {
            binding!!.ivDay.setImageResource(R.drawable.happy_face)
        }

        binding!!.tvDayDate.text = date
        binding!!.tvDayContent.text = sharedPref.getString("reason")
    }

    fun initPrefs() {
        sharedPref = SharedPrefUtility(this)
    }
}
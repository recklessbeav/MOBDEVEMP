package com.mobdeve.s12.salamante.cuasi.mobdevemp.moodtracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mobdeve.s12.salamante.cuasi.mobdevemp.moodtracker.databinding.ActivityLogBinding
import com.mobdeve.s12.salamante.cuasi.mobdevemp.moodtracker.databinding.ActivityWeekBinding

class WeekActivity : AppCompatActivity() {

    var binding: ActivityWeekBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeekBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        binding!!.btnMonday.setOnClickListener {
            val gotoDayActivity = Intent(applicationContext, DayActivity::class.java)
            startActivity(gotoDayActivity)
            //finish()
        }
    }
}
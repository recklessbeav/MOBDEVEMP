package com.mobdeve.s12.salamante.cuasi.mobdevemp.moodtracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mobdeve.s12.salamante.cuasi.mobdevemp.moodtracker.databinding.ActivityLogBinding

class LogActivity : AppCompatActivity() {

    var binding: ActivityLogBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        var mood = intent.getStringExtra("mood")

        binding!!.etLog.hint = "Why do you feel " + mood!!.lowercase() + "?"

        binding!!.btnReasonProceed.setOnClickListener {
            val gotoWeekActivity = Intent(applicationContext, WeekActivity::class.java)
            gotoWeekActivity.putExtra("mood", mood)
            startActivity(gotoWeekActivity)
            finish()
        }

        binding!!.btnMap.setOnClickListener {
            val gotoMapsActivity = Intent(applicationContext, MapsActivity::class.java)
            startActivity(gotoMapsActivity)
        }
    }
}
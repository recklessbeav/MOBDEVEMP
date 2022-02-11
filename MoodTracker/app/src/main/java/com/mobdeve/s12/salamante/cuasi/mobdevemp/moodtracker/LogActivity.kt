package com.mobdeve.s12.salamante.cuasi.mobdevemp.moodtracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.mobdeve.s12.salamante.cuasi.mobdevemp.moodtracker.databinding.ActivityLogBinding
import com.mobdeve.s12.salamante.cuasi.mobdevemp.moodtracker.util.SharedPrefUtility

class LogActivity : AppCompatActivity() {

    private lateinit var sharedPref: SharedPrefUtility
    var binding: ActivityLogBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initPrefs()
        binding = ActivityLogBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        var mood = intent.getStringExtra("mood")

        binding!!.etLog.hint = "Why do you feel " + mood!!.lowercase() + "?"

        binding!!.btnReasonProceed.setOnClickListener {
            if (binding!!.etLog.text.toString() == "") {
                Toast.makeText(applicationContext, "Add a reason!", Toast.LENGTH_LONG).show()
            } else {
                sharedPref.saveString("reason", binding!!.etLog.text.toString())
                val gotoWeekActivity = Intent(applicationContext, WeekActivity::class.java)
                gotoWeekActivity.putExtra("mood", mood)
                startActivity(gotoWeekActivity)
                finish()
            }
        }

        binding!!.btnMap.setOnClickListener {
            val gotoMapsActivity = Intent(applicationContext, MapsActivity::class.java)
            startActivity(gotoMapsActivity)
        }
    }

    fun initPrefs() {
        sharedPref = SharedPrefUtility(this)
    }
}
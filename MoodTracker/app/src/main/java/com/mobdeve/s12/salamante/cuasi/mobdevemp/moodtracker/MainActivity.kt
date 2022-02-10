package com.mobdeve.s12.salamante.cuasi.mobdevemp.moodtracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mobdeve.s12.salamante.cuasi.mobdevemp.moodtracker.databinding.ActivityMainBinding
import com.mobdeve.s12.salamante.cuasi.mobdevemp.moodtracker.util.SharedPrefUtility
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPref: SharedPrefUtility
    var binding: ActivityMainBinding? = null

    val sdf = SimpleDateFormat("dd/M/yyyy")
    val currentDate = sdf.format(Date())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initPrefs()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        sharedPref.saveString("date", currentDate.toString())
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

        binding!!.btnLoggedAlready.setOnClickListener {
            val gotoWeekActivity = Intent(applicationContext, WeekActivity::class.java)
            startActivity(gotoWeekActivity)
            finish()
        }
    }

    fun initPrefs() {
        sharedPref = SharedPrefUtility(this)
    }

    override fun onResume() {
        super.onResume()
        sharedPref.saveString("app_loaded", currentDate.toString())
    }

    override fun onPause() {
        super.onPause()
        sharedPref.saveString("app_closed", currentDate.toString())
    }
}
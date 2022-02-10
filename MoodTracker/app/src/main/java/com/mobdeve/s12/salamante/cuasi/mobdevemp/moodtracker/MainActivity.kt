package com.mobdeve.s12.salamante.cuasi.mobdevemp.moodtracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mobdeve.s12.salamante.cuasi.mobdevemp.moodtracker.databinding.ActivityMainBinding
import com.mobdeve.s12.salamante.cuasi.mobdevemp.moodtracker.util.SharedPrefUtility

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPref: SharedPrefUtility
    var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // initPrefs()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        val gotoLogActivity = Intent(applicationContext, LogActivity::class.java)

        binding!!.ivSad.setOnClickListener {
            gotoLogActivity.putExtra("mood", binding!!.tvFeelingSad.text.toString())
            startActivity(gotoLogActivity)
            finish()
        }

        binding!!.ivHappy.setOnClickListener {
            gotoLogActivity.putExtra("mood", binding!!.tvFeelingHappy.text.toString())
            startActivity(gotoLogActivity)
            finish()
        }

        binding!!.ivNeutral.setOnClickListener {
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

    override fun onResume() {
        super.onResume()
        // sharedPref.saveInt("counterOne", counterOne)
    }

    override fun onPause() {
        super.onPause()
        // sharedPref.saveInt("shutdown", 1)
    }

    fun initPrefs() {
        sharedPref = SharedPrefUtility(this)
    }
}
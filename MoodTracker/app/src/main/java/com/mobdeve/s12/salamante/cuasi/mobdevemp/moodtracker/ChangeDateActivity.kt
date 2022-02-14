package com.mobdeve.s12.salamante.cuasi.mobdevemp.moodtracker

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import com.mobdeve.s12.salamante.cuasi.mobdevemp.moodtracker.databinding.ActivityChangeDateBinding
import com.mobdeve.s12.salamante.cuasi.mobdevemp.moodtracker.util.SharedPrefUtility


class ChangeDateActivity : AppCompatActivity() {
    var binding: ActivityChangeDateBinding? = null
    private lateinit var sharedPref: SharedPrefUtility

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initPrefs()
        binding = ActivityChangeDateBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        binding!!.dpDate.spinnersShown = true

        binding!!.btnDone.setOnClickListener {
            sharedPref.saveInt("change_date", 1)
            val day: Int = binding!!.dpDate.dayOfMonth
            val month: Int = binding!!.dpDate.month
            val year: Int = binding!!.dpDate.year
            val calendar: Calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Manila"))
            calendar.set(year, month, day)
//            Toast.makeText(applicationContext,calendar.toString(),Toast.LENGTH_SHORT).show()

            val gotoWeekActivity = Intent(applicationContext, WeekActivity::class.java)
            gotoWeekActivity.putExtra("change_date", calendar)
            startActivity(gotoWeekActivity)
            finish()
        }
    }

    fun initPrefs() {
        sharedPref = SharedPrefUtility(this)
    }
}
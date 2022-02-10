package com.mobdeve.s12.salamante.cuasi.mobdevemp.moodtracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.mobdeve.s12.salamante.cuasi.mobdevemp.moodtracker.databinding.ActivityWeekBinding
import com.mobdeve.s12.salamante.cuasi.mobdevemp.moodtracker.util.SharedPrefUtility
import java.text.SimpleDateFormat
import java.util.*

class WeekActivity : AppCompatActivity() {

    private lateinit var sharedPref: SharedPrefUtility
    var binding: ActivityWeekBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initPrefs()
        binding = ActivityWeekBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        // Toast.makeText(applicationContext, sharedPref.getString("reason"), Toast.LENGTH_SHORT).show()

        var mood = intent.getStringExtra("mood")

        val sdf = SimpleDateFormat("MMM d yyyy")
        val sdfButton = SimpleDateFormat("MMMM d")
        val weekNumber = Calendar.getInstance(TimeZone.getTimeZone("UTC")).get(Calendar.WEEK_OF_YEAR)

        val cal = Calendar.getInstance()
        cal[Calendar.WEEK_OF_YEAR] = weekNumber
        cal[Calendar.DAY_OF_WEEK] = Calendar.MONDAY

        val calWeekEnd = Calendar.getInstance()
        calWeekEnd[Calendar.WEEK_OF_YEAR] = weekNumber
        calWeekEnd[Calendar.DAY_OF_WEEK] = Calendar.MONDAY
        calWeekEnd.add(Calendar.DATE, 6)

        binding!!.tvDate.text = "(" + sdf.format(cal.time).toString() + " — " + sdf.format(calWeekEnd.time).toString() + ")"

        binding!!.btnMonday.text = "Monday (" + sdfButton.format(cal.time).toString() + ")"
        // need to fix; use mood color stored in databse
        binding!!.btnMonday.setBackgroundColor(moodCheck(mood))
        binding!!.btnMonday.setOnClickListener {
            val gotoDayActivity = Intent(applicationContext, DayActivity::class.java)

            cal[Calendar.WEEK_OF_YEAR] = weekNumber
            cal[Calendar.DAY_OF_WEEK] = Calendar.MONDAY

            gotoDayActivity.putExtra("date", sdf.format(cal.time).toString())
            startActivity(gotoDayActivity)
        }

        cal.add(Calendar.DATE, 1)
        binding!!.btnTuesday.text = "Tuesday (" + sdfButton.format(cal.time).toString() + ")"
        // need to fix; use mood color stored in databse
        binding!!.btnTuesday.setBackgroundColor(moodCheck(mood))
        binding!!.btnTuesday.setOnClickListener {
            val gotoDayActivity = Intent(applicationContext, DayActivity::class.java)

            cal[Calendar.WEEK_OF_YEAR] = weekNumber
            cal[Calendar.DAY_OF_WEEK] = Calendar.MONDAY
            cal.add(Calendar.DATE, 1)

            gotoDayActivity.putExtra("date", sdf.format(cal.time).toString())
            startActivity(gotoDayActivity)
        }

        cal.add(Calendar.DATE, 1)
        binding!!.btnWednesday.text = "Wednesday (" + sdfButton.format(cal.time).toString() + ")"
        // need to fix; use mood color stored in databse
        binding!!.btnWednesday.setBackgroundColor(moodCheck(mood))
        binding!!.btnWednesday.setOnClickListener {
            val gotoDayActivity = Intent(applicationContext, DayActivity::class.java)

            cal[Calendar.WEEK_OF_YEAR] = weekNumber
            cal[Calendar.DAY_OF_WEEK] = Calendar.MONDAY
            cal.add(Calendar.DATE, 2)

            gotoDayActivity.putExtra("date", sdf.format(cal.time).toString())
            startActivity(gotoDayActivity)
        }

        cal.add(Calendar.DATE, 1)
        binding!!.btnThursday.text = "Thursday (" + sdfButton.format(cal.time).toString() + ")"
        // need to fix; use mood color stored in databse
        binding!!.btnThursday.setBackgroundColor(moodCheck(mood))
        binding!!.btnThursday.setOnClickListener {
            val gotoDayActivity = Intent(applicationContext, DayActivity::class.java)

            cal[Calendar.WEEK_OF_YEAR] = weekNumber
            cal[Calendar.DAY_OF_WEEK] = Calendar.MONDAY
            cal.add(Calendar.DATE, 3)

            gotoDayActivity.putExtra("date", sdf.format(cal.time).toString())
            startActivity(gotoDayActivity)
        }

        cal.add(Calendar.DATE, 1)
        binding!!.btnFriday.text = "Friday (" + sdfButton.format(cal.time).toString() + ")"
        // need to fix; use mood color stored in databse
        binding!!.btnFriday.setBackgroundColor(moodCheck(mood))
        binding!!.btnFriday.setOnClickListener {
            val gotoDayActivity = Intent(applicationContext, DayActivity::class.java)

            cal[Calendar.WEEK_OF_YEAR] = weekNumber
            cal[Calendar.DAY_OF_WEEK] = Calendar.MONDAY
            cal.add(Calendar.DATE, 4)

            gotoDayActivity.putExtra("date", sdf.format(cal.time).toString())
            startActivity(gotoDayActivity)
        }

        cal.add(Calendar.DATE, 1)
        binding!!.btnSaturday.text = "Saturday (" + sdfButton.format(cal.time).toString() + ")"
        // need to fix; use mood color stored in databse
        binding!!.btnSaturday.setBackgroundColor(moodCheck(mood))
        binding!!.btnSaturday.setOnClickListener {
            val gotoDayActivity = Intent(applicationContext, DayActivity::class.java)

            cal[Calendar.WEEK_OF_YEAR] = weekNumber
            cal[Calendar.DAY_OF_WEEK] = Calendar.MONDAY
            cal.add(Calendar.DATE, 5)

            gotoDayActivity.putExtra("date", sdf.format(cal.time).toString())
            startActivity(gotoDayActivity)
        }

        cal.add(Calendar.DATE, 1)
        binding!!.btnSunday.text = "Sunday (" + sdfButton.format(cal.time).toString() + ")"
        // need to fix; use mood color stored in databse
        binding!!.btnSunday.setBackgroundColor(moodCheck(mood))
        binding!!.btnSunday.setOnClickListener {
            val gotoDayActivity = Intent(applicationContext, DayActivity::class.java)

            cal[Calendar.WEEK_OF_YEAR] = weekNumber
            cal[Calendar.DAY_OF_WEEK] = Calendar.MONDAY
            cal.add(Calendar.DATE, 6)

            gotoDayActivity.putExtra("date", sdf.format(cal.time).toString())
            startActivity(gotoDayActivity)
        }
    }

    fun initPrefs() {
        sharedPref = SharedPrefUtility(this)
    }

    private fun moodCheck (mood : String?): Int {
        // Sad          #CD8383
        // Neutral      #CDC683
        // Happy        #AFCD83

        if (mood == "Sad") {
            return getResources().getColor(R.color.sad)
        } else if (mood == "Neutral") {
            return getResources().getColor(R.color.neutral)
        } else if (mood == "Happy") {
            return getResources().getColor(R.color.sad)
        } else {
            return getResources().getColor(R.color.extra)
        }
//    Neutral, Happy
    }
}


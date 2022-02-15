package com.mobdeve.s12.salamante.cuasi.mobdevemp.moodtracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.mobdeve.s12.salamante.cuasi.mobdevemp.moodtracker.dao.NoteDAO
import com.mobdeve.s12.salamante.cuasi.mobdevemp.moodtracker.dao.NoteDAODatabase
import com.mobdeve.s12.salamante.cuasi.mobdevemp.moodtracker.databinding.ActivityWeekBinding
import com.mobdeve.s12.salamante.cuasi.mobdevemp.moodtracker.model.Note
import com.mobdeve.s12.salamante.cuasi.mobdevemp.moodtracker.util.SharedPrefUtility
import java.text.SimpleDateFormat
import java.util.*

class WeekActivity : AppCompatActivity() {

    private lateinit var sharedPref: SharedPrefUtility
    lateinit var noteDAO: NoteDAO
    var noteList: ArrayList<Note?> = ArrayList<Note?>()
    var binding: ActivityWeekBinding? = null

    val sdf = SimpleDateFormat("MMM d yyyy")
    val sdfButton = SimpleDateFormat("MMMM d")
    val sdfDay = SimpleDateFormat("EEEE")
    var weekNumber : Int = 0
    var cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Manila"))
    var calWeekEnd = Calendar.getInstance(TimeZone.getTimeZone("Asia/Manila"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initPrefs()
        noteDAO = NoteDAODatabase(applicationContext)
        binding = ActivityWeekBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        // flag that all needed input are gathered
        sharedPref.saveInt("saved_data", 1)

        // get notes db
        noteList = noteDAO.getNotes()!!

        var mood = ""

        if (sharedPref.getInt("change_date") == 1) {
            sharedPref.saveInt("change_date", 0)
            var changedCal = intent.getSerializableExtra("change_date") as Calendar

            weekNumber = changedCal[Calendar.WEEK_OF_YEAR]

            cal[Calendar.WEEK_OF_YEAR] = weekNumber
            cal[Calendar.DAY_OF_WEEK] = Calendar.MONDAY

            calWeekEnd[Calendar.WEEK_OF_YEAR] = weekNumber
            calWeekEnd[Calendar.DAY_OF_WEEK] = Calendar.MONDAY
            calWeekEnd.add(Calendar.DATE, 6)

            cal.add(Calendar.DATE, -1)
            calWeekEnd.add(Calendar.DATE, -1)
        } else {
            weekNumber = Calendar.getInstance(TimeZone.getTimeZone("Asia/Manila")).get(Calendar.WEEK_OF_YEAR)

            cal[Calendar.WEEK_OF_YEAR] = weekNumber
            cal[Calendar.DAY_OF_WEEK] = Calendar.MONDAY

            calWeekEnd[Calendar.WEEK_OF_YEAR] = weekNumber
            calWeekEnd[Calendar.DAY_OF_WEEK] = Calendar.MONDAY
            calWeekEnd.add(Calendar.DATE, 6)

            cal.add(Calendar.DATE, -1)
            calWeekEnd.add(Calendar.DATE, -1)
        }

        binding!!.tvDate.text =
            "(" + sdf.format(cal.time).toString() + " — " + sdf.format(calWeekEnd.time)
                .toString() + ")"

        binding!!.btnSunday.text = "Sunday (" + sdfButton.format(cal.time).toString() + ")"
        mood = getMoodString(noteList, cal)
        binding!!.btnSunday.setBackgroundColor(moodCheck(mood))
        binding!!.btnSunday.setOnClickListener {
            cal[Calendar.WEEK_OF_YEAR] = weekNumber
            cal[Calendar.DAY_OF_WEEK] = Calendar.MONDAY
            cal.add(Calendar.DATE, -1)

            if (noteValidator(sdf.format(cal.time).toString())) {
                val gotoDayActivity = Intent(applicationContext, DayActivity::class.java)
                gotoDayActivity.putExtra("date", sdf.format(cal.time).toString())
                gotoDayActivity.putExtra("day", sdfDay.format(cal.time).toString())
                startActivity(gotoDayActivity)
            } else {
                Toast.makeText(
                    applicationContext,
                    "No log for this day has been found.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        cal.add(Calendar.DATE, 1)
        binding!!.btnMonday.text = "Monday (" + sdfButton.format(cal.time).toString() + ")"
        mood = getMoodString(noteList, cal)
        binding!!.btnMonday.setBackgroundColor(moodCheck(mood))
        binding!!.btnMonday.setOnClickListener {
            cal[Calendar.WEEK_OF_YEAR] = weekNumber
            cal[Calendar.DAY_OF_WEEK] = Calendar.MONDAY

            if (noteValidator(sdf.format(cal.time).toString())) {
                val gotoDayActivity = Intent(applicationContext, DayActivity::class.java)
                gotoDayActivity.putExtra("date", sdf.format(cal.time).toString())
                gotoDayActivity.putExtra("day", sdfDay.format(cal.time).toString())
                startActivity(gotoDayActivity)
            } else {
                Toast.makeText(
                    applicationContext,
                    "No log for this day has been found.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        cal.add(Calendar.DATE, 1)
        binding!!.btnTuesday.text = "Tuesday (" + sdfButton.format(cal.time).toString() + ")"
        mood = getMoodString(noteList, cal)
        binding!!.btnTuesday.setBackgroundColor(moodCheck(mood))
        binding!!.btnTuesday.setOnClickListener {
            cal[Calendar.WEEK_OF_YEAR] = weekNumber
            cal[Calendar.DAY_OF_WEEK] = Calendar.MONDAY
            cal.add(Calendar.DATE, 1)

            if (noteValidator(sdf.format(cal.time).toString())) {
                val gotoDayActivity = Intent(applicationContext, DayActivity::class.java)
                gotoDayActivity.putExtra("date", sdf.format(cal.time).toString())
                gotoDayActivity.putExtra("day", sdfDay.format(cal.time).toString())
                startActivity(gotoDayActivity)
            } else {
                Toast.makeText(
                    applicationContext,
                    "No log for this day has been found.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        cal.add(Calendar.DATE, 1)
        binding!!.btnWednesday.text = "Wednesday (" + sdfButton.format(cal.time).toString() + ")"
        mood = getMoodString(noteList, cal)
        binding!!.btnWednesday.setBackgroundColor(moodCheck(mood))
        binding!!.btnWednesday.setOnClickListener {
            cal[Calendar.WEEK_OF_YEAR] = weekNumber
            cal[Calendar.DAY_OF_WEEK] = Calendar.MONDAY
            cal.add(Calendar.DATE, 2)

            if (noteValidator(sdf.format(cal.time).toString())) {
                val gotoDayActivity = Intent(applicationContext, DayActivity::class.java)
                gotoDayActivity.putExtra("date", sdf.format(cal.time).toString())
                gotoDayActivity.putExtra("day", sdfDay.format(cal.time).toString())
                startActivity(gotoDayActivity)
            } else {
                Toast.makeText(
                    applicationContext,
                    "No log for this day has been found.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        cal.add(Calendar.DATE, 1)
        binding!!.btnThursday.text = "Thursday (" + sdfButton.format(cal.time).toString() + ")"
        mood = getMoodString(noteList, cal)
        binding!!.btnThursday.setBackgroundColor(moodCheck(mood))
        binding!!.btnThursday.setOnClickListener {
            cal[Calendar.WEEK_OF_YEAR] = weekNumber
            cal[Calendar.DAY_OF_WEEK] = Calendar.MONDAY
            cal.add(Calendar.DATE, 3)

            if (noteValidator(sdf.format(cal.time).toString())) {
                val gotoDayActivity = Intent(applicationContext, DayActivity::class.java)
                gotoDayActivity.putExtra("date", sdf.format(cal.time).toString())
                gotoDayActivity.putExtra("day", sdfDay.format(cal.time).toString())
                startActivity(gotoDayActivity)
            } else {
                Toast.makeText(
                    applicationContext,
                    "No log for this day has been found.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        cal.add(Calendar.DATE, 1)
        binding!!.btnFriday.text = "Friday (" + sdfButton.format(cal.time).toString() + ")"
        mood = getMoodString(noteList, cal)
        binding!!.btnFriday.setBackgroundColor(moodCheck(mood))
        binding!!.btnFriday.setOnClickListener {
            cal[Calendar.WEEK_OF_YEAR] = weekNumber
            cal[Calendar.DAY_OF_WEEK] = Calendar.MONDAY
            cal.add(Calendar.DATE, 4)

            if (noteValidator(sdf.format(cal.time).toString())) {
                val gotoDayActivity = Intent(applicationContext, DayActivity::class.java)
                gotoDayActivity.putExtra("date", sdf.format(cal.time).toString())
                gotoDayActivity.putExtra("day", sdfDay.format(cal.time).toString())
                startActivity(gotoDayActivity)
            } else {
                Toast.makeText(
                    applicationContext,
                    "No log for this day has been found.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        cal.add(Calendar.DATE, 1)
        binding!!.btnSaturday.text = "Saturday (" + sdfButton.format(cal.time).toString() + ")"
        mood = getMoodString(noteList, cal)
        binding!!.btnSaturday.setBackgroundColor(moodCheck(mood))
        binding!!.btnSaturday.setOnClickListener {
            cal[Calendar.WEEK_OF_YEAR] = weekNumber
            cal[Calendar.DAY_OF_WEEK] = Calendar.MONDAY
            cal.add(Calendar.DATE, 5)

            if (noteValidator(sdf.format(cal.time).toString())) {
                val gotoDayActivity = Intent(applicationContext, DayActivity::class.java)
                gotoDayActivity.putExtra("date", sdf.format(cal.time).toString())
                gotoDayActivity.putExtra("day", sdfDay.format(cal.time).toString())
                startActivity(gotoDayActivity)
            } else {
                Toast.makeText(
                    applicationContext,
                    "No log for this day has been found.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        binding!!.btnDate.setOnClickListener {
            val gotoChangeDateActivity = Intent(applicationContext, ChangeDateActivity::class.java)
            gotoChangeDateActivity.putExtra("date", sdf.format(cal.time).toString())
            gotoChangeDateActivity.putExtra("day", sdfDay.format(cal.time).toString())
            startActivity(gotoChangeDateActivity)
            finish()
        }
    }

    fun initPrefs() {
        sharedPref = SharedPrefUtility(this)
    }

    fun noteValidator(currentDate: String): Boolean {
        for (note in noteList) {
            if (note!!.date.toString() == currentDate) {
                return true
            }
        }
        return false
    }

    fun getMoodString(noteList: ArrayList<Note?>, cal: Calendar): String {
        lateinit var mood: String
        for (note in noteList) {
            if (note!!.date.toString() == sdf.format(cal.time).toString()) {
                mood = note.mood.toString()
                break
            } else {
                mood = "extra"
            }
        }
        return mood
    }

    private fun moodCheck(mood: String?): Int {
        // Sad          #CD8383
        // Neutral      #CDC683
        // Happy        #AFCD83
        if (mood == "Sad") {
            return getResources().getColor(R.color.sad)
        } else if (mood == "Neutral") {
            return getResources().getColor(R.color.neutral)
        } else if (mood == "Happy") {
            return getResources().getColor(R.color.happy)
        } else {
            return getResources().getColor(R.color.teal_700)
        }
    }
}


package com.mobdeve.s12.salamante.cuasi.mobdevemp.moodtracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mobdeve.s12.salamante.cuasi.mobdevemp.moodtracker.databinding.ActivityDayBinding

class DayActivity : AppCompatActivity() {

    var binding: ActivityDayBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDayBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
    }
}
package com.mobdeve.s12.cuasi.beaverly.mobdevemp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mobdeve.s12.cuasi.beaverly.mobdevemp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)


        binding!!.ivSad.setOnClickListener {
            val gotoLogActivity = Intent(applicationContext, LogActivity::class.java)
            startActivity(gotoLogActivity)
            finish()
        }

    }
}
package com.mobdeve.s12.salamante.cuasi.mobdevemp.moodtracker

import android.Manifest
import android.R
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.core.app.ActivityCompat
import com.mobdeve.s12.salamante.cuasi.mobdevemp.moodtracker.databinding.ActivityLogBinding
import com.mobdeve.s12.salamante.cuasi.mobdevemp.moodtracker.util.SharedPrefUtility

class LogActivity : AppCompatActivity(), LocationListener{

    protected var locationManager: LocationManager? = null
    protected var locationListener: LocationListener? = null
    protected var context: Context? = null


    var provider: String? = ""
    var lat: String? = ""
    protected var latitude: String? = ""
    protected var longitude: String? = ""
    protected var gps_enabled: Boolean? = true
    protected var network_enabled: Boolean? = true


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

        locationManager = getSystemService(LOCATION_SERVICE) as LocationManager
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION )
            == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            locationManager!!.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, this)
        } else{
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)
                || ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_COARSE_LOCATION)){
                Toast.makeText(applicationContext, "Wrong Password",
                    Toast.LENGTH_SHORT).show()
            }else{
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION), 100)
            }
        }
//        locationManager!!.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, this)



    }

    fun initPrefs() {
        sharedPref = SharedPrefUtility(this)
    }

    override fun onLocationChanged(location: Location) {
        binding!!.tvLoc.text = "Latitude:" + location.latitude + " \n Longitude:" + location.longitude
    }

    override fun onProviderDisabled(provider: String) {
        Log.d("Latitude", "disable")
    }

    override fun onProviderEnabled(provider: String) {
        Log.d("Latitude", "enable")
    }

    override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {
        Log.d("Latitude", "status")
    }

}
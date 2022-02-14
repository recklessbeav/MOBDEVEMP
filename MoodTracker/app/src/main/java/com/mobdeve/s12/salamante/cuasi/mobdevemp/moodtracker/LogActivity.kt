package com.mobdeve.s12.salamante.cuasi.mobdevemp.moodtracker

import android.Manifest
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.util.Log
import androidx.core.app.ActivityCompat
import com.mobdeve.s12.salamante.cuasi.mobdevemp.moodtracker.dao.NoteDAO
import com.mobdeve.s12.salamante.cuasi.mobdevemp.moodtracker.dao.NoteDAODatabase
import com.mobdeve.s12.salamante.cuasi.mobdevemp.moodtracker.databinding.ActivityLogBinding
import com.mobdeve.s12.salamante.cuasi.mobdevemp.moodtracker.model.Note
import com.mobdeve.s12.salamante.cuasi.mobdevemp.moodtracker.util.SharedPrefUtility
import java.text.SimpleDateFormat
import java.util.*
import android.location.Address

import java.util.Locale

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

    private lateinit var geocoder: Geocoder
    private lateinit var sharedPref: SharedPrefUtility
    var binding: ActivityLogBinding? = null
    lateinit var noteDAO: NoteDAO

    val sdf = SimpleDateFormat("MMM d yyyy")
    val currentDate = sdf.format(Date())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initPrefs()
        noteDAO = NoteDAODatabase(applicationContext)
        binding = ActivityLogBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        var mood = intent.getStringExtra("mood")

        val sdf_id = SimpleDateFormat("ddMyyyy")
        val id_date = sdf_id.format(Date()).toInt()

        binding!!.etLog.hint = "Why do you feel " + mood!!.lowercase() + "?"

        binding!!.btnReasonProceed.setOnClickListener {
            if (binding!!.etLog.text.toString() == "") {
                Toast.makeText(applicationContext, "Add a reason!", Toast.LENGTH_LONG).show()
            } else {
                sharedPref.saveString("reason", binding!!.etLog.text.toString())
                sharedPref.saveString("location", binding!!.tvLoc.text.toString())
                sharedPref.saveString("date", currentDate.toString())

                // add db code here
                var note = Note(sharedPref!!.getString("mood"),
                    sharedPref!!.getString("reason"),
                    sharedPref!!.getString("location"),
                    sharedPref!!.getString("date"),
                    id_date)

                noteDAO.addNote(note)

                val gotoWeekActivity = Intent(applicationContext, WeekActivity::class.java)
//                gotoWeekActivity.putExtra("mood", mood)
                startActivity(gotoWeekActivity)
                finish()
            }
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
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)
                || ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_COARSE_LOCATION)){
                Toast.makeText(applicationContext, "Wrong Password",
                    Toast.LENGTH_SHORT).show()
            } else{
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION), 100)
            }
        }
    }

    fun initPrefs() {
        sharedPref = SharedPrefUtility(this)
        geocoder = Geocoder(this)
    }

    override fun onLocationChanged(location: Location) {
       var addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1)
        binding!!.tvLoc.setText(addresses[0].getAddressLine(0))
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
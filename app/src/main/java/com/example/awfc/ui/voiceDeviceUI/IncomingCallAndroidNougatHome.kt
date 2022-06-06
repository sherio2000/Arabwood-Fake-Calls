package com.example.awfc.ui.voiceDeviceUI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Chronometer
import com.example.awfc.R

class IncomingCallAndroidNougatHome : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_incoming_call_android_nougat_home)

        val chronometer = findViewById<Chronometer>(R.id.chronometerAndroidNougat)
        chronometer.start()
    }
}
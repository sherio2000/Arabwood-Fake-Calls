package com.example.awfc.ui.voiceDeviceUI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Chronometer
import android.widget.ImageView
import com.example.awfc.R

class IncomingVoiceCallSamsungS7Home : AppCompatActivity() {

    private lateinit var chronometer: Chronometer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_incoming_voice_call_samsung_s7_home)

        chronometer = findViewById(R.id.chronometerSamsungS7)
        chronometer.start()

        val endCallBtn = findViewById<ImageView>(R.id.endCallBtnIV)
        endCallBtn.setOnClickListener {
            chronometer.stop()
            this.finish()
        }
    }

    override fun onDestroy() {
        chronometer.stop()
        this.finish()
        super.onDestroy()
    }

    override fun onBackPressed() {
        chronometer.stop()
        this.finish()
        super.onBackPressed()
    }
}
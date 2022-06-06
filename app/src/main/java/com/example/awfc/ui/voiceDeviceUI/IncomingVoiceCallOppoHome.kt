package com.example.awfc.ui.voiceDeviceUI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Chronometer
import android.widget.ImageView
import com.example.awfc.R

class IncomingVoiceCallOppoHome : AppCompatActivity() {

    private lateinit var chronometer: Chronometer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_incoming_voice_call_oppo_home)


        chronometer = findViewById(R.id.chronometerOppo)
        chronometer.start()

        val endCallBtn = findViewById<ImageView>(R.id.endCallBtnIV)
        endCallBtn.setOnClickListener {
            chronometer.stop()
            this.finish()
        }
    }
}
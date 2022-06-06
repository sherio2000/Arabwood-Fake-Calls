package com.example.awfc.ui.voiceDeviceUI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Chronometer
import android.widget.ImageView
import com.example.awfc.R

class IncomingVoiceCall_Ios12_2_home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_incoming_voice_call_ios122_home)

        val chronometer = findViewById<Chronometer>(R.id.chronometerIphone12_1)
        chronometer.start()

        val endCallBtn = findViewById<ImageView>(R.id.endCallBtnIV)
        endCallBtn.setOnClickListener {
            chronometer.stop()
            this.finish()
        }
    }
}
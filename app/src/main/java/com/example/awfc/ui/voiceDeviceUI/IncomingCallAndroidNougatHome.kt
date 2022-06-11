package com.example.awfc.ui.voiceDeviceUI

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Chronometer
import android.widget.ImageView
import android.widget.TextView
import com.example.awfc.R
import com.example.awfc.data.Artist
import com.example.awfc.data.VoiceCaller
import com.example.awfc.utils.ScheduleVoiceCall

class IncomingCallAndroidNougatHome : AppCompatActivity() {
    private var caller: VoiceCaller? = null
    private var mp: MediaPlayer? = MediaPlayer()
    override fun onCreate(savedInstanceState: Bundle?) {
        ScheduleVoiceCall().showWhenLockedAndTurnScreenOn(this)
        ScheduleVoiceCall().hideSystemBars(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_incoming_call_android_nougat_home)
        val callerNameTv = findViewById<TextView>(R.id.callerIdTv)
        val callerMobileTv = findViewById<TextView>(R.id.callerNumTv)

        val endCallIV = findViewById<ImageView>(R.id.endCallBtnIV)



        caller = intent.getParcelableExtra("caller")
        caller?.let { ScheduleVoiceCall().placeCallerInfo(callerNameTv, callerMobileTv, it) }

        val chronometer = findViewById<Chronometer>(R.id.chronometerAndroidNougat)
        chronometer.start()

        endCallIV.setOnClickListener {
            chronometer.stop()
            this.finish()
        }
    }
}
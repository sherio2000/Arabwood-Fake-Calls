package com.example.awfc.ui.voiceDeviceUI

import android.content.Context
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.Chronometer
import android.widget.ImageView
import android.widget.TextView
import com.example.awfc.R
import com.example.awfc.data.VoiceCaller
import com.example.awfc.utils.ScheduleVoiceCall
import com.example.awfc.utils.SharedPreferences
import com.example.awfc.utils.TAG

class IncomingVoiceCallSamsungS7Home : AppCompatActivity() {

    private lateinit var chronometer: Chronometer
    private var mp: MediaPlayer? = MediaPlayer()
    private var caller: VoiceCaller? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        ScheduleVoiceCall().showWhenLockedAndTurnScreenOn(this)
        ScheduleVoiceCall().hideSystemBars(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_incoming_voice_call_samsung_s7_home)

        val callerNameTv = findViewById<TextView>(R.id.callerIdTv)
        val callerMobileTv = findViewById<TextView>(R.id.callerNumTv)

        caller = intent.getParcelableExtra("caller")
        caller?.let { ScheduleVoiceCall().placeCallerInfo(callerNameTv, callerMobileTv, it) }


        chronometer = findViewById(R.id.chronometerSamsungS7)
        chronometer.start()

        ScheduleVoiceCall().createMediaPlayer(SharedPreferences().getAudioUri(this@IncomingVoiceCallSamsungS7Home), mp, this)

        val endCallBtn = findViewById<ImageView>(R.id.endCallBtnIV)
        endCallBtn.setOnClickListener {
            mp?.stop()
            mp?.release()
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
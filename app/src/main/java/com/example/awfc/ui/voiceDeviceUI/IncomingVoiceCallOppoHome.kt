package com.example.awfc.ui.voiceDeviceUI

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Chronometer
import android.widget.ImageView
import android.widget.TextView
import com.example.awfc.R
import com.example.awfc.data.VoiceCaller
import com.example.awfc.utils.ScheduleVoiceCall
import com.example.awfc.utils.SharedPreferences

class IncomingVoiceCallOppoHome : AppCompatActivity() {

    private lateinit var chronometer: Chronometer
    private var caller: VoiceCaller? = null
    private var mp: MediaPlayer? = MediaPlayer()

    override fun onCreate(savedInstanceState: Bundle?) {
        ScheduleVoiceCall().showWhenLockedAndTurnScreenOn(this)
        ScheduleVoiceCall().hideSystemBars(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_incoming_voice_call_oppo_home)

        val callerNameTv = findViewById<TextView>(R.id.callerIdTv)
        val callerNumTv = findViewById<TextView>(R.id.callerNumTv)

        caller = intent.getParcelableExtra("caller")
        caller?.let { ScheduleVoiceCall().placeCallerInfo(callerNameTv, callerNumTv, it) }


        ScheduleVoiceCall().createMediaPlayer(SharedPreferences().getAudioUri(this@IncomingVoiceCallOppoHome), mp, this)


        chronometer = findViewById(R.id.chronometerOppo)
        chronometer.start()

        val endCallBtn = findViewById<ImageView>(R.id.endCallBtnIV)
        endCallBtn.setOnClickListener {
            chronometer.stop()
            this.finish()
        }
    }
}
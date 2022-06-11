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

class IncomingVoiceCallIos122Home : AppCompatActivity() {
    private var caller: VoiceCaller? = null
    private var mp: MediaPlayer? = MediaPlayer()
    override fun onCreate(savedInstanceState: Bundle?) {
        ScheduleVoiceCall().showWhenLockedAndTurnScreenOn(this)
        ScheduleVoiceCall().hideSystemBars(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_incoming_voice_call_ios122_home)

        val chronometer = findViewById<Chronometer>(R.id.chronometerIphone12_1)
        chronometer.start()

        val callerNameTv = findViewById<TextView>(R.id.callerNameTv)

        ScheduleVoiceCall().createMediaPlayer(SharedPreferences().getAudioUri(this@IncomingVoiceCallIos122Home), mp, this)

        caller = intent.getParcelableExtra("caller")
        caller?.let { ScheduleVoiceCall().placeCallerInfo(callerNameTv, null, it) }


        val endCallBtn = findViewById<ImageView>(R.id.endCallBtnIV)
        endCallBtn.setOnClickListener {
            chronometer.stop()
            this.finish()
        }
    }
}
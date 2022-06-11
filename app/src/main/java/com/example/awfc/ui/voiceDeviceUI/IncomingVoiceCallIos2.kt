package com.example.awfc.ui.voiceDeviceUI

import android.content.Intent
import android.media.MediaPlayer
import android.media.RingtoneManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.awfc.R
import com.example.awfc.data.VoiceCaller
import com.example.awfc.utils.ScheduleVoiceCall

class IncomingVoiceCallIos2 : AppCompatActivity() {

    private var mp: MediaPlayer? = null
    private var caller: VoiceCaller? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        ScheduleVoiceCall().showWhenLockedAndTurnScreenOn(this)
        ScheduleVoiceCall().hideSystemBars(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_incoming_voice_call_ios2)

        val notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)
        val callerNameTv = findViewById<TextView>(R.id.callerNameTv)

        caller = intent.getParcelableExtra("caller")
        caller?.let { ScheduleVoiceCall().placeCallerInfo(callerNameTv, null, it) }

        mp = MediaPlayer.create(this, notification)
        mp?.start()


        val declineBtn = findViewById<ImageView>(R.id.declineBtnIV)
        val answerBtn = findViewById<ImageView>(R.id.acceptBtn)
        declineBtn.setOnClickListener {
            mp?.stop()
            this.finish()
        }
        answerBtn.setOnClickListener { mp?.stop()
            this@IncomingVoiceCallIos2.finish()
            val intent = Intent(this@IncomingVoiceCallIos2, IncomingVoiceCallIos122Home::class.java)
            intent.putExtra("caller", caller)
            startActivity(intent) }
    }

    override fun onDestroy() {
        super.onDestroy()
        mp?.stop()
    }
}
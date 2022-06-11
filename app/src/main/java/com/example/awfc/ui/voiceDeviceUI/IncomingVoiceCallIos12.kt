package com.example.awfc.ui.voiceDeviceUI

import android.content.Intent
import android.media.MediaPlayer
import android.media.RingtoneManager
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.awfc.R
import com.example.awfc.data.VoiceCaller
import com.example.awfc.utils.ScheduleVoiceCall
import com.ncorti.slidetoact.SlideToActView


class IncomingVoiceCallIos12 : AppCompatActivity() {
    private var mp: MediaPlayer? = null
    private var caller: VoiceCaller? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        ScheduleVoiceCall().showWhenLockedAndTurnScreenOn(this)
        ScheduleVoiceCall().hideSystemBars(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_incoming_voice_call_ios12)

        val acceptSlider = findViewById<SlideToActView>(R.id.answerBtnSlider)

        val notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)

        val callerNameTv = findViewById<TextView>(R.id.callerNameTv)

        caller = intent.getParcelableExtra("caller")
        caller?.let { ScheduleVoiceCall().placeCallerInfo(callerNameTv, null, it) }


        mp = MediaPlayer.create(this, notification)
        mp?.start()

        acceptSlider.animDuration = 1
        acceptSlider.onSlideCompleteListener = object : SlideToActView.OnSlideCompleteListener {
            override fun onSlideComplete(view: SlideToActView) {
                mp?.stop()
                this@IncomingVoiceCallIos12.finish()
                val intent = Intent(this@IncomingVoiceCallIos12, IncomingVoiceCallIos12Home::class.java)
                intent.putExtra("caller", caller)
                startActivity(intent)
            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mp?.stop()
    }
}
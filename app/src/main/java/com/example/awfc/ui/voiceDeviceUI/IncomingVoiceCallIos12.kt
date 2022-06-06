package com.example.awfc.ui.voiceDeviceUI

import android.content.Intent
import android.media.MediaPlayer
import android.media.RingtoneManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.awfc.R
import com.ncorti.slidetoact.SlideToActView


class IncomingVoiceCallIos12 : AppCompatActivity() {
    private var mp: MediaPlayer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_incoming_voice_call_ios12)

        val acceptSlider = findViewById<SlideToActView>(R.id.answerBtnSlider)

        val notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)

        mp = MediaPlayer.create(this, notification)
        mp?.start()

        acceptSlider.animDuration = 1
        acceptSlider.onSlideCompleteListener = object : SlideToActView.OnSlideCompleteListener {
            override fun onSlideComplete(view: SlideToActView) {
                mp?.stop()
                this@IncomingVoiceCallIos12.finish()
                val intent = Intent(this@IncomingVoiceCallIos12, IncomingVoiceCallIos12Home::class.java)
                startActivity(intent)
            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mp?.stop()
    }
}
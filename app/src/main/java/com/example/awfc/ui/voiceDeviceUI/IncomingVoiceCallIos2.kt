package com.example.awfc.ui.voiceDeviceUI

import android.content.Intent
import android.media.MediaPlayer
import android.media.RingtoneManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.awfc.R

class IncomingVoiceCallIos2 : AppCompatActivity() {

    private var mp: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_incoming_voice_call_ios2)

        val notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)

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
            val intent = Intent(this@IncomingVoiceCallIos2, IncomingVoiceCall_Ios12_2_home::class.java)
            startActivity(intent) }
    }

    override fun onDestroy() {
        super.onDestroy()
        mp?.stop()
    }
}
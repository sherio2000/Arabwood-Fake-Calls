package com.example.awfc.ui.voiceDeviceUI

import android.content.Intent
import android.media.MediaPlayer
import android.media.RingtoneManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.example.awfc.R
import com.example.awfc.utils.OnSwipeTouchListener

class IncomingVoiceCallOppo : AppCompatActivity() {
    private var mp: MediaPlayer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_incoming_voice_call_oppo)

        val notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)

        mp = MediaPlayer.create(this, notification)
        mp?.start()

        val answerBtn = findViewById<ImageView>(R.id.answerBtnIV)
        answerBtn.startAnimation(AnimationUtils.loadAnimation(this, R.anim.shake_stronger))

        val declineBtn = findViewById<ImageView>(R.id.declineBtnIV)
        declineBtn.startAnimation(AnimationUtils.loadAnimation(this, R.anim.shake))

        val messageBtn = findViewById<ImageView>(R.id.messageBtnIV)
        messageBtn.startAnimation(AnimationUtils.loadAnimation(this, R.anim.shake2))

        declineBtn.setOnTouchListener(object : OnSwipeTouchListener(this@IncomingVoiceCallOppo) {
            override fun onSwipeUp() {
                mp?.stop()
                this@IncomingVoiceCallOppo.finish()
                super.onSwipeUp()
            }

        })

        answerBtn.setOnTouchListener(object : OnSwipeTouchListener(this@IncomingVoiceCallOppo) {
            override fun onSwipeUp() {
                mp?.stop()
                this@IncomingVoiceCallOppo.finish()
                val intent = Intent(this@IncomingVoiceCallOppo, IncomingVoiceCallOppo::class.java)
                startActivity(intent)
                super.onSwipeUp()
            }

        })
    }

    override fun onDestroy() {
        super.onDestroy()
        mp?.stop()
    }
}
package com.example.awfc.ui.voiceDeviceUI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.example.awfc.R

class IncomingVoiceCallOppo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_incoming_voice_call_oppo)

        val answerBtn = findViewById<ImageView>(R.id.answerBtnIV)
        answerBtn.startAnimation(AnimationUtils.loadAnimation(this, R.anim.shake_stronger))

        val declineBtn = findViewById<ImageView>(R.id.declineBtnIV)
        declineBtn.startAnimation(AnimationUtils.loadAnimation(this, R.anim.shake))

        val messageBtn = findViewById<ImageView>(R.id.messageBtnIV)
        messageBtn.startAnimation(AnimationUtils.loadAnimation(this, R.anim.shake2))
    }
}
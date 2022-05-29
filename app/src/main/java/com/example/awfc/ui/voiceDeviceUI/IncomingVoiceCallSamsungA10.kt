package com.example.awfc.ui.voiceDeviceUI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.example.awfc.R

class IncomingVoiceCallSamsungA10 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_incoming_voice_call_samsung_a10)

        val chevronRight = findViewById<ImageView>(R.id.chevronRightIV)
        chevronRight.startAnimation(AnimationUtils.loadAnimation(this, R.anim.right_slide_chevron))

        val chevronLeft = findViewById<ImageView>(R.id.chevronLeftIV)
        chevronLeft.startAnimation(AnimationUtils.loadAnimation(this, R.anim.left_slide_chevron))
    }
}
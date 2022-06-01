package com.example.awfc.ui.voiceDeviceUI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.example.awfc.R

class IncomingVoiceCallSamsungS7 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_incoming_voice_call_samsung_s7)

        val leftChevron1 = findViewById<ImageView>(R.id.chevronLeftIV1)
        leftChevron1.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fadein))

        val leftChevron2 = findViewById<ImageView>(R.id.chevronLeftIV2)
        leftChevron2.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fadein2))

        val leftChevron3 = findViewById<ImageView>(R.id.chevronLeftIV3)
        leftChevron3.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fadein3))


        val rightChevron1 = findViewById<ImageView>(R.id.chevronRightIV1)
        rightChevron1.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fadein))

        val rightChevron2 = findViewById<ImageView>(R.id.chevronRightIV2)
        rightChevron2.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fadein2))

        val rightChevron3 = findViewById<ImageView>(R.id.chevronRightIV3)
        rightChevron3.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fadein3))
    }
}
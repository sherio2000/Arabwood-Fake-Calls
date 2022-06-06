package com.example.awfc.ui.voiceDeviceUI

import android.content.Intent
import android.media.MediaPlayer
import android.media.RingtoneManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.example.awfc.R
import com.example.awfc.utils.OnSwipeTouchListener

class IncomingVoiceCallSamsungS7 : AppCompatActivity() {
    private var mp: MediaPlayer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_incoming_voice_call_samsung_s7)

        val notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)

        mp = MediaPlayer.create(this, notification)
        mp?.start()

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

        val declineBtn = findViewById<ImageView>(R.id.declineBtnIV)

        val answerBtn = findViewById<ImageView>(R.id.answerBtnIV)

        declineBtn.setOnTouchListener(object : OnSwipeTouchListener(this@IncomingVoiceCallSamsungS7) {


            override fun onSwipeLeft() {
                mp?.stop()
                this@IncomingVoiceCallSamsungS7.finish()
                super.onSwipeLeft()
            }

        })

        answerBtn.setOnTouchListener(object : OnSwipeTouchListener(this@IncomingVoiceCallSamsungS7) {

            override fun onSwipeRight() {
                mp?.stop()
                this@IncomingVoiceCallSamsungS7.finish()
                val intent = Intent(this@IncomingVoiceCallSamsungS7, IncomingVoiceCallSamsungS7Home::class.java)
                startActivity(intent)
                super.onSwipeLeft()
            }

        })


    }

    override fun onDestroy() {
        super.onDestroy()
        mp?.stop()
    }
}
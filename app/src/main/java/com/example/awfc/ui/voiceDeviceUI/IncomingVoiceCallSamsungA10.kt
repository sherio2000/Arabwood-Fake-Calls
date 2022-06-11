package com.example.awfc.ui.voiceDeviceUI

import android.annotation.SuppressLint
import android.content.Intent
import android.media.Image
import android.media.MediaPlayer
import android.media.RingtoneManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import com.example.awfc.R
import com.example.awfc.data.VoiceCaller
import com.example.awfc.utils.OnSwipeTouchListener
import com.example.awfc.utils.ScheduleVoiceCall

class IncomingVoiceCallSamsungA10 : AppCompatActivity() {
    private var mp: MediaPlayer? = null
    private var caller: VoiceCaller? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        ScheduleVoiceCall().showWhenLockedAndTurnScreenOn(this)
        ScheduleVoiceCall().hideSystemBars(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_incoming_voice_call_samsung_a10)

        val callerNameTv = findViewById<TextView>(R.id.callerIdTv)
        val callerMobileTv = findViewById<TextView>(R.id.callerNumTv)

        caller = intent.getParcelableExtra("caller")
        caller?.let { ScheduleVoiceCall().placeCallerInfo(callerNameTv, callerMobileTv, it) }


        val notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)

        mp = MediaPlayer.create(this, notification)
        mp?.start()

        val chevronRight = findViewById<ImageView>(R.id.chevronRightIV)
        chevronRight.startAnimation(AnimationUtils.loadAnimation(this, R.anim.right_slide_chevron))

        val chevronLeft = findViewById<ImageView>(R.id.chevronLeftIV)
        chevronLeft.startAnimation(AnimationUtils.loadAnimation(this, R.anim.left_slide_chevron))

        val declineBtn = findViewById<ImageView>(R.id.declineBtnIV)
        val answerBtn = findViewById<ImageView>(R.id.answerBtnIV)


        declineBtn.setOnTouchListener(object : OnSwipeTouchListener(this@IncomingVoiceCallSamsungA10) {
            override fun onSwipeLeft() {
                mp?.stop()
                this@IncomingVoiceCallSamsungA10.finish()
                super.onSwipeUp()
            }

        })

        answerBtn.setOnTouchListener(object : OnSwipeTouchListener(this@IncomingVoiceCallSamsungA10) {
            override fun onSwipeRight() {
                mp?.stop()
                this@IncomingVoiceCallSamsungA10.finish()
                val intent = Intent(this@IncomingVoiceCallSamsungA10, IncomingVoiceCallSamsungA10Home::class.java)
                intent.putExtra("caller", caller)
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
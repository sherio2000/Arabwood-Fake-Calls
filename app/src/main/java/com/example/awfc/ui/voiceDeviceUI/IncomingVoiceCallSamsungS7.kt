package com.example.awfc.ui.voiceDeviceUI

import android.content.Intent
import android.media.MediaPlayer
import android.media.Ringtone
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
import com.example.awfc.utils.SharedPreferences

class IncomingVoiceCallSamsungS7 : AppCompatActivity() {
    private var notification: Ringtone? = null
    private var caller: VoiceCaller? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        ScheduleVoiceCall().showWhenLockedAndTurnScreenOn(this)
        ScheduleVoiceCall().hideSystemBars(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_incoming_voice_call_samsung_s7)

        val callerNameTv = findViewById<TextView>(R.id.callerIdTv)
        val callerMobileTv = findViewById<TextView>(R.id.callerNumTv)

        caller = intent.getParcelableExtra("caller")
        caller?.let { ScheduleVoiceCall().placeCallerInfo(callerNameTv, callerMobileTv, it) }

        notification = RingtoneManager.getRingtone(this, SharedPreferences().getRingtoneUri(this))

        notification?.play()

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
                notification?.stop()
                this@IncomingVoiceCallSamsungS7.finish()
                super.onSwipeLeft()
            }

        })

        answerBtn.setOnTouchListener(object : OnSwipeTouchListener(this@IncomingVoiceCallSamsungS7) {

            override fun onSwipeRight() {
                notification?.stop()
                this@IncomingVoiceCallSamsungS7.finish()
                val intent = Intent(this@IncomingVoiceCallSamsungS7, IncomingVoiceCallSamsungS7Home::class.java)
                intent.putExtra("caller", caller)
                startActivity(intent)
                super.onSwipeLeft()
            }

        })


    }

    override fun onDestroy() {
        super.onDestroy()
        notification?.stop()
    }
}
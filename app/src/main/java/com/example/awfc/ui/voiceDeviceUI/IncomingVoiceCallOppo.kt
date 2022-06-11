package com.example.awfc.ui.voiceDeviceUI

import android.content.Intent
import android.media.MediaPlayer
import android.media.RingtoneManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import com.example.awfc.R
import com.example.awfc.data.VoiceCaller
import com.example.awfc.utils.OnSwipeTouchListener
import com.example.awfc.utils.ScheduleVoiceCall

class IncomingVoiceCallOppo : AppCompatActivity() {
    private var mp: MediaPlayer? = null
    private var caller: VoiceCaller? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        ScheduleVoiceCall().showWhenLockedAndTurnScreenOn(this)
        ScheduleVoiceCall().hideSystemBars(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_incoming_voice_call_oppo)

        val notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)

        mp = MediaPlayer.create(this, notification)
        mp?.start()

        val callerNameTv = findViewById<TextView>(R.id.callerIdTv)
        val callerNumTv = findViewById<TextView>(R.id.callerNumTv)

        caller = intent.getParcelableExtra("caller")
        caller?.let { ScheduleVoiceCall().placeCallerInfo(callerNameTv, callerNumTv, it) }


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
                val intent = Intent(this@IncomingVoiceCallOppo, IncomingVoiceCallOppoHome::class.java)
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
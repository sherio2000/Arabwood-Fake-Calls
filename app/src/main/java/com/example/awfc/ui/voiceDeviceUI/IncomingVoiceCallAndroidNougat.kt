package com.example.awfc.ui.voiceDeviceUI

import android.annotation.SuppressLint
import android.content.Context
import android.media.MediaPlayer
import android.media.RingtoneManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.example.awfc.R
import com.example.awfc.utils.OnSwipeTouchListener
import com.google.android.material.floatingactionbutton.FloatingActionButton

class IncomingVoiceCallAndroidNougat : AppCompatActivity() {

    private var handlerAnimation = Handler()
    private var mp: MediaPlayer? = null
    private var statusAnimation = false

    @SuppressLint("ClickableViewAccessibility", "UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_incoming_voice_call_android_nougat)
        val notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)

        mp = MediaPlayer.create(this, notification)
        mp?.start()

        startPulse();

        val answerIcon = findViewById<ImageView>(R.id.answerIconIV)
        val declineIcon = findViewById<ImageView>(R.id.declineIconIV)




        val answerDrawable = "@drawable/ic_call"
        val declineDrawable = "@drawable/ic_call_end"

        val answerDrawableResource = resources.getIdentifier(answerDrawable, null, packageName)
        val declineDrawableResource = resources.getIdentifier(declineDrawable, null, packageName)


        answerIcon.setImageDrawable(resources.getDrawable(answerDrawableResource))

        declineIcon.setImageDrawable(resources.getDrawable(declineDrawableResource))

        val pulsatingBtn = findViewById<FloatingActionButton>(R.id.pulsatingCallBtn)
        pulsatingBtn.setOnTouchListener(object : OnSwipeTouchListener(this@IncomingVoiceCallAndroidNougat) {
            override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
                if(motionEvent.action == MotionEvent.ACTION_UP)
                {

                    Log.i("Button Pressed!", " Button Pressed")
                    answerIcon.visibility = View.GONE
                    declineIcon.visibility = View.GONE
                } else if (motionEvent.action == MotionEvent.ACTION_DOWN)
                {
                    Log.i("Button Released!", " Button Released")
                    answerIcon.visibility = View.VISIBLE
                    declineIcon.visibility = View.VISIBLE
                }


                return super.onTouch(view, motionEvent)
            }

            override fun onSwipeLeft() {
                mp?.stop()
                stopPulse()
                this@IncomingVoiceCallAndroidNougat.finish()
                super.onSwipeLeft()
            }

            override fun onSwipeRight() {
                mp?.stop()
                stopPulse()
                Toast.makeText(
                    this@IncomingVoiceCallAndroidNougat,
                    "Swipe Right gesture detected",
                    Toast.LENGTH_SHORT
                ).show()
                super.onSwipeRight()
            }
        })
    }

    override fun onCreateView(
        parent: View?,
        name: String,
        context: Context,
        attrs: AttributeSet
    ): View? {
        //runnable.run()
        return super.onCreateView(parent, name, context, attrs)


    }

    private fun startPulse() {
        runnable.run()
    }

    private fun stopPulse() {
        handlerAnimation.removeCallbacks(runnable)
    }


    private var runnable = object : Runnable {
        override fun run() {

            val imgAnimation1 = findViewById<ImageView>(R.id.imgAnimation1)
            val imgAnimation2 = findViewById<ImageView>(R.id.imgAnimation2)

            imgAnimation1.animate().scaleX(4f).scaleY(4f).alpha(0f).setDuration(1000)
                .withEndAction {
                    imgAnimation1.scaleX = 1f
                    imgAnimation1.scaleY = 1f
                    imgAnimation1.alpha = 1f
                }

            imgAnimation2.animate().scaleX(4f).scaleY(4f).alpha(0f).setDuration(700)
                .withEndAction {
                    imgAnimation2.scaleX = 1f
                    imgAnimation2.scaleY = 1f
                    imgAnimation2.alpha = 1f
                }

            handlerAnimation.postDelayed(this, 1500)
        }
    }
}
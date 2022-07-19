package com.example.awfc.ui.voiceDeviceUI

import android.content.Intent
import android.media.MediaPlayer
import android.media.Ringtone
import android.media.RingtoneManager
import android.net.Uri
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import com.example.awfc.R
import com.example.awfc.data.VoiceCaller
import com.example.awfc.utils.ScheduleVoiceCall
import com.example.awfc.utils.SharedPreferences
import com.ncorti.slidetoact.SlideToActView


class IncomingVoiceCallIos12 : AppCompatActivity() {
    private var notification: Ringtone? = null
    private var caller: VoiceCaller? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        ScheduleVoiceCall().showWhenLockedAndTurnScreenOn(this)
        ScheduleVoiceCall().hideSystemBars(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_incoming_voice_call_ios12)

        val acceptSlider = findViewById<SlideToActView>(R.id.answerBtnSlider)

        notification = RingtoneManager.getRingtone(this, SharedPreferences().getRingtoneUri(this))

        val callerNameTv = findViewById<TextView>(R.id.callerNameTv)

        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        val vibrationSwitch = prefs.getBoolean("Vibrate", true)

        caller = intent.getParcelableExtra("caller")
        caller?.let { ScheduleVoiceCall().placeCallerInfo(callerNameTv, null, it) }

        notification?.play()
        if(vibrationSwitch)
            ScheduleVoiceCall().vibratePhone(this)

        acceptSlider.animDuration = 1
        acceptSlider.onSlideCompleteListener = object : SlideToActView.OnSlideCompleteListener {
            override fun onSlideComplete(view: SlideToActView) {
                notification?.stop()
                ScheduleVoiceCall().stopVibration(this@IncomingVoiceCallIos12)
                this@IncomingVoiceCallIos12.finish()
                val intent = Intent(this@IncomingVoiceCallIos12, IncomingVoiceCallIos12Home::class.java)
                intent.putExtra("caller", caller)
                startActivity(intent)
            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        ScheduleVoiceCall().stopVibration(this@IncomingVoiceCallIos12)
        notification?.stop()
    }
}
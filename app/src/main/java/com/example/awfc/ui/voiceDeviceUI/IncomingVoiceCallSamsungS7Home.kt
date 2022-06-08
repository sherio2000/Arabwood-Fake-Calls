package com.example.awfc.ui.voiceDeviceUI

import android.content.Context
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.Chronometer
import android.widget.ImageView
import com.example.awfc.R
import com.example.awfc.utils.SharedPreferences
import com.example.awfc.utils.TAG

class IncomingVoiceCallSamsungS7Home : AppCompatActivity() {

    private lateinit var chronometer: Chronometer
    private var mp: MediaPlayer? = MediaPlayer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_incoming_voice_call_samsung_s7_home)

        chronometer = findViewById(R.id.chronometerSamsungS7)
        chronometer.start()

        createMediaPlayer(SharedPreferences().getAudioUri(this@IncomingVoiceCallSamsungS7Home))

        val endCallBtn = findViewById<ImageView>(R.id.endCallBtnIV)
        endCallBtn.setOnClickListener {
            mp?.stop()
            mp?.release()
            chronometer.stop()
            this.finish()
        }
    }

    override fun onDestroy() {
        chronometer.stop()
        this.finish()
        super.onDestroy()
    }

    override fun onBackPressed() {
        chronometer.stop()
        this.finish()
        super.onBackPressed()
    }

    private fun createMediaPlayer(uri: Uri)
    {
        mp?.setAudioAttributes(
            AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setLegacyStreamType(AudioManager.STREAM_VOICE_CALL)
                .build()
        )
        try {
            mp?.setDataSource(applicationContext, uri)
            mp?.prepare()
            mp?.start()
        } catch (e: Exception)
        {
            Log.d(TAG, "Error Playing Audio File")
        }
    }
}
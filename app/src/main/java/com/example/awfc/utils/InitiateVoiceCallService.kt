package com.example.awfc.utils

import android.app.job.JobParameters
import android.app.job.JobService
import android.content.ContentValues.TAG
import android.content.Intent
import android.util.Log
import com.example.awfc.data.Artist
import com.example.awfc.data.VoiceCaller
import com.example.awfc.ui.IncomingRingingActivity

import kotlinx.coroutines.*


@DelicateCoroutinesApi
class InitiateVoiceCallService : JobService() {

    override fun onStartJob(params: JobParameters?): Boolean {
        //p0.extras
        val newIntent =
            params?.extras?.getInt("deviceId")
                ?.let { ScheduleVoiceCall().getDeviceIntent(it, this) }
        val caller = VoiceCaller(
                params?.extras?.getString("callerName").toString(),
                params?.extras?.getString("callerMobile").toString(),

        )

        if (newIntent != null) {
            newIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        newIntent?.putExtra("caller", caller)
        startActivity(newIntent)
        Log.i(TAG, "Incoming Call Service")
        stopSelf(11)
        return false
    }


    override fun onStopJob(p0: JobParameters?): Boolean {
        Log.i(TAG, "Incoming Call Service Stopped!!")
        stopSelf()
        return true
    }


}
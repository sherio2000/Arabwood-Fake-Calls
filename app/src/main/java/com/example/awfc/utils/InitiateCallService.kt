package com.example.awfc.utils

import android.app.job.JobParameters
import android.app.job.JobService
import android.content.ContentValues.TAG
import android.content.Intent
import android.util.Log
import com.example.awfc.data.Artist
import com.example.awfc.ui.IncomingRingingActivity

import kotlinx.coroutines.*


@DelicateCoroutinesApi
class InitiateCallService : JobService() {

    override fun onStartJob(params: JobParameters?): Boolean {
        //p0.extras
        val newIntent = Intent(this , IncomingRingingActivity::class.java)
        val artist = Artist(
                0,
                params?.extras?.getString("artistName").toString(),
                params?.extras?.getString("arabicName").toString(),
                params?.extras?.getString("arabicDesc").toString(),
                params?.extras?.getString("artistDesc").toString(),
                params?.extras?.getString("artistImage").toString(),
                params?.extras?.getString("artistVideo1").toString(),
                params?.extras?.getString("artistVideo2").toString(),
                params?.extras?.getString("artistVideo3").toString()
        )

        newIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        newIntent.putExtra("artist", artist)
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
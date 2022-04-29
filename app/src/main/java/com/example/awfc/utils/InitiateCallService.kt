package com.example.awfc.utils

import android.app.job.JobInfo
import android.app.job.JobParameters
import android.app.job.JobScheduler
import android.app.job.JobService
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.loader.content.AsyncTaskLoader
import com.example.awfc.data.Artist
import com.example.awfc.ui.IncomingRingingActivity
import java.util.*
import kotlin.concurrent.schedule
import kotlin.properties.Delegates
import android.R
import android.app.*

import android.graphics.Color

import androidx.core.app.NotificationCompat
import android.app.PendingIntent
import android.os.*
import kotlinx.coroutines.*
import java.lang.Runnable


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
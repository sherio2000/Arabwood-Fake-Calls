package com.example.awfc.utils

import android.app.Activity
import android.app.AlarmManager
import android.app.PendingIntent
import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import android.os.PersistableBundle
import android.util.Log
import android.view.WindowManager
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.coroutineScope
import com.example.awfc.data.VoiceCaller
import com.example.awfc.data.VoiceCallerHistory
import com.example.awfc.ui.voiceDeviceUI.*
import com.example.awfc.viewmodels.MainViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

class ScheduleVoiceCall {

    @RequiresApi(Build.VERSION_CODES.O)
    @DelicateCoroutinesApi
    fun scheduleCall(duration: Int, callerName: String, callerMobile: String?, deviceId: Int, context: Context, audioUri: String, viewModelStoreOwner: ViewModelStoreOwner)
    {
        val jobScheduler = context.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        val bundle = PersistableBundle()
        bundle.putInt("deviceId", deviceId)
        bundle.putString("callerName", callerName)
        bundle.putString("audioUri", audioUri)
        bundle.putString("callerMobile", callerMobile)
        val current = LocalDateTime.now()

        val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
        val formatted = current.format(formatter)

        val jobInfo = JobInfo.Builder(12, ComponentName(context , InitiateVoiceCallService::class.java))
            // only add if network access is required
            .setExtras(bundle)
            .setPersisted(true)
            .setMinimumLatency(duration.toLong())
            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY).build()
        jobScheduler.schedule(jobInfo)
    }
    private fun saveCallRecordDb(voiceCallerHistory: VoiceCallerHistory, owner: ViewModelStoreOwner)
    {
        val voiceCallRecord = VoiceCallerHistory(
            voiceCallerHistory.id,
            voiceCallerHistory.callerName,
            voiceCallerHistory.callerNumber,
            voiceCallerHistory.duration,
            voiceCallerHistory.device,
            voiceCallerHistory.voicePlayback,
            voiceCallerHistory.dateTime
        )
        var viewModel = ViewModelProvider(owner).get(MainViewModel::class.java)
        viewModel.saveVoiceCallRecord(voiceCallRecord)
    }


    fun getDeviceIntent(deviceId: Int, context: Context) : Intent
    {
        when(deviceId) {
            1 -> {
                return Intent(context, IncomingVoiceCallAndroidNougat::class.java)

            }
            2 -> {
                return Intent(context, IncomingVoiceCallIos2::class.java)
            }
            3 -> {
                return Intent(context, IncomingVoiceCallIos12::class.java)
            }
            4 -> {
                return Intent(context, IncomingVoiceCallSamsungA10::class.java)
            }
            5 -> {
                return Intent(context, IncomingVoiceCallSamsungS7::class.java)
            }
            6 -> {
                return Intent(context, IncomingVoiceCallOppo::class.java)
            }
        }
        return Intent(context, IncomingVoiceCallAndroidNougat::class.java)
    }

    fun placeCallerInfo(callerNameTv: TextView?, callerMobileTv: TextView?, voiceCaller: VoiceCaller)
    {
        if (callerNameTv != null) {
            callerNameTv.text = voiceCaller.callerName
        }
        if (callerMobileTv != null) {
            callerMobileTv.text = voiceCaller.callerMobile
        }
    }

    fun hideSystemBars(activity: Activity) {
        val windowInsetsController =
            ViewCompat.getWindowInsetsController(activity.window.decorView) ?: return
        // Configure the behavior of the hidden system bars
        windowInsetsController.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        // Hide both the status bar and the navigation bar
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())
    }

    fun showWhenLockedAndTurnScreenOn(activity: Activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
            activity.setShowWhenLocked(true)
            activity.setTurnScreenOn(true)
        } else {
            activity.window.addFlags(
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                        or WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
            )
        }
    }
    fun createMediaPlayer(uri: Uri, mp: MediaPlayer?, activity: Activity)
    {
        mp?.setAudioAttributes(
            AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setLegacyStreamType(AudioManager.STREAM_VOICE_CALL)
                .build()
        )
        try {
            mp?.setDataSource(activity.applicationContext, uri)
            mp?.prepare()
            mp?.start()
        } catch (e: Exception)
        {
            Log.d(TAG, "Error Playing Audio File")
        }

    }
}
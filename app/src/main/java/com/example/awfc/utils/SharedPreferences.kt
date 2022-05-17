package com.example.awfc.utils

import android.app.Activity
import android.content.Context

class SharedPreferences {

    private val PREF_NAME = "voiceCall"
    private val Audio_Voice_Call_Pref_Name = "audio"
    private val Device_Voice_Call_Pref_Name = "device"
    private val Duration_Voice_Call_Pref_Name = "duration"

    fun setDefaultVoiceCallPreferences(activity: Activity)
    {
        val sharedPreferences = activity.getSharedPreferences(this.PREF_NAME ,Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.putInt(Device_Voice_Call_Pref_Name, 1)
        editor.putInt(Duration_Voice_Call_Pref_Name, 5)
        editor.putString(Audio_Voice_Call_Pref_Name, "")
        editor.apply()
    }

    fun getAudioVoiceCallPreference(activity: Activity) : String
    {
        val sharedPreferences = activity.getSharedPreferences(this.PREF_NAME ,Context.MODE_PRIVATE)
        return sharedPreferences.getString(Audio_Voice_Call_Pref_Name, "").toString()
    }

    fun getDeviceVoiceCallPreference(activity: Activity) : Int
    {
        val sharedPreferences = activity.getSharedPreferences(this.PREF_NAME ,Context.MODE_PRIVATE)
        return sharedPreferences.getInt(Device_Voice_Call_Pref_Name, 1)
    }

    fun getDeviceVoiceCallPreference(context: Context) : Int
    {
        val sharedPreferences = context.getSharedPreferences(this.PREF_NAME ,Context.MODE_PRIVATE)
        return sharedPreferences.getInt(Device_Voice_Call_Pref_Name, 1)
    }

    fun getDurationVoiceCallPreference(context: Context) : Int
    {
        val sharedPreferences = context.getSharedPreferences(this.PREF_NAME ,Context.MODE_PRIVATE)
        return sharedPreferences.getInt(Duration_Voice_Call_Pref_Name, 0)
    }

    fun getDurationVoiceCallPreferenceString(context: Context) : String
    {
        val sharedPreferences = context.getSharedPreferences(this.PREF_NAME ,Context.MODE_PRIVATE)
        when {
            sharedPreferences.getInt(Duration_Voice_Call_Pref_Name, 2)  == 0 -> {
                return "Now"
            }
            sharedPreferences.getInt(Duration_Voice_Call_Pref_Name, 2)  == 10000 -> {
                return "10s"
            }
            sharedPreferences.getInt(Duration_Voice_Call_Pref_Name, 2)  == 30000 -> {
                return "30s"
            }
            sharedPreferences.getInt(Duration_Voice_Call_Pref_Name, 2)  == 60000 -> {
                return "1m"
            }
            sharedPreferences.getInt(Duration_Voice_Call_Pref_Name, 2)  == 300000 -> {
                return "5m"
            }
            sharedPreferences.getInt(Duration_Voice_Call_Pref_Name, 2)  == 600000 -> {
                return "10m"
            }
            else -> return "Now"
        }

    }

    fun setDurationVoiceCallPreference(context: Context, duration: Int) {
        val sharedPreferences = context.getSharedPreferences(this.PREF_NAME ,Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt(Duration_Voice_Call_Pref_Name, duration)
        editor.apply()
    }

    fun setDeviceVoiceCallPreference(activity: Activity, index: Int) {
        val sharedPreferences = activity.getSharedPreferences(this.PREF_NAME ,Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt(Device_Voice_Call_Pref_Name, index)
        editor.apply()
    }

}
package com.example.awfc.ui

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Toast
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.example.awfc.R
import android.provider.Settings
import androidx.core.app.ActivityCompat.startActivityForResult

import android.media.RingtoneManager

import android.content.Intent
import android.net.Uri
import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.provider.ContactsContract
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.lifecycle.ProcessLifecycleOwner
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.preference.PreferenceManager
import com.example.awfc.utils.SharedPreferences
import com.example.awfc.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class SettingsFragment : PreferenceFragmentCompat() {
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preference, rootKey)
        val ringtonePref = findPreference<Preference>("Ringtone")
        val vibrationPref = findPreference<Preference>("Vibrate")
        val languagePref = findPreference<Preference>("language")
        val policyPref = findPreference<Preference>("policy")
        val voiceCallHistoryPref = findPreference<Preference>("Clear Voice Call History")
        val rateUsPref = findPreference<Preference>("Rate Us")
        if(context?.let { SharedPreferences().getLanguage(it) } == "AR")
        {
            languagePref?.title = "اللغه"
        }
        if(context?.let { SharedPreferences().getLanguage(it) } == "AR")
        {
            policyPref?.title = "سياسة التطبيق"
        }
        if(context?.let { SharedPreferences().getLanguage(it) } == "AR")
        {
            ringtonePref?.title = "نعمه الرنين"
        }
        if(context?.let { SharedPreferences().getLanguage(it) } == "AR")
        {
            vibrationPref?.title = "رنين الاهتزاز"
        }
        if(context?.let { SharedPreferences().getLanguage(it) } == "AR")
        {
            voiceCallHistoryPref?.title = "مسح سجل المكالمات الصوتية"
        }
        if(context?.let { SharedPreferences().getLanguage(it) } == "AR")
        {
            rateUsPref?.title = "قيمنا"
        }
        if(context?.let { SharedPreferences().getLanguage(it) } == "AR")
        {
            languagePref?.setDefaultValue(2)
        }
        val currentRingtone = RingtoneManager.getRingtone(context, SharedPreferences().getRingtoneUri(requireActivity()))
        ringtonePref?.summary = currentRingtone.getTitle(context)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onPreferenceTreeClick(preference: Preference): Boolean {
        return when (preference.key) {
            getString(R.string.ringtone_pref_name) -> {
                val intent = Intent(RingtoneManager.ACTION_RINGTONE_PICKER)
                intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE, RingtoneManager.TYPE_RINGTONE)
                intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TITLE, "Select Tone")
                intent.putExtra(RingtoneManager.EXTRA_RINGTONE_EXISTING_URI, SharedPreferences().getRingtoneUri(requireActivity()))
                intent.putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_SILENT, false)
                intent.putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_DEFAULT, true)
                startActivityForResult(intent, 999)
                true
            }
            getString(R.string.clear_voice_history_pref_name) -> {
                val builder = AlertDialog.Builder(context)
                builder.setCancelable(true)
                builder.setTitle("Warning")
                builder.setMessage("Are you sure you want to clear your voice call history?")

                builder.setPositiveButton("Yes") { dialog, which ->
                    lifecycleScope.launch {
                        mainViewModel.clearVoiceCallHistory()
                    }
                }
                builder.show()

                true
            }
            else -> {
                super.onPreferenceTreeClick(preference)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 999 && resultCode == RESULT_OK) {
            val uri = data?.getParcelableExtra<Uri>(RingtoneManager.EXTRA_RINGTONE_PICKED_URI)
            context?.let { SharedPreferences().setRingtone(it, uri.toString()) }
            val ringtonePref = findPreference<Preference>("Ringtone")
            val currentRingtone = RingtoneManager.getRingtone(context, SharedPreferences().getRingtoneUri(requireActivity()))
            ringtonePref?.summary = currentRingtone.getTitle(context)
        }
    }
}
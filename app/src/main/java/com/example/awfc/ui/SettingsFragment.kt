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
import android.provider.ContactsContract
import androidx.annotation.RequiresApi
import androidx.preference.PreferenceManager
import com.example.awfc.utils.SharedPreferences
import kotlinx.coroutines.runInterruptible


class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preference, rootKey)
        val ringtonePref = findPreference<Preference>("Ringtone")
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
package com.example.awfc.ui

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.text.Html
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toolbar
import androidx.annotation.RequiresApi
import androidx.annotation.VisibleForTesting
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.awfc.R
import com.example.awfc.ui.voiceDeviceUI.IncomingVoiceCallAndroidNougat
import com.example.awfc.utils.SharedPreferences
import com.google.android.material.button.MaterialButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VoiceCallHomeFragment : Fragment() {


    private lateinit var mView: View
    private val sharedPreferences = SharedPreferences()
    private val PICK_FILE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val toolbar = activity?.findViewById<Toolbar>(R.id.toolbar)
        toolbar?.menu?.clear()
        mView =  inflater.inflate(R.layout.fragment_voice_call_home, container, false)

        val scheduleCallBtn = mView.findViewById<MaterialButton>(R.id.scheduleCallBtn)
        val setTimeBtn = mView.findViewById<MaterialButton>(R.id.setTimeBtn)
        val setDeviceBtn = mView.findViewById<MaterialButton>(R.id.deviceDesignBtn)
        val addVoiceBtn = mView.findViewById<MaterialButton>(R.id.addVoiceBtn)

        getDevicePreference(setDeviceBtn)


        refreshHomePage(setTimeBtn)

        val builder = AlertDialog.Builder(this.context)
        val durations : Array<String> = arrayOf("Now","10 seconds","30 seconds","1 minute","5 minutes","10 minutes")
        addVoiceBtn.text = Html.fromHtml("<big>" + "<font size=10' face='arial' color='#ffffff'>" + "Set Audio" + "</font>" + "</big>" +  "<br />" +
                "<small>" +
                "<font size=6' face='arial' color='#d9d9d9'>" +
                "None" +
                "</font>"
                + "</small>" + "<br />");

        addVoiceBtn.setOnClickListener {
            if (ContextCompat.checkSelfPermission(requireActivity(),
                    Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(requireActivity(),
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    23)
            } else {
                val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
                intent.addCategory(Intent.CATEGORY_OPENABLE)
                intent.type = "audio/*"
                startActivityForResult(Intent.createChooser(intent, "Select Audio"), 1)
            }

        }

        setDeviceBtn.setOnClickListener {
            val intent = Intent(this.context, DeviceVoiceCallActivity::class.java)
            startActivity(intent)
        }

        scheduleCallBtn.setOnClickListener {
            if(!Settings.canDrawOverlays(this.context)) {
                // dialog box with ok btn
                builder.setTitle("Permission Request")
                builder.setMessage("Please allow this app to display over other apps, then restart the app")
                builder.setPositiveButton(
                    "OK"
                ) { dialogInterface, i ->
                    val myIntent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION)
                    startActivity(myIntent)
                    dialogInterface.cancel()
                }
                builder.create()
                builder.show()
            } else {

                val intent = Intent(this.context, IncomingVoiceCallAndroidNougat::class.java)
                startActivity(intent)
            }
        }
        setTimeBtn.setOnClickListener {

            builder.setTitle("Receive call in ...")
            builder.setItems(durations) { _, i ->
                when {
                    "Now" == durations[i] -> {
                        this.context?.let { it1 -> sharedPreferences.setDurationVoiceCallPreference(it1, 0) }
                        refreshHomePage(setTimeBtn)
                    }
                    "10 seconds" == durations[i] -> {
                        this.context?.let { it1 -> sharedPreferences.setDurationVoiceCallPreference(it1, 10000) }
                        refreshHomePage(setTimeBtn)
                    }
                    "30 seconds" == durations[i] -> {
                        this.context?.let { it1 -> sharedPreferences.setDurationVoiceCallPreference(it1, 30000) }
                        refreshHomePage(setTimeBtn)
                    }
                    "1 minute" == durations[i] -> {
                        this.context?.let { it1 -> sharedPreferences.setDurationVoiceCallPreference(it1, 60000) }
                        refreshHomePage(setTimeBtn)
                    }
                    "5 minutes" == durations[i] -> {
                        this.context?.let { it1 -> sharedPreferences.setDurationVoiceCallPreference(it1, 300000) }
                        refreshHomePage(setTimeBtn)
                    }
                    "10 minutes" == durations[i] -> {
                        this.context?.let { it1 -> sharedPreferences.setDurationVoiceCallPreference(it1, 600000) }
                        refreshHomePage(setTimeBtn)
                    }
                }
            }
            builder.show()

        }

        return mView
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == PICK_FILE && resultCode == RESULT_OK)
        {
            if(data != null)
            {

                val addVoiceBtn = mView.findViewById<MaterialButton>(R.id.addVoiceBtn)
                val uri = data.data.toString()
                context?.let { SharedPreferences().saveAudioUri(it, uri) }
                setAudioBtnText(addVoiceBtn)
            }
        }
    }

    fun setAudioBtnText(button1: Button)
    {
        if(getNameFromUri(this.context?.let { SharedPreferences().getAudioUri(it) }) != null)
        {
            val name = getNameFromUri(this.context?.let { SharedPreferences().getAudioUri(it) })
            button1.text = Html.fromHtml("<big>" + "<font size=10' face='arial' color='#ffffff'>" + "Set Audio" + "</font>" + "</big>" +  "<br />" +
                    "<small>" +
                    "<font size=6' face='arial' color='#d9d9d9'>" +
                    name +
                    "</font>"
                    + "</small>" + "<br />");
        } else {
            button1.text = Html.fromHtml("<big>" + "<font size=10' face='arial' color='#ffffff'>" + "Set Audio" + "</font>" + "</big>" +  "<br />" +
                    "<small>" +
                    "<font size=6' face='arial' color='#d9d9d9'>" +
                    "None" +
                    "</font>"
                    + "</small>" + "<br />");
        }
    }

    @SuppressLint("Range")
    fun getNameFromUri(uri: Uri?): String? {
        var fileName = ""
        var cursor: Cursor? = null
        cursor = uri?.let {
            activity?.contentResolver?.query(
                it, arrayOf(
                    MediaStore.Images.ImageColumns.DISPLAY_NAME
                ), null, null, null
            )
        }
        if (cursor != null && cursor.moveToFirst()) {
            fileName =
                cursor.getString(cursor.getColumnIndex(MediaStore.Images.ImageColumns.DISPLAY_NAME))
        }
        if (cursor != null) {
            cursor.close()
        }
        return fileName
    }

    override fun onResume() {
        super.onResume()

        val setDeviceBtn = mView.findViewById<MaterialButton>(R.id.deviceDesignBtn)
        getDevicePreference(setDeviceBtn)
    }


    private fun refreshHomePage(button1: Button)
    {
        button1.text = Html.fromHtml("<big>" + "Set Timer" + "</big>" +  "<br />" +
                "<small>" +
                "<font size=6' face='arial' color='#d9d9d9'>" +
                this.context?.let { sharedPreferences.getDurationVoiceCallPreferenceString(it) } +
                "</font>"
                + "</small>" + "<br />");
    }

    private fun getDevicePreference(button1: Button)
    {
        when(this.context?.let { sharedPreferences.getDeviceVoiceCallPreference(it) }) {
            1 -> {
                button1.text = Html.fromHtml("<big>" + "<font size=10' face='arial' color='#ffffff'>" + "Set Device" + "</font>" + "</big>" +  "<br />" +
                        "<small>" +
                        "<font size=6' face='arial' color='#d9d9d9'>" +
                        "Android Nougat 7.0" +
                        "</font>"
                        + "</small>" + "<br />");
            }
            2 -> {
                button1.text = Html.fromHtml("<big>" + "<font size=10' face='arial' color='#ffffff'>" + "Set Device" + "</font>" + "</big>" +  "<br />" +
                        "<small>" +
                        "<font size=6' face='arial' color='#d9d9d9'>" +
                        "IPhone ios" +
                        "</font>"
                        + "</small>" + "<br />");
            }
            3 -> {
                button1.text = Html.fromHtml("<big>" + "<font size=10' face='arial' color='#ffffff'>" + "Set Device" + "</font>" + "</big>" +  "<br />" +
                        "<small>" +
                        "<font size=6' face='arial' color='#d9d9d9'>" +
                        "IPhone ios 12" +
                        "</font>"
                        + "</small>" + "<br />");
            }
            4 -> {
                button1.text = Html.fromHtml("<big>" + "<font size=10' face='arial' color='#ffffff'>" + "Set Device" + "</font>" + "</big>" +  "<br />" +
                        "<small>" +
                        "<font size=6' face='arial' color='#d9d9d9'>" +
                        "Samsung A10" +
                        "</font>"
                        + "</small>" + "<br />");
            }
            5 -> {
                button1.text = Html.fromHtml("<big>" + "<font size=10' face='arial' color='#ffffff'>" + "Set Device" + "</font>" + "</big>" +  "<br />" +
                        "<small>" +
                        "<font size=6' face='arial' color='#d9d9d9'>" +
                        "Samsung S7" +
                        "</font>"
                        + "</small>" + "<br />");
            }
            6 -> {
                button1.text = Html.fromHtml("<big>" + "<font size=8' face='arial' color='#ffffff'>" + "Set Device" + "</font>" + "</big>" +  "<br />" +
                        "<small>" +
                        "<font size=6' face='arial' color='#d9d9d9'>" +
                        "Oppo" +
                        "</font>"
                        + "</small>" + "<br />");
            }
        }

    }
}
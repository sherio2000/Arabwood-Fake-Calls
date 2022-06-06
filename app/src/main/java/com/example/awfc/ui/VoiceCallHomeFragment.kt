package com.example.awfc.ui

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import android.os.Bundle
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
import com.example.awfc.R
import com.example.awfc.ui.voiceDeviceUI.IncomingVoiceCallAndroidNougat
import com.example.awfc.utils.SharedPreferences
import com.google.android.material.button.MaterialButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VoiceCallHomeFragment : Fragment() {


    private lateinit var mView: View
    private val sharedPreferences = SharedPreferences()

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

        getDevicePreference(setDeviceBtn)


        refreshHomePage(setTimeBtn)

        val builder = AlertDialog.Builder(this.context)
        val durations : Array<String> = arrayOf("Now","10 seconds","30 seconds","1 minute","5 minutes","10 minutes")

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

    override fun onResume() {
        super.onResume()

        val setDeviceBtn = mView.findViewById<MaterialButton>(R.id.deviceDesignBtn)
        getDevicePreference(setDeviceBtn)
    }


    fun refreshHomePage(button1: Button)
    {
        button1.text = Html.fromHtml("<big>" + "Set Timer" + "</big>" +  "<br />" +
                "<small>" +
                "<font size=6' face='arial' color='#d9d9d9'>" +
                this.context?.let { sharedPreferences.getDurationVoiceCallPreferenceString(it) } +
                "</font>"
                + "</small>" + "<br />");
    }

    fun getDevicePreference(button1: Button)
    {
        val index = this.context?.let { sharedPreferences.getDeviceVoiceCallPreference(it) }
        when(index) {
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
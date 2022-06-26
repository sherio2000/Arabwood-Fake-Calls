package com.example.awfc.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioButton
import androidx.appcompat.widget.Toolbar
import com.example.awfc.R
import com.example.awfc.ui.voiceDeviceUI.*
import com.example.awfc.utils.SharedPreferences

class DeviceVoiceCallActivity : AppCompatActivity() {
    private val sharedPreferences = SharedPreferences()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_device_voice_call)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.setTitle("Details")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val previewBtn = findViewById<Button>(R.id.previewBtn)
        previewBtn.setOnClickListener {
            initiatePreview(getDefaultDeviceVoiceCallPreference())
        }

        checkRadioButtonPreference(getDefaultDeviceVoiceCallPreference())

        val radioBtn1 = findViewById<RadioButton>(R.id.radioBtn1)
        val radioBtn2 = findViewById<RadioButton>(R.id.radioBtn2)
        val radioBtn3 = findViewById<RadioButton>(R.id.radioBtn3)
        val radioBtn4 = findViewById<RadioButton>(R.id.radioBtn4)
        val radioBtn5 = findViewById<RadioButton>(R.id.radioBtn5)
        val radioBtn6 = findViewById<RadioButton>(R.id.radioBtn6)


        val imageView1 = findViewById<ImageView>(R.id.imageView1)
        val imageView2 = findViewById<ImageView>(R.id.imageView2)
        val imageView3 = findViewById<ImageView>(R.id.imageView3)
        val imageView4 = findViewById<ImageView>(R.id.imageView4)
        val imageView5 = findViewById<ImageView>(R.id.imageView5)
        val imageView6 = findViewById<ImageView>(R.id.imageView6)


        setRadioButtonPreference(radioBtn1, 1)
        setRadioButtonPreference(radioBtn2, 2)
        setRadioButtonPreference(radioBtn3, 3)
        setRadioButtonPreference(radioBtn4, 4)
        setRadioButtonPreference(radioBtn5, 5)
        setRadioButtonPreference(radioBtn6, 6)

        setImagePreference(imageView1, 1)
        setImagePreference(imageView2, 2)
        setImagePreference(imageView3, 3)
        setImagePreference(imageView4, 4)
        setImagePreference(imageView5, 5)
        setImagePreference(imageView6, 6)
    }


    fun checkRadioButtonPreference(index: Int)
    {
        val radioBtn1 = findViewById<RadioButton>(R.id.radioBtn1)
        val radioBtn2 = findViewById<RadioButton>(R.id.radioBtn2)
        val radioBtn3 = findViewById<RadioButton>(R.id.radioBtn3)
        val radioBtn4 = findViewById<RadioButton>(R.id.radioBtn4)
        val radioBtn5 = findViewById<RadioButton>(R.id.radioBtn5)
        val radioBtn6 = findViewById<RadioButton>(R.id.radioBtn6)

        radioBtn1.isChecked = false
        radioBtn2.isChecked = false
        radioBtn3.isChecked = false
        radioBtn4.isChecked = false
        radioBtn5.isChecked = false
        radioBtn6.isChecked = false

        when (index) {
            1 -> {
                radioBtn1.isChecked = true
            }
            2 -> {
                radioBtn2.isChecked = true
            }
            3 -> {
                radioBtn3.isChecked = true
            }
            4 -> {
                radioBtn4.isChecked = true
            }
            5 -> {
                radioBtn5.isChecked = true
            }
            6 -> {
                radioBtn6.isChecked = true
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


    private fun initiatePreview(index: Int)
    {
        when(index) {
            1 -> {
                val intent = Intent(this, IncomingVoiceCallAndroidNougat::class.java)
                startActivity(intent)
            }
            2 -> {
                val intent = Intent(this, IncomingVoiceCallIos2::class.java)
                startActivity(intent)
            }
            3 -> {
                val intent = Intent(this, IncomingVoiceCallIos12::class.java)
                startActivity(intent)
            }
            4 -> {
                val intent = Intent(this, IncomingVoiceCallSamsungA10::class.java)
                startActivity(intent)
            }
            5 -> {
                val intent = Intent(this, IncomingVoiceCallSamsungS7::class.java)
                startActivity(intent)
            }
            6 -> {
                val intent = Intent(this, IncomingVoiceCallOppo::class.java)
                startActivity(intent)
            }
        }
    }

    private fun setRadioButtonPreference(button: RadioButton, index: Int)
    {
        button.setOnClickListener {
            sharedPreferences.setDeviceVoiceCallPreference(this, index)
            uncheckAllRadioButtons(index)
        }
    }

    private fun setImagePreference(imageView: ImageView, index: Int)
    {
        imageView.setOnClickListener {
            sharedPreferences.setDeviceVoiceCallPreference(this, index)
            uncheckAllRadioButtons(index)
        }
    }

    private fun uncheckAllRadioButtons(index: Int) {
        val radioBtn1 = findViewById<RadioButton>(R.id.radioBtn1)
        val radioBtn2 = findViewById<RadioButton>(R.id.radioBtn2)
        val radioBtn3 = findViewById<RadioButton>(R.id.radioBtn3)
        val radioBtn4 = findViewById<RadioButton>(R.id.radioBtn4)
        val radioBtn5 = findViewById<RadioButton>(R.id.radioBtn5)
        val radioBtn6 = findViewById<RadioButton>(R.id.radioBtn6)

        when (index) {
            1 -> {
                radioBtn1.isChecked = true
                radioBtn2.isChecked = false
                radioBtn3.isChecked = false
                radioBtn4.isChecked = false
                radioBtn5.isChecked = false
                radioBtn6.isChecked = false
            }
            2 -> {
                radioBtn2.isChecked = true
                radioBtn1.isChecked = false
                radioBtn3.isChecked = false
                radioBtn4.isChecked = false
                radioBtn5.isChecked = false
                radioBtn6.isChecked = false
            }
            3 -> {
                radioBtn3.isChecked = true
                radioBtn1.isChecked = false
                radioBtn2.isChecked = false
                radioBtn4.isChecked = false
                radioBtn5.isChecked = false
                radioBtn6.isChecked = false
            }
            4 -> {
                radioBtn4.isChecked = true
                radioBtn1.isChecked = false
                radioBtn2.isChecked = false
                radioBtn3.isChecked = false
                radioBtn5.isChecked = false
                radioBtn6.isChecked = false
            }
            5 -> {
                radioBtn1.isChecked = false
                radioBtn2.isChecked = false
                radioBtn3.isChecked = false
                radioBtn4.isChecked = false
                radioBtn5.isChecked = true
                radioBtn6.isChecked = false
            }
            6 -> {
                radioBtn1.isChecked = false
                radioBtn2.isChecked = false
                radioBtn3.isChecked = false
                radioBtn4.isChecked = false
                radioBtn5.isChecked = false
                radioBtn6.isChecked = true

            }
        }
    }
    private fun getDefaultDeviceVoiceCallPreference() : Int
    {
        return sharedPreferences.getDeviceVoiceCallPreference(this)
    }
}
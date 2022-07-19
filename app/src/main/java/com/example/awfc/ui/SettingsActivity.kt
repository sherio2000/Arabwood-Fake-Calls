package com.example.awfc.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.awfc.R
import android.view.View
import androidx.appcompat.widget.Toolbar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Settings"

        // below line is used to check if
        // frame layout is empty or not.

        // below line is used to check if
        // frame layout is empty or not.
        if (findViewById<View?>(R.id.idFrameLayout) != null) {
            if (savedInstanceState != null) {
                return
            }
            // below line is to inflate our fragment.
            supportFragmentManager.beginTransaction().add(R.id.idFrameLayout, SettingsFragment()).commit()
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

}
package com.example.awfc

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.toolbar))

        val navController = findNavController(R.id.fragmentContainerView)
        val config = AppBarConfiguration(navController.graph)

        findViewById<Toolbar>(R.id.toolbar).setupWithNavController(navController, config)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.setBackgroundColor(Color.rgb(32, 4, 209))
        toolbar.setTitleTextColor(Color.WHITE)
        toolbar.setTitle(R.string.app_name)

    }

}
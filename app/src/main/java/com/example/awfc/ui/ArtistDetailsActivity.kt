package com.example.awfc.ui

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.navigation.navArgs
import coil.load
import com.example.awfc.R
import com.example.awfc.data.Artist
import com.squareup.picasso.Picasso

class ArtistDetailsActivity : AppCompatActivity() {
    val args: ArtistDetailsActivityArgs by navArgs()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artist_details)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.setTitle("Details")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val result: Artist = args.result
        val image = findViewById<ImageView>(R.id.artistDetailsIV)
        val artistTitle = findViewById<TextView>(R.id.artistDetailsNameTV)
        val artistDesc = findViewById<TextView>(R.id.artistDetailsDescTV)
        Log.d("Picasso Image URL", result.image)
        Picasso.get().load(result.image).into(image)
        Picasso.get().isLoggingEnabled = true
        artistTitle.text = result.name
        artistDesc.text = result.description

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home)
        {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
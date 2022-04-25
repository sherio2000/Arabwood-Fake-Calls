package com.example.awfc.ui

import android.Manifest
import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.navigation.navArgs
import coil.load
import com.example.awfc.R
import com.example.awfc.data.Artist
import com.squareup.picasso.Picasso
import android.graphics.Bitmap

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface

import android.widget.Toast

import androidx.core.app.ActivityCompat.startActivityForResult

import android.content.pm.PackageManager
import android.os.Build
import android.os.Handler
import android.provider.MediaStore
import android.service.autofill.OnClickAction

import androidx.annotation.NonNull
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import java.util.concurrent.Delayed
import kotlin.concurrent.schedule

@AndroidEntryPoint
class ArtistDetailsActivity : AppCompatActivity() {

    private val CAMERA_REQUEST = 1888
    private val imageView: ImageView? = null
    private val MY_CAMERA_PERMISSION_CODE = 100


    companion object {
        private const val CAMERA_PERMISSION_CODE = 100
        private const val STORAGE_PERMISSION_CODE = 101
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artist_details)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.setTitle("Details")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

//        val cardView = findViewById<CardView>(R.id.artistDetailsNameCV)
//        cardView.elevation = 20F
//        cardView.radius = 10F
//        cardView.cardElevation = 30F
//        cardView.preventCornerOverlap = true

        val image = findViewById<ImageView>(R.id.artistDetailsIV)
        val artistTitle = findViewById<TextView>(R.id.artistDetailsNameTV)
        val artistDesc = findViewById<TextView>(R.id.artistDetailsDescTV)
//        Log.d("Picasso Image URL", result.image)
        Picasso.get().load(intent.getStringExtra("artistImage")).into(image)
        Picasso.get().isLoggingEnabled = true
        artistTitle.text = intent.getStringExtra("artistName")
        artistDesc.text = intent.getStringExtra("artistDesc")
        val artist = Artist(
            0,
            intent.getStringExtra("artistName").toString(),
            intent.getStringExtra("arabicName").toString(),
            intent.getStringExtra("arabicDesc").toString(),
            intent.getStringExtra("artistDesc").toString(),
            intent.getStringExtra("artistImage").toString(),
            intent.getStringExtra("artistVideo1").toString(),
            intent.getStringExtra("artistVideo2").toString(),
            intent.getStringExtra("artistVideo3").toString()
        )
        val durations : Array<String> = arrayOf("5 seconds","10 seconds","30 seconds","1 minute","2 minutes")
        val builder = AlertDialog.Builder(this)

        findViewById<Button>(R.id.incomingCallBtn).setOnClickListener {

            if (ContextCompat.checkSelfPermission(this@ArtistDetailsActivity, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {
                // Requesting the permission
                ActivityCompat.requestPermissions(this@ArtistDetailsActivity, arrayOf(Manifest.permission.CAMERA), CAMERA_PERMISSION_CODE)
            } else {
                builder.setTitle("Receive call in ...")
                builder.setItems(durations) { _, i ->
                    if ("5 seconds" == durations[i]) {
                        Log.d("ITem Cliked", durations[i])
                        this.finish()
                        initiateIncomingCall(it, 5000, artist)
                    } else if("10 seconds" == durations[i])
                    {
                        this.finish()
                        initiateIncomingCall(it, 10000, artist)
                    } else if("30 seconds" == durations[i])
                    {
                        this.finish()
                        initiateIncomingCall(it, 30000, artist)
                    } else  if("1 minute" == durations[i])
                    {
                        this.finish()
                        initiateIncomingCall(it, 60000, artist)

                    } else if("2 minutes" == durations[i])
                    {
                        this.finish()
                        initiateIncomingCall(it, 120000, artist)
                    }
                }
                builder.show()

            }
        }

    }

    private fun initiateIncomingCall(it: View, duration: Long, artist: Artist){
        Timer().schedule(duration)
        {
            val intent = Intent(it.context, IncomingRingingActivity::class.java)
            intent.putExtra("artist", artist)
            it.context.startActivity(intent)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>,
                                            grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this@ArtistDetailsActivity, "Camera Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this@ArtistDetailsActivity, "Camera Permission Denied", Toast.LENGTH_SHORT).show()
            }
        } else if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this@ArtistDetailsActivity, "Storage Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this@ArtistDetailsActivity, "Storage Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun checkPermission(permission: String, requestCode: Int) {
        if (ContextCompat.checkSelfPermission(this@ArtistDetailsActivity, permission) == PackageManager.PERMISSION_DENIED) {
            // Requesting the permission
            ActivityCompat.requestPermissions(this@ArtistDetailsActivity, arrayOf(permission), requestCode)
        } else {
            Toast.makeText(this@ArtistDetailsActivity, "Permission already granted", Toast.LENGTH_SHORT).show()
        }
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home)
        {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
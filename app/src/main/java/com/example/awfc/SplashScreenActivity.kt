package com.example.awfc

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val videoIV: ImageView = findViewById(R.id.video_iv)
        videoIV.alpha = 0f
        videoIV.animate().setDuration(3000).alpha(1f).withEndAction {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }

    }
}
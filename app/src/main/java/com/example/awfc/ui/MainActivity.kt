package com.example.awfc.ui

import android.Manifest
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TableLayout
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.awfc.R
import com.example.awfc.adapters.ViewPagerAdapter
import com.example.awfc.databinding.ActivityMainBinding
import com.example.awfc.utils.PermissionRequestUtil
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import pub.devrel.easypermissions.EasyPermissions

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.toolbar))
        val tabLayout = findViewById<TabLayout>(R.id.tab_layout)
        val viewPager2  = findViewById<ViewPager2>(R.id.view_pager_2)

        val adapter  = ViewPagerAdapter(supportFragmentManager, lifecycle)
        viewPager2.adapter = adapter
        TabLayoutMediator(tabLayout, viewPager2){tab, postion ->
            when(postion) {
                0 -> {
                    tab.text="Artists"
                }
                1 -> {
                    tab.text = "Voice Calls"
                }
            }
        }.attach()

        requestPermissions()

        //val navController = findNavController(R.id.fragmentContainerView)
//        val config = AppBarConfiguration(navController.graph)
//
//
//        findViewById<Toolbar>(R.id.toolbar).setupWithNavController(navController, config)
        val toolbar = findViewById<Toolbar>(R.id.mainToolbar)
        toolbar.setBackgroundColor(Color.rgb(32, 4, 209))
        toolbar.setTitleTextColor(Color.WHITE)
        toolbar.setTitle(R.string.app_name)


    }


    private fun requestPermissions()
    {
        if (PermissionRequestUtil.hasCameraPermissions(this)) {
            return
        } else {
            EasyPermissions.requestPermissions(
                this,
                "Please accept camera permissions to use this app.",
                0,
                Manifest.permission.CAMERA
            )
        }
    }


}
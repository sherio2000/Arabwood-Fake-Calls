package com.example.awfc.ui

import android.Manifest
import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.widget.SearchView
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.awfc.R
import com.example.awfc.adapters.ArtistsAdapter
import com.example.awfc.adapters.ViewPagerAdapter
import com.example.awfc.data.Artist
import com.example.awfc.databinding.ActivityMainBinding
import com.example.awfc.utils.PermissionRequestUtil
import com.example.awfc.viewmodels.MainViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import pub.devrel.easypermissions.EasyPermissions

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), ArtistsAdapter.OnArtistListener {

    private lateinit var binding: ActivityMainBinding

    private val mAdapter by lazy { ArtistsAdapter(this) }

    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var viewPager2:ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(findViewById(R.id.toolbar))
        val tabLayout = findViewById<TabLayout>(R.id.tab_layout)
        viewPager2 =  findViewById(R.id.view_pager_2)

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

        viewPager2.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(), SearchView.OnQueryTextListener {
            @SuppressLint("ResourceType")
            override fun onPageSelected(position: Int) {
                if(position == 0)
                {

                    val toolbar = findViewById<Toolbar>(R.id.mainToolbar)
                    toolbar.inflateMenu(R.menu.search_menu)
                    val search = toolbar.menu.findItem(R.id.menu_search)
                    val searchView = search?.actionView as? SearchView
                    searchView?.isSubmitButtonEnabled = true
                    searchView?.setOnQueryTextListener(this)

                } else {
                    val toolbar = findViewById<Toolbar>(R.id.mainToolbar)
                    toolbar.menu.clear()
                }
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onQueryTextSubmit(query: String?): Boolean {
                if(query != null){
                    searchArtist(query)
                    mAdapter.notifyDataSetChanged()
                }
                return true
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onQueryTextChange(newText: String?): Boolean {
                if(newText != null){
                    searchArtist(newText)
                    mAdapter.notifyDataSetChanged()
                }
                return true
            }
        })

        requestPermissions()

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

    @SuppressLint("NotifyDataSetChanged")
    private fun searchArtist(query: String)
    {
        val searchQuery = "%$query%"
        lifecycle.coroutineScope.launch {
            mainViewModel.searchArtist(searchQuery).collect { it ->
                mainViewModel.artistsResponse.value = it
                Log.i(TAG, "search Artists initiated")
            }
        }
    }



    override fun onArtistClick(artist: Artist, position: Int) {
        TODO("Not yet implemented")
    }


}
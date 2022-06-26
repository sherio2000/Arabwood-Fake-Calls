package com.example.awfc.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.awfc.R
import com.example.awfc.adapters.VoiceCallHistoryRowAdapter
import com.example.awfc.data.Artist
import com.example.awfc.data.VoiceCallerHistory
import com.example.awfc.utils.ConnectionLiveData
import com.example.awfc.utils.SharedPreferences
import com.example.awfc.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class VoiceCallHistory : AppCompatActivity(), VoiceCallHistoryRowAdapter.OnVoiceCallHistoryRowListener {

    private val mainViewModel: MainViewModel by viewModels()
    private val mAdapter by lazy { VoiceCallHistoryRowAdapter(this) }
    private lateinit var voiceCallHistoryList: List<VoiceCallerHistory>

    @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_voice_call_history)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.title = "Voice Call History"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        lifecycle.coroutineScope.launch {
            mainViewModel.getVoiceCallHistory().collect {
                mainViewModel.voiceCallerHistoryResponse.value = it
                voiceCallHistoryList = it
                mAdapter.setData(it)
            }
        }
        val rv = findViewById<RecyclerView>(R.id.voiceCallHistoryRV)
        rv.adapter = mAdapter
        rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mainViewModel.voiceCallerHistoryResponse.observe(this, Observer {
            mAdapter.setData(it)
        })


    }

    @SuppressLint("CutPasteId")
    override fun onCreateView(
        parent: View?,
        name: String,
        context: Context,
        attrs: AttributeSet
    ): View? {


        return super.onCreateView(parent, name, context, attrs)
    }


    override fun onVoiceCallRecordClick(voiceCallerHistory: VoiceCallerHistory, position: Int) {
        val orderedList = voiceCallHistoryList.reversed()
        val intent =  Intent(this,MainActivity::class.java)

        // you pass the position you want the viewpager to show in the extra,
        // please don't forget to define and initialize the position variable
        // properly
        intent.putExtra("viewpager_position", 1)
        SharedPreferences().setCallerName(this, orderedList[position].callerName)
        SharedPreferences().setCallerNumber(this, orderedList[position].callerNumber)
        SharedPreferences().setDurationVoiceCallPreference(this, orderedList[position].duration)
        SharedPreferences().setDeviceVoiceCallPreference(this, orderedList[position].device)
        startActivity(intent);
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
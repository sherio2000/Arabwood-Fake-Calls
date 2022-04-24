package com.example.awfc.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import com.example.awfc.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VoiceCallHomeFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val toolbar = activity?.findViewById<Toolbar>(R.id.toolbar)
        toolbar?.menu?.clear()
        return inflater.inflate(R.layout.fragment_voice_call_home, container, false)
    }


}
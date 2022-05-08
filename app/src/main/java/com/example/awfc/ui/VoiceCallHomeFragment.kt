package com.example.awfc.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toolbar
import coil.load
import com.example.awfc.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VoiceCallHomeFragment : Fragment() {


    private lateinit var mView: View

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
        mView =  inflater.inflate(R.layout.fragment_voice_call_home, container, false)

        return mView
    }


}
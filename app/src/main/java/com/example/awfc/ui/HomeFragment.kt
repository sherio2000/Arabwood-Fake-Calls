package com.example.awfc.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import com.example.awfc.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_home, container, false)
        view.findViewById<Button>(R.id.videoCallBtn).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.nav_to_videoCallHomeFragment)
        }
        view.findViewById<Button>(R.id.voiceCallBtn).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.nav_to_voiceCallHomeFragment)
        }
        return view
    }

}
package com.example.awfc.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.awfc.ui.VideoCallHomeFragment
import com.example.awfc.ui.VoiceCallHomeFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0->
            {
                VideoCallHomeFragment()
            }
            1->
            {
                VoiceCallHomeFragment()
            }
            else -> {
                Fragment()
            }
        }
    }
}
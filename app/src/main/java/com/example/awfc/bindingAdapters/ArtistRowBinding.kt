package com.example.awfc.bindingAdapters

import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import coil.load
import com.example.awfc.data.Artist
import com.example.awfc.ui.VideoCallHomeFragment
import com.example.awfc.ui.VideoCallHomeFragmentDirections
import com.squareup.picasso.Picasso

class ArtistRowBinding {
    companion object {

        @BindingAdapter("onArtistClickListener")
        @JvmStatic
        fun onArtistClickListener(artistRowLayout: ConstraintLayout, result: Artist) {
            artistRowLayout.setOnClickListener {
                try {
                    val action = VideoCallHomeFragmentDirections.actionVideoCallHomeFragmentToArtistDetailsActivity(result)
                    artistRowLayout.findNavController().navigate(action)
                } catch (e: Exception)
                {
                    Log.d("OnArtistClickListener", e.toString())
                }
            }
        }

        @BindingAdapter("loadImageFromUrl")
        @JvmStatic
        fun loadImageFromUrl(imageView: ImageView, imageUrl: String)
        {
            Picasso.get().load(imageUrl).into(imageView)
        }

        @BindingAdapter( "setName")
        @JvmStatic
        fun setArtistName(textView: TextView, name: String)
        {
            textView.text = name
        }
    }
}
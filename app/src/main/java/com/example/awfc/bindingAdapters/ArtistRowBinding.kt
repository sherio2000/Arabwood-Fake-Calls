package com.example.awfc.bindingAdapters

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.load

class ArtistRowBinding {
    companion object {


        @BindingAdapter("loadImageFromUrl")
        @JvmStatic
        fun loadImageFromUrl(imageView: ImageView, imageUrl: String)
        {
            imageView.load(imageUrl) {
                crossfade(true)
                crossfade(600)
            }
        }

        @BindingAdapter( "setName")
        @JvmStatic
        fun setArtistName(textView: TextView, name: String)
        {
            textView.text = name
        }
    }
}
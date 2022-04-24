package com.example.awfc.bindingAdapters

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

class ArtistRowBinding {
    companion object {


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
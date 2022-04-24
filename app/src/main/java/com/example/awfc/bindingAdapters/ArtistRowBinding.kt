package com.example.awfc.bindingAdapters

import android.widget.ImageView
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

    }
}
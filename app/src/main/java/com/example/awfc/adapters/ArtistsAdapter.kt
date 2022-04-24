package com.example.awfc.adapters

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Intent
import android.service.autofill.OnClickAction
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.awfc.data.Artist
import com.example.awfc.databinding.ArtistRowLayoutBinding
import com.example.awfc.utils.ArtistsDiffUtil

class ArtistsAdapter(var artistListener: OnArtistListener) : RecyclerView.Adapter<ArtistsAdapter.MyViewHolder>() {

    private var artists = emptyList<Artist>()

    class MyViewHolder(private val binding: ArtistRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun init(artist: Artist, action: OnArtistListener)
        {
            itemView.setOnClickListener {
                action.onArtistClick(artist, adapterPosition)
            }
        }

        fun bind(modelClass: Artist) {
            binding.result = modelClass
            binding.executePendingBindings()
        }


        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ArtistRowLayoutBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        Log.i(TAG, "OnCreateViewHolder initiated")
        return MyViewHolder(
            ArtistRowLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentResult = artists[position]
        holder.bind(currentResult)
        holder.init(currentResult, artistListener)
        Log.i(TAG, "OnBindViewHOlder initiated")
    }

    override fun getItemCount(): Int {
        return artists.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newData: List<Artist>) {
        val artistsDiffUtil = ArtistsDiffUtil(artists, newData)
        val diffUtilResult = DiffUtil.calculateDiff(artistsDiffUtil)
        artists = emptyList()
        artists = newData
        diffUtilResult.dispatchUpdatesTo(this)
        this.notifyDataSetChanged()
        //this.notifyDataSetChanged()
    }
    interface OnArtistListener {
        fun onArtistClick(artist:Artist, position: Int)
    }
}
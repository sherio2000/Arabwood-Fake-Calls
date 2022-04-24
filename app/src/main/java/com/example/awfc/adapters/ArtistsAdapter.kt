package com.example.awfc.adapters

import android.view.LayoutInflater
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
    }

    override fun getItemCount(): Int {
        return artists.size
    }

    fun setData(newData: List<Artist>) {
        val artistsDiffUtil = ArtistsDiffUtil(artists, newData)
        val diffUtilResult = DiffUtil.calculateDiff(artistsDiffUtil)
        artists = emptyList()
        artists = newData
        diffUtilResult.dispatchUpdatesTo(this)
    }
    interface OnArtistListener {
        fun onArtistClick(artist:Artist, position: Int)
    }
}
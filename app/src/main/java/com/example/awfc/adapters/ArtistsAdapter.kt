package com.example.awfc.adapters

import android.annotation.SuppressLint
import android.content.Intent
import android.service.autofill.OnClickAction
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.awfc.data.Artist
import com.example.awfc.databinding.ArtistRowLayoutBinding
import com.example.awfc.ui.ArtistDetailsActivity
import com.example.awfc.ui.IncomingRingingActivity
import kotlinx.coroutines.flow.*

class ArtistsAdapter(var artistListener: OnArtistListener) : RecyclerView.Adapter<ArtistsAdapter.MyViewHolder>() {

    private var artists = emptyList<Artist>()

    class MyViewHolder(private val binding: ArtistRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

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

        override fun onClick(p0: View?) {
            TODO("Not yet implemented")
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentResult = artists[position]
        holder.bind(currentResult)
        holder.init(currentResult, artistListener)

    }

    override fun getItemCount(): Int {
        return artists.size
    }

    suspend fun <T> Flow<List<T>>.flattenToList() =
        flatMapConcat { it.asFlow() }.toList()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newData: List<Artist>) {
        artists = newData
        notifyDataSetChanged()
    }
    interface OnArtistListener {
        fun onArtistClick(artist:Artist, position: Int)
    }


}
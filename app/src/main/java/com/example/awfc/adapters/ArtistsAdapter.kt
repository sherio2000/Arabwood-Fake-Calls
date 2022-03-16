package com.example.awfc.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.awfc.data.Artist
import com.example.awfc.databinding.ArtistRowLayoutBinding
import kotlinx.coroutines.flow.*

class ArtistsAdapter : RecyclerView.Adapter<ArtistsAdapter.MyViewHolder>() {

    private var artists = emptyList<Artist>()

    class MyViewHolder(private val binding: ArtistRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

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
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentResult = artists[position]
        holder.bind(currentResult)
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
}
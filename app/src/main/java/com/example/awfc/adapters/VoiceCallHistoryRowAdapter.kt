package com.example.awfc.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.awfc.data.VoiceCallerHistory
import com.example.awfc.databinding.VoiceCallHistoryItemBinding
import com.example.awfc.utils.VoiceCallHistoryDiffUtil

class VoiceCallHistoryRowAdapter(var voiceCallHistoryListener: OnVoiceCallHistoryRowListener) : RecyclerView.Adapter<VoiceCallHistoryRowAdapter.MyViewHolder>() {

    private var voiceCallHistoryRecordsList = emptyList<VoiceCallerHistory>()

    class MyViewHolder(private val binding: VoiceCallHistoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun init(voiceCallerHistory: VoiceCallerHistory, action: OnVoiceCallHistoryRowListener)
        {
            itemView.setOnClickListener {
                action.onVoiceCallRecordClick(voiceCallerHistory, adapterPosition)
            }
        }

        fun bind(modelClass: VoiceCallerHistory) {
            binding.result = modelClass
            binding.executePendingBindings()
        }


        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = VoiceCallHistoryItemBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            VoiceCallHistoryItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentResult = voiceCallHistoryRecordsList[position]
        holder.bind(currentResult)
        holder.init(currentResult, voiceCallHistoryListener)
    }

    override fun getItemCount(): Int {
        return voiceCallHistoryRecordsList.size
    }

    fun setData(newData: List<VoiceCallerHistory>) {
        val voiceCallHistoryDiffUtil = VoiceCallHistoryDiffUtil(voiceCallHistoryRecordsList, newData)
        val diffUtilResult = DiffUtil.calculateDiff(voiceCallHistoryDiffUtil)
        voiceCallHistoryRecordsList = emptyList()
        voiceCallHistoryRecordsList = newData.reversed()
        diffUtilResult.dispatchUpdatesTo(this)
    }
    interface OnVoiceCallHistoryRowListener {
        fun onVoiceCallRecordClick(voiceCallerHistory: VoiceCallerHistory, position: Int)
    }
}
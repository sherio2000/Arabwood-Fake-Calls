package com.example.awfc.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import javax.annotation.Nullable

@Entity(tableName = "voice_call_history")
@Parcelize
data class VoiceCallerHistory(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @Nullable
    var callerName: String,
    @Nullable
    var callerNumber: String,
    @Nullable
    var duration: Int,
    @Nullable
    var device: Int,
    @Nullable
    var voicePlayback: String,
    @Nullable
    var dateTime: String
) : Parcelable

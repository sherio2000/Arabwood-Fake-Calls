package com.example.awfc.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import javax.annotation.Nullable

@Entity(tableName = "artist")
@Parcelize
data class Artist(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var name: String,
    var description: String,
    var image: String,
    var videoUrl1: String,
    @Nullable
    val videoUrl2: String?,
    @Nullable
    val videoUrl3: String?
) : Parcelable

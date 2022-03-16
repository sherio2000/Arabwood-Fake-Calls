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
    val name: String,
    val description: String,
    val image: String,
    val videoUrl1: String,
    @Nullable
    val videoUrl2: String?,
    @Nullable
    val videoUrl3: String?
) : Parcelable

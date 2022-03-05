package com.example.awfc.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.annotation.Nullable

@Entity(tableName = "artist")
data class Artist(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: Int,
    val image: String,
    val videoUrl1: String,
    @Nullable
    val videoUrl2: String,
    @Nullable
    val videoUrl3: String
)

package com.example.awfc.Dao

import androidx.room.Dao
import androidx.room.Query
import com.example.awfc.data.Artist
import kotlinx.coroutines.flow.Flow

@Dao
interface ArtistDao {
    @Query("SELECT * FROM artist")
    fun readAll(): Flow<List<Artist>>
}
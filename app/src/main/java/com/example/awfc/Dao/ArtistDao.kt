package com.example.awfc.Dao

import androidx.room.Dao
import androidx.room.Query
import com.example.awfc.data.Artist
import kotlinx.coroutines.flow.Flow

@Dao
interface ArtistDao {
    @Query("SELECT * FROM artist")
    fun readAll(): Flow<List<Artist>>

    @Query("SELECT * FROM artist WHERE name LIKE :searchQuery OR name_arabic LIKE :searchQuery")
    fun searchArtists(searchQuery: String): Flow<List<Artist>>
}
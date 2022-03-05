package com.example.awfc.data


import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.awfc.Dao.ArtistDao


@Database(entities = [Artist::class], version = 1, exportSchema = true)
abstract class ArtistDatabase: RoomDatabase() {
    abstract fun ArtistDao(): ArtistDao
}
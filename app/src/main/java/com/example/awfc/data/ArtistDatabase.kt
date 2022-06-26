package com.example.awfc.data


import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.awfc.dao.ArtistDao
import com.example.awfc.dao.VoiceCallerHistoryDao


@Database(entities = [Artist::class, VoiceCallerHistory::class], version = 1, exportSchema = true)
abstract class ArtistDatabase: RoomDatabase() {
    abstract fun ArtistDao(): ArtistDao
    abstract fun VoiceCallerHistoryDao(): VoiceCallerHistoryDao
}
package com.example.awfc.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.awfc.data.VoiceCallerHistory
import kotlinx.coroutines.flow.Flow

@Dao
interface VoiceCallerHistoryDao {
    @Query("SELECT * FROM voice_call_history")
    fun readAll(): Flow<List<VoiceCallerHistory>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addVoiceCallRecord(voiceCallerHistory: VoiceCallerHistory)
}
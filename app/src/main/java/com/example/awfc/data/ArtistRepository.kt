package com.example.awfc.data

import com.example.awfc.dao.ArtistDao
import com.example.awfc.dao.VoiceCallerHistoryDao
import javax.inject.Inject

class ArtistRepository @Inject constructor(
    private val artistDao: ArtistDao,
    private val voiceCallerHistoryDao: VoiceCallerHistoryDao
) {
    val readAll = artistDao.readAll()
}
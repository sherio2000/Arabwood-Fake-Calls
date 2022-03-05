package com.example.awfc.data

import com.example.awfc.Dao.ArtistDao
import javax.inject.Inject

class ArtistRepository @Inject constructor(
    private val artistDao: ArtistDao
) {
    val readAll = artistDao.readAll()
}
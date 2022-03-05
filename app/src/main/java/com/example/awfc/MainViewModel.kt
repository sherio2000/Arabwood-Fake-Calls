package com.example.awfc

import androidx.lifecycle.ViewModel
import com.example.awfc.data.ArtistRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(
    artistRepository: ArtistRepository
): ViewModel() {
    val readAll = artistRepository.readAll
}
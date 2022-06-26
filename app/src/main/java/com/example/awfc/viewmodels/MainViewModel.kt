package com.example.awfc.viewmodels

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.awfc.dao.ArtistDao
import com.example.awfc.dao.VoiceCallerHistoryDao
import com.example.awfc.data.Artist
import com.example.awfc.data.VoiceCallerHistory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


class MainViewModel @ViewModelInject constructor(
    private val artistDao: ArtistDao,
    private val voiceCallerHistoryDao: VoiceCallerHistoryDao,
    application: Application
): AndroidViewModel(application) {
    var artistsResponse: MutableLiveData<List<Artist>> = MutableLiveData()
    var voiceCallerHistoryResponse: MutableLiveData<List<VoiceCallerHistory>> = MutableLiveData()

    fun getArtists(): Flow<List<Artist>> = artistDao.readAll()
    fun saveVoiceCallRecord(voiceCallerHistory: VoiceCallerHistory) = viewModelScope.launch(Dispatchers.IO) {  voiceCallerHistoryDao.addVoiceCallRecord(voiceCallerHistory) }
    fun getVoiceCallHistory(): Flow<List<VoiceCallerHistory>> = voiceCallerHistoryDao.readAll()
    fun searchArtist(searchQuery: String): Flow<List<Artist>> {
        return artistDao.searchArtists(searchQuery)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<Application>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }
}
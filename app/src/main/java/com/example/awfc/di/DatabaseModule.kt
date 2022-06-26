package com.example.awfc.di

import android.content.Context
import androidx.room.Room
import com.example.awfc.data.ArtistDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        ArtistDatabase::class.java,
        "artist_db"
    ).createFromAsset("database/artist.db").build()

    @Singleton
    @Provides
    fun provideDao(database: ArtistDatabase) = database.ArtistDao()

    @Singleton
    @Provides
    fun provideHistoryDao(database: ArtistDatabase) = database.VoiceCallerHistoryDao()
}
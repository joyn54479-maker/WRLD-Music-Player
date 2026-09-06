package com.wrld.musicplayer.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.media3.exoplayer.ExoPlayer
import com.wrld.musicplayer.database.WrldMusicDatabase
import com.wrld.musicplayer.data.repository.PlaylistRepository
import com.wrld.musicplayer.data.repository.SongRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

val Context.preferencesDataStore: DataStore<Preferences> by preferencesDataStore(
    name = "wrld_preferences"
)

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): WrldMusicDatabase {
        return WrldMusicDatabase.getInstance(context)
    }

    @Provides
    @Singleton
    fun provideSongRepository(database: WrldMusicDatabase): SongRepository {
        return SongRepository(database.songDao())
    }

    @Provides
    @Singleton
    fun providePlaylistRepository(database: WrldMusicDatabase): PlaylistRepository {
        return PlaylistRepository(
            database.playlistDao(),
            database.playlistSongDao()
        )
    }

    @Provides
    @Singleton
    fun provideExoPlayer(@ApplicationContext context: Context): ExoPlayer {
        return ExoPlayer.Builder(context).build()
    }

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return context.preferencesDataStore
    }
}

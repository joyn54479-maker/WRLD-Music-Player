package com.wrld.musicplayer.di

import android.content.Context
import androidx.media3.exoplayer.ExoPlayer
import androidx.room.Room
import com.wrld.musicplayer.database.WrldDatabase
import com.wrld.musicplayer.database.dao.*
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
    fun provideWrldDatabase(
        @ApplicationContext context: Context
    ): WrldDatabase {
        return Room.databaseBuilder(
            context,
            WrldDatabase::class.java,
            "wrld_database"
        ).build()
    }

    @Singleton
    @Provides
    fun provideSongDao(database: WrldDatabase): SongDao = database.songDao()

    @Singleton
    @Provides
    fun providePlaylistDao(database: WrldDatabase): PlaylistDao = database.playlistDao()

    @Singleton
    @Provides
    fun provideAlbumDao(database: WrldDatabase): AlbumDao = database.albumDao()

    @Singleton
    @Provides
    fun provideArtistDao(database: WrldDatabase): ArtistDao = database.artistDao()

    @Singleton
    @Provides
    fun provideGenreDao(database: WrldDatabase): GenreDao = database.genreDao()

    @Singleton
    @Provides
    fun provideFolderDao(database: WrldDatabase): FolderDao = database.folderDao()

    @Singleton
    @Provides
    fun provideQueueDao(database: WrldDatabase): QueueDao = database.queueDao()
}

@Module
@InstallIn(SingletonComponent::class)
object MediaPlayerModule {
    @Singleton
    @Provides
    fun provideExoPlayer(
        @ApplicationContext context: Context
    ): ExoPlayer = ExoPlayer.Builder(context).build()
}

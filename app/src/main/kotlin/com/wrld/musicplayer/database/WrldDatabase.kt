package com.wrld.musicplayer.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.wrld.musicplayer.database.dao.*
import com.wrld.musicplayer.database.entity.*

@Database(
    entities = [
        SongEntity::class,
        AlbumEntity::class,
        ArtistEntity::class,
        GenreEntity::class,
        PlaylistEntity::class,
        PlaylistSongEntity::class,
        FolderEntity::class,
        QueueEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class WrldDatabase : RoomDatabase() {
    abstract fun songDao(): SongDao
    abstract fun playlistDao(): PlaylistDao
    abstract fun albumDao(): AlbumDao
    abstract fun artistDao(): ArtistDao
    abstract fun genreDao(): GenreDao
    abstract fun folderDao(): FolderDao
    abstract fun queueDao(): QueueDao

    companion object {
        @Volatile
        private var Instance: WrldDatabase? = null

        fun getDatabase(context: Context): WrldDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    WrldDatabase::class.java,
                    "wrld_database"
                ).build().also { Instance = it }
            }
        }
    }
}

package com.wrld.musicplayer.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.wrld.musicplayer.database.dao.PlaylistDao
import com.wrld.musicplayer.database.dao.PlaylistSongDao
import com.wrld.musicplayer.database.dao.SongDao
import com.wrld.musicplayer.database.entity.PlaylistEntity
import com.wrld.musicplayer.database.entity.PlaylistSongEntity
import com.wrld.musicplayer.database.entity.SongEntity

@Database(
    entities = [
        SongEntity::class,
        PlaylistEntity::class,
        PlaylistSongEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class WrldMusicDatabase : RoomDatabase() {
    abstract fun songDao(): SongDao
    abstract fun playlistDao(): PlaylistDao
    abstract fun playlistSongDao(): PlaylistSongDao

    companion object {
        @Volatile
        private var INSTANCE: WrldMusicDatabase? = null

        fun getInstance(context: Context): WrldMusicDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WrldMusicDatabase::class.java,
                    "wrld_music.db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

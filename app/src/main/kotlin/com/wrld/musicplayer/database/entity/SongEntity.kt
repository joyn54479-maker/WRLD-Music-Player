package com.wrld.musicplayer.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "songs")
data class SongEntity(
    @PrimaryKey val id: String,
    val title: String,
    val artist: String,
    val album: String,
    val duration: Long,
    val path: String,
    val dateAdded: Long = System.currentTimeMillis(),
    val playCount: Int = 0,
    val lastPlayed: Long = 0
)

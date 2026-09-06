package com.wrld.musicplayer.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "songs")
data class SongEntity(
    @PrimaryKey val id: String,
    val title: String,
    val artist: String,
    val album: String,
    val genre: String,
    val duration: Long,
    val path: String,
    val fileSize: Long,
    val dateAdded: Long,
    val dateModified: Long,
    val isFavorite: Boolean = false,
    val playCount: Int = 0,
    val lastPlayedTime: Long = 0,
    val albumArtPath: String? = null,
    val bitrate: Int = 0,
    val sampleRate: Int = 0
)

@Entity(tableName = "albums")
data class AlbumEntity(
    @PrimaryKey val id: String,
    val title: String,
    val artist: String,
    val songCount: Int,
    val artPath: String? = null,
    val dateAdded: Long
)

@Entity(tableName = "artists")
data class ArtistEntity(
    @PrimaryKey val id: String,
    val name: String,
    val songCount: Int,
    val albumCount: Int,
    val artPath: String? = null
)

@Entity(tableName = "genres")
data class GenreEntity(
    @PrimaryKey val id: String,
    val name: String,
    val songCount: Int
)

@Entity(tableName = "playlists")
data class PlaylistEntity(
    @PrimaryKey val id: String,
    val name: String,
    val description: String = "",
    val createdAt: Long,
    val modifiedAt: Long,
    val songCount: Int = 0
)

@Entity(tableName = "playlist_songs")
data class PlaylistSongEntity(
    @PrimaryKey val id: String,
    val playlistId: String,
    val songId: String,
    val position: Int,
    val addedAt: Long
)

@Entity(tableName = "folders")
data class FolderEntity(
    @PrimaryKey val id: String,
    val path: String,
    val name: String,
    val songCount: Int,
    val dateScanned: Long
)

@Entity(tableName = "queue")
data class QueueEntity(
    @PrimaryKey val id: String,
    val songId: String,
    val position: Int,
    val timestamp: Long
)

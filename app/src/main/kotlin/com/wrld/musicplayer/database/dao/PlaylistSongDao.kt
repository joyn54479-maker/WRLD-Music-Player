package com.wrld.musicplayer.database.dao

import androidx.room.*
import com.wrld.musicplayer.database.entity.PlaylistSongEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PlaylistSongDao {
    @Insert
    suspend fun insertPlaylistSong(relation: PlaylistSongEntity)

    @Query("DELETE FROM playlist_songs WHERE playlistId = :playlistId AND songId = :songId")
    suspend fun deletePlaylistSong(playlistId: String, songId: String)

    @Query("DELETE FROM playlist_songs WHERE playlistId = :playlistId")
    suspend fun deletePlaylistSongs(playlistId: String)

    @Query("SELECT songId FROM playlist_songs WHERE playlistId = :playlistId ORDER BY addedAt DESC")
    fun getPlaylistSongs(playlistId: String): Flow<List<String>>
}

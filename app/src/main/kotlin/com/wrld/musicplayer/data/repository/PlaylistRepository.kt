package com.wrld.musicplayer.data.repository

import com.wrld.musicplayer.database.dao.PlaylistDao
import com.wrld.musicplayer.database.dao.PlaylistSongDao
import com.wrld.musicplayer.database.entity.PlaylistEntity
import com.wrld.musicplayer.database.entity.PlaylistSongEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlaylistRepository @Inject constructor(
    private val playlistDao: PlaylistDao,
    private val playlistSongDao: PlaylistSongDao
) {
    fun getAllPlaylists(): Flow<List<PlaylistEntity>> = playlistDao.getAllPlaylists()

    fun getPlaylistById(id: String): Flow<PlaylistEntity> = playlistDao.getPlaylistById(id)

    suspend fun insertPlaylist(playlist: PlaylistEntity) = playlistDao.insertPlaylist(playlist)

    suspend fun updatePlaylist(playlist: PlaylistEntity) = playlistDao.updatePlaylist(playlist)

    suspend fun deletePlaylist(playlistId: String) {
        playlistDao.deletePlaylistById(playlistId)
        playlistSongDao.deletePlaylistSongs(playlistId)
    }

    suspend fun addSongToPlaylist(playlistId: String, songId: String) {
        val relation = PlaylistSongEntity(
            playlistId = playlistId,
            songId = songId
        )
        playlistSongDao.insertPlaylistSong(relation)
    }

    suspend fun removeSongFromPlaylist(playlistId: String, songId: String) {
        playlistSongDao.deletePlaylistSong(playlistId, songId)
    }

    fun getPlaylistSongs(playlistId: String): Flow<List<String>> =
        playlistSongDao.getPlaylistSongs(playlistId)
}

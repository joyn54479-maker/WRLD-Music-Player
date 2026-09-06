package com.wrld.musicplayer.data.repository

import com.wrld.musicplayer.database.dao.PlaylistDao
import com.wrld.musicplayer.database.entity.PlaylistEntity
import com.wrld.musicplayer.database.entity.PlaylistSongEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PlaylistRepository @Inject constructor(
    private val playlistDao: PlaylistDao
) {
    fun getAllPlaylists(): Flow<List<PlaylistEntity>> = playlistDao.getAllPlaylists()

    suspend fun getPlaylistById(playlistId: String): PlaylistEntity? =
        playlistDao.getPlaylistById(playlistId)

    suspend fun getPlaylistByName(name: String): PlaylistEntity? =
        playlistDao.getPlaylistByName(name)

    suspend fun createPlaylist(playlist: PlaylistEntity) =
        playlistDao.insertPlaylist(playlist)

    suspend fun updatePlaylist(playlist: PlaylistEntity) =
        playlistDao.updatePlaylist(playlist)

    suspend fun deletePlaylist(playlist: PlaylistEntity) {
        playlistDao.deletePlaylistSongs(playlist.id)
        playlistDao.deletePlaylist(playlist)
    }

    suspend fun deletePlaylistById(playlistId: String) {
        playlistDao.deletePlaylistSongs(playlistId)
        playlistDao.deletePlaylistById(playlistId)
    }

    fun getPlaylistCount(): Flow<Int> = playlistDao.getPlaylistCount()

    suspend fun addSongToPlaylist(playlistSong: PlaylistSongEntity) =
        playlistDao.addSongToPlaylist(playlistSong)

    suspend fun addSongsToPlaylist(playlistSongs: List<PlaylistSongEntity>) =
        playlistDao.addSongsToPlaylist(playlistSongs)

    fun getPlaylistSongs(playlistId: String): Flow<List<PlaylistSongEntity>> =
        playlistDao.getPlaylistSongs(playlistId)

    suspend fun removeSongFromPlaylist(playlistId: String, songId: String) =
        playlistDao.removeSongFromPlaylist(playlistId, songId)

    suspend fun updateSongPosition(playlistId: String, songId: String, position: Int) =
        playlistDao.updateSongPosition(playlistId, songId, position)

    suspend fun clearPlaylist(playlistId: String) =
        playlistDao.clearPlaylist(playlistId)
}

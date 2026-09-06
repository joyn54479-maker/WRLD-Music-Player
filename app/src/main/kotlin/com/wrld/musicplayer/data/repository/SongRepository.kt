package com.wrld.musicplayer.data.repository

import com.wrld.musicplayer.database.dao.SongDao
import com.wrld.musicplayer.database.entity.SongEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SongRepository @Inject constructor(
    private val songDao: SongDao
) {
    fun getAllSongs(): Flow<List<SongEntity>> = songDao.getAllSongs()

    fun getFavoriteSongs(): Flow<List<SongEntity>> = songDao.getFavoriteSongs()

    fun getRecentlyPlayed(limit: Int = 50): Flow<List<SongEntity>> =
        songDao.getRecentlyPlayed(limit)

    fun getRecentlyAdded(limit: Int = 50): Flow<List<SongEntity>> =
        songDao.getRecentlyAdded(limit)

    fun getMostPlayed(limit: Int = 50): Flow<List<SongEntity>> =
        songDao.getMostPlayed(limit)

    fun getSongsByArtist(artist: String): Flow<List<SongEntity>> =
        songDao.getSongsByArtist(artist)

    fun getSongsByAlbum(album: String): Flow<List<SongEntity>> =
        songDao.getSongsByAlbum(album)

    fun getSongsByGenre(genre: String): Flow<List<SongEntity>> =
        songDao.getSongsByGenre(genre)

    fun searchSongs(query: String): Flow<List<SongEntity>> =
        songDao.searchSongs(query)

    suspend fun insertSong(song: SongEntity) = songDao.insertSong(song)

    suspend fun insertSongs(songs: List<SongEntity>) = songDao.insertSongs(songs)

    suspend fun updateSong(song: SongEntity) = songDao.updateSong(song)

    suspend fun deleteSong(song: SongEntity) = songDao.deleteSong(song)

    suspend fun deleteSongByPath(path: String) = songDao.deleteSongByPath(path)

    suspend fun updateFavoriteStatus(songId: String, isFavorite: Boolean) =
        songDao.updateFavoriteStatus(songId, isFavorite)

    suspend fun updatePlayStats(songId: String, timestamp: Long) =
        songDao.updatePlayStats(songId, timestamp)

    fun getSongCount(): Flow<Int> = songDao.getSongCount()

    suspend fun deleteAllSongs() = songDao.deleteAllSongs()

    suspend fun getSongById(songId: String): SongEntity? = songDao.getSongById(songId)
}

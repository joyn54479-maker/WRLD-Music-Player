package com.wrld.musicplayer.data.repository

import com.wrld.musicplayer.database.dao.SongDao
import com.wrld.musicplayer.database.entity.SongEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SongRepository @Inject constructor(
    private val songDao: SongDao
) {
    fun getAllSongs(): Flow<List<SongEntity>> = songDao.getAllSongs()

    fun searchSongs(query: String): Flow<List<SongEntity>> = songDao.searchSongs("%$query%")

    fun getSongById(id: String): Flow<SongEntity> = songDao.getSongById(id)

    suspend fun insertSong(song: SongEntity) = songDao.insertSong(song)

    suspend fun insertSongs(songs: List<SongEntity>) = songDao.insertSongs(songs)

    suspend fun deleteSong(song: SongEntity) = songDao.deleteSong(song)

    suspend fun updateSong(song: SongEntity) = songDao.updateSong(song)

    fun getSongsByArtist(artist: String): Flow<List<SongEntity>> = songDao.getSongsByArtist(artist)

    fun getSongsByAlbum(album: String): Flow<List<SongEntity>> = songDao.getSongsByAlbum(album)
}

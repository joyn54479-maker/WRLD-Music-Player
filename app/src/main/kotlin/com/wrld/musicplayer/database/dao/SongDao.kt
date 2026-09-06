package com.wrld.musicplayer.database.dao

import androidx.room.*
import com.wrld.musicplayer.database.entity.SongEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SongDao {
    @Query("SELECT * FROM songs ORDER BY title ASC")
    fun getAllSongs(): Flow<List<SongEntity>>

    @Query("SELECT * FROM songs WHERE title LIKE :query OR artist LIKE :query OR album LIKE :query")
    fun searchSongs(query: String): Flow<List<SongEntity>>

    @Query("SELECT * FROM songs WHERE id = :id")
    fun getSongById(id: String): Flow<SongEntity>

    @Insert
    suspend fun insertSong(song: SongEntity)

    @Insert
    suspend fun insertSongs(songs: List<SongEntity>)

    @Delete
    suspend fun deleteSong(song: SongEntity)

    @Update
    suspend fun updateSong(song: SongEntity)

    @Query("SELECT * FROM songs WHERE artist = :artist ORDER BY title ASC")
    fun getSongsByArtist(artist: String): Flow<List<SongEntity>>

    @Query("SELECT * FROM songs WHERE album = :album ORDER BY title ASC")
    fun getSongsByAlbum(album: String): Flow<List<SongEntity>>
}

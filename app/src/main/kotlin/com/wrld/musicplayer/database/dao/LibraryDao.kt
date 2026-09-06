package com.wrld.musicplayer.database.dao

import androidx.room.*
import com.wrld.musicplayer.database.entity.AlbumEntity
import com.wrld.musicplayer.database.entity.ArtistEntity
import com.wrld.musicplayer.database.entity.GenreEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AlbumDao {
    @Query("SELECT * FROM albums ORDER BY title ASC")
    fun getAllAlbums(): Flow<List<AlbumEntity>>

    @Query("SELECT * FROM albums WHERE id = :albumId")
    suspend fun getAlbumById(albumId: String): AlbumEntity?

    @Query("SELECT * FROM albums WHERE artist = :artist ORDER BY title ASC")
    fun getAlbumsByArtist(artist: String): Flow<List<AlbumEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAlbum(album: AlbumEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAlbums(albums: List<AlbumEntity>)

    @Update
    suspend fun updateAlbum(album: AlbumEntity)

    @Delete
    suspend fun deleteAlbum(album: AlbumEntity)

    @Query("DELETE FROM albums")
    suspend fun deleteAllAlbums()

    @Query("SELECT COUNT(*) FROM albums")
    fun getAlbumCount(): Flow<Int>
}

@Dao
interface ArtistDao {
    @Query("SELECT * FROM artists ORDER BY name ASC")
    fun getAllArtists(): Flow<List<ArtistEntity>>

    @Query("SELECT * FROM artists WHERE id = :artistId")
    suspend fun getArtistById(artistId: String): ArtistEntity?

    @Query("SELECT * FROM artists WHERE name = :name")
    suspend fun getArtistByName(name: String): ArtistEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArtist(artist: ArtistEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArtists(artists: List<ArtistEntity>)

    @Update
    suspend fun updateArtist(artist: ArtistEntity)

    @Delete
    suspend fun deleteArtist(artist: ArtistEntity)

    @Query("DELETE FROM artists")
    suspend fun deleteAllArtists()

    @Query("SELECT COUNT(*) FROM artists")
    fun getArtistCount(): Flow<Int>
}

@Dao
interface GenreDao {
    @Query("SELECT * FROM genres ORDER BY name ASC")
    fun getAllGenres(): Flow<List<GenreEntity>>

    @Query("SELECT * FROM genres WHERE id = :genreId")
    suspend fun getGenreById(genreId: String): GenreEntity?

    @Query("SELECT * FROM genres WHERE name = :name")
    suspend fun getGenreByName(name: String): GenreEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenre(genre: GenreEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenres(genres: List<GenreEntity>)

    @Update
    suspend fun updateGenre(genre: GenreEntity)

    @Delete
    suspend fun deleteGenre(genre: GenreEntity)

    @Query("DELETE FROM genres")
    suspend fun deleteAllGenres()

    @Query("SELECT COUNT(*) FROM genres")
    fun getGenreCount(): Flow<Int>
}

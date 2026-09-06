package com.wrld.musicplayer.data.repository

import com.wrld.musicplayer.database.dao.AlbumDao
import com.wrld.musicplayer.database.dao.ArtistDao
import com.wrld.musicplayer.database.dao.GenreDao
import com.wrld.musicplayer.database.entity.AlbumEntity
import com.wrld.musicplayer.database.entity.ArtistEntity
import com.wrld.musicplayer.database.entity.GenreEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AlbumRepository @Inject constructor(
    private val albumDao: AlbumDao
) {
    fun getAllAlbums(): Flow<List<AlbumEntity>> = albumDao.getAllAlbums()

    suspend fun getAlbumById(albumId: String): AlbumEntity? = albumDao.getAlbumById(albumId)

    fun getAlbumsByArtist(artist: String): Flow<List<AlbumEntity>> =
        albumDao.getAlbumsByArtist(artist)

    suspend fun insertAlbum(album: AlbumEntity) = albumDao.insertAlbum(album)

    suspend fun insertAlbums(albums: List<AlbumEntity>) = albumDao.insertAlbums(albums)

    suspend fun updateAlbum(album: AlbumEntity) = albumDao.updateAlbum(album)

    suspend fun deleteAlbum(album: AlbumEntity) = albumDao.deleteAlbum(album)

    suspend fun deleteAllAlbums() = albumDao.deleteAllAlbums()

    fun getAlbumCount(): Flow<Int> = albumDao.getAlbumCount()
}

class ArtistRepository @Inject constructor(
    private val artistDao: ArtistDao
) {
    fun getAllArtists(): Flow<List<ArtistEntity>> = artistDao.getAllArtists()

    suspend fun getArtistById(artistId: String): ArtistEntity? = artistDao.getArtistById(artistId)

    suspend fun getArtistByName(name: String): ArtistEntity? = artistDao.getArtistByName(name)

    suspend fun insertArtist(artist: ArtistEntity) = artistDao.insertArtist(artist)

    suspend fun insertArtists(artists: List<ArtistEntity>) = artistDao.insertArtists(artists)

    suspend fun updateArtist(artist: ArtistEntity) = artistDao.updateArtist(artist)

    suspend fun deleteArtist(artist: ArtistEntity) = artistDao.deleteArtist(artist)

    suspend fun deleteAllArtists() = artistDao.deleteAllArtists()

    fun getArtistCount(): Flow<Int> = artistDao.getArtistCount()
}

class GenreRepository @Inject constructor(
    private val genreDao: GenreDao
) {
    fun getAllGenres(): Flow<List<GenreEntity>> = genreDao.getAllGenres()

    suspend fun getGenreById(genreId: String): GenreEntity? = genreDao.getGenreById(genreId)

    suspend fun getGenreByName(name: String): GenreEntity? = genreDao.getGenreByName(name)

    suspend fun insertGenre(genre: GenreEntity) = genreDao.insertGenre(genre)

    suspend fun insertGenres(genres: List<GenreEntity>) = genreDao.insertGenres(genres)

    suspend fun updateGenre(genre: GenreEntity) = genreDao.updateGenre(genre)

    suspend fun deleteGenre(genre: GenreEntity) = genreDao.deleteGenre(genre)

    suspend fun deleteAllGenres() = genreDao.deleteAllGenres()

    fun getGenreCount(): Flow<Int> = genreDao.getGenreCount()
}

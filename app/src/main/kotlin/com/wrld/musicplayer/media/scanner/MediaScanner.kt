package com.wrld.musicplayer.media.scanner

import android.content.ContentResolver
import android.content.Context
import android.provider.MediaStore
import com.wrld.musicplayer.database.entity.SongEntity
import com.wrld.musicplayer.data.repository.SongRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.util.*
import javax.inject.Inject

class MediaScanner @Inject constructor(
    private val context: Context,
    private val songRepository: SongRepository
) {
    suspend fun scanLibrary() = withContext(Dispatchers.IO) {
        val contentResolver: ContentResolver = context.contentResolver
        val audioUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        
        val projection = arrayOf(
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.ALBUM,
            MediaStore.Audio.Media.GENRE,
            MediaStore.Audio.Media.DURATION,
            MediaStore.Audio.Media.DATA,
            MediaStore.Audio.Media.SIZE,
            MediaStore.Audio.Media.DATE_ADDED,
            MediaStore.Audio.Media.DATE_MODIFIED,
            MediaStore.Audio.Media.ALBUM_ID
        )

        val selection = "${MediaStore.Audio.Media.IS_MUSIC} != 0"
        val cursor = contentResolver.query(audioUri, projection, selection, null, null)

        val songs = mutableListOf<SongEntity>()
        cursor?.use {
            val idColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)
            val titleColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)
            val artistColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)
            val albumColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM)
            val durationColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)
            val dataColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)
            val sizeColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE)
            val dateAddedColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media.DATE_ADDED)
            val dateModifiedColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media.DATE_MODIFIED)

            while (it.moveToNext()) {
                val id = it.getString(idColumn)
                val title = it.getString(titleColumn) ?: "Unknown"
                val artist = it.getString(artistColumn) ?: "Unknown Artist"
                val album = it.getString(albumColumn) ?: "Unknown Album"
                val duration = it.getLong(durationColumn)
                val path = it.getString(dataColumn) ?: continue
                val size = it.getLong(sizeColumn)
                val dateAdded = it.getLong(dateAddedColumn)
                val dateModified = it.getLong(dateModifiedColumn)

                // Skip if file doesn't exist
                if (!File(path).exists()) continue

                songs.add(
                    SongEntity(
                        id = id,
                        title = title,
                        artist = artist,
                        album = album,
                        genre = "Unknown",
                        duration = duration,
                        path = path,
                        fileSize = size,
                        dateAdded = dateAdded * 1000,
                        dateModified = dateModified * 1000
                    )
                )
            }
        }

        // Replace all songs with scanned ones
        songRepository.deleteAllSongs()
        if (songs.isNotEmpty()) {
            songRepository.insertSongs(songs)
        }
        songs.size
    }

    suspend fun rescanLibrary() = scanLibrary()
}

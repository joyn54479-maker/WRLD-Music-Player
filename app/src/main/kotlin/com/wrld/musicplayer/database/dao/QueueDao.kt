package com.wrld.musicplayer.database.dao

import androidx.room.*
import com.wrld.musicplayer.database.entity.FolderEntity
import com.wrld.musicplayer.database.entity.QueueEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FolderDao {
    @Query("SELECT * FROM folders ORDER BY name ASC")
    fun getAllFolders(): Flow<List<FolderEntity>>

    @Query("SELECT * FROM folders WHERE id = :folderId")
    suspend fun getFolderById(folderId: String): FolderEntity?

    @Query("SELECT * FROM folders WHERE path = :path")
    suspend fun getFolderByPath(path: String): FolderEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFolder(folder: FolderEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFolders(folders: List<FolderEntity>)

    @Update
    suspend fun updateFolder(folder: FolderEntity)

    @Delete
    suspend fun deleteFolder(folder: FolderEntity)

    @Query("DELETE FROM folders")
    suspend fun deleteAllFolders()
}

@Dao
interface QueueDao {
    @Query("SELECT * FROM queue ORDER BY position ASC")
    fun getQueue(): Flow<List<QueueEntity>>

    @Query("SELECT * FROM queue WHERE id = :queueItemId")
    suspend fun getQueueItem(queueItemId: String): QueueEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToQueue(item: QueueEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToQueueBulk(items: List<QueueEntity>)

    @Delete
    suspend fun removeFromQueue(item: QueueEntity)

    @Query("DELETE FROM queue WHERE id = :queueItemId")
    suspend fun removeFromQueueById(queueItemId: String)

    @Query("DELETE FROM queue")
    suspend fun clearQueue()

    @Query("SELECT COUNT(*) FROM queue")
    fun getQueueSize(): Flow<Int>
}

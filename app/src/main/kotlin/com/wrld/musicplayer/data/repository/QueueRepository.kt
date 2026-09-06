package com.wrld.musicplayer.data.repository

import com.wrld.musicplayer.database.dao.QueueDao
import com.wrld.musicplayer.database.entity.QueueEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class QueueRepository @Inject constructor(
    private val queueDao: QueueDao
) {
    fun getQueue(): Flow<List<QueueEntity>> = queueDao.getQueue()

    suspend fun getQueueItem(queueItemId: String): QueueEntity? =
        queueDao.getQueueItem(queueItemId)

    suspend fun addToQueue(item: QueueEntity) = queueDao.addToQueue(item)

    suspend fun addToQueueBulk(items: List<QueueEntity>) = queueDao.addToQueueBulk(items)

    suspend fun removeFromQueue(item: QueueEntity) = queueDao.removeFromQueue(item)

    suspend fun removeFromQueueById(queueItemId: String) =
        queueDao.removeFromQueueById(queueItemId)

    suspend fun clearQueue() = queueDao.clearQueue()

    fun getQueueSize(): Flow<Int> = queueDao.getQueueSize()
}

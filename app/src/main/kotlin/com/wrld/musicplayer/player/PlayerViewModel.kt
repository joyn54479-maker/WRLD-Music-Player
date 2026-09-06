package com.wrld.musicplayer.player

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.wrld.musicplayer.data.repository.QueueRepository
import com.wrld.musicplayer.data.repository.SongRepository
import com.wrld.musicplayer.database.entity.QueueEntity
import com.wrld.musicplayer.database.entity.SongEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import java.util.UUID

@HiltViewModel
class PlayerViewModel @Inject constructor(
    private val exoPlayer: ExoPlayer,
    private val songRepository: SongRepository,
    private val queueRepository: QueueRepository
) : ViewModel() {

    private val _isPlaying = MutableStateFlow(false)
    val isPlaying: StateFlow<Boolean> = _isPlaying.asStateFlow()

    private val _currentSong = MutableStateFlow<SongEntity?>(null)
    val currentSong: StateFlow<SongEntity?> = _currentSong.asStateFlow()

    private val _queue = MutableStateFlow<List<SongEntity>>(emptyList())
    val queue: StateFlow<List<SongEntity>> = _queue.asStateFlow()

    private val _currentPosition = MutableStateFlow(0L)
    val currentPosition: StateFlow<Long> = _currentPosition.asStateFlow()

    private val _duration = MutableStateFlow(0L)
    val duration: StateFlow<Long> = _duration.asStateFlow()

    private val _repeatMode = MutableStateFlow(Player.REPEAT_MODE_OFF)
    val repeatMode: StateFlow<Int> = _repeatMode.asStateFlow()

    private val _isShuffleEnabled = MutableStateFlow(false)
    val isShuffleEnabled: StateFlow<Boolean> = _isShuffleEnabled.asStateFlow()

    private val _playbackSpeed = MutableStateFlow(1.0f)
    val playbackSpeed: StateFlow<Float> = _playbackSpeed.asStateFlow()

    init {
        setupPlayerListener()
    }

    private fun setupPlayerListener() {
        exoPlayer.addListener(object : Player.Listener {
            override fun onPlaybackStateChanged(playbackState: Int) {
                _isPlaying.value = exoPlayer.isPlaying
            }

            override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
                val currentIndex = exoPlayer.currentMediaItemIndex
                if (currentIndex >= 0 && currentIndex < _queue.value.size) {
                    _currentSong.value = _queue.value[currentIndex]
                }
            }

            override fun onIsPlayingChanged(isPlaying: Boolean) {
                _isPlaying.value = isPlaying
                if (isPlaying && _currentSong.value != null) {
                    viewModelScope.launch {
                        val song = _currentSong.value
                        if (song != null) {
                            songRepository.updatePlayStats(song.id, System.currentTimeMillis())
                        }
                    }
                }
            }
        })
    }

    fun loadQueue(songs: List<SongEntity>) {
        _queue.value = songs
        val mediaItems = songs.map { MediaItem.fromUri("file://${it.path}") }
        exoPlayer.setMediaItems(mediaItems)
    }

    fun play() {
        exoPlayer.play()
        _isPlaying.value = true
    }

    fun pause() {
        exoPlayer.pause()
        _isPlaying.value = false
    }

    fun togglePlayPause() {
        if (_isPlaying.value) pause() else play()
    }

    fun next() {
        exoPlayer.seekToNextMediaItem()
    }

    fun previous() {
        exoPlayer.seekToPreviousMediaItem()
    }

    fun seekTo(positionMs: Long) {
        exoPlayer.seekTo(positionMs)
        _currentPosition.value = positionMs
    }

    fun setRepeatMode(mode: Int) {
        exoPlayer.repeatMode = mode
        _repeatMode.value = mode
    }

    fun toggleShuffle() {
        val newShuffleState = !_isShuffleEnabled.value
        exoPlayer.shuffleModeEnabled = newShuffleState
        _isShuffleEnabled.value = newShuffleState
    }

    fun setPlaybackSpeed(speed: Float) {
        exoPlayer.setPlaybackSpeed(speed)
        _playbackSpeed.value = speed
    }

    fun playSong(song: SongEntity) {
        viewModelScope.launch {
            val index = _queue.value.indexOfFirst { it.id == song.id }
            if (index >= 0) {
                exoPlayer.seekToDefaultPosition(index)
                play()
            }
        }
    }

    fun addToQueue(songs: List<SongEntity>) {
        val currentQueue = _queue.value.toMutableList()
        currentQueue.addAll(songs)
        _queue.value = currentQueue
        val newMediaItems = songs.map { MediaItem.fromUri("file://${it.path}") }
        exoPlayer.addMediaItems(newMediaItems)
    }

    fun clearQueue() {
        _queue.value = emptyList()
        exoPlayer.clearMediaItems()
    }

    fun updateCurrentPosition(position: Long) {
        _currentPosition.value = position
    }

    fun updateDuration(duration: Long) {
        _duration.value = duration
    }

    override fun onCleared() {
        super.onCleared()
        exoPlayer.release()
    }
}

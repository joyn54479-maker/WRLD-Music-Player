package com.wrld.musicplayer.player

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.wrld.musicplayer.database.entity.SongEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val exoPlayer: ExoPlayer
) : ViewModel() {

    private val _isPlaying = MutableStateFlow(false)
    val isPlaying: StateFlow<Boolean> = _isPlaying.asStateFlow()

    private val _currentSong = MutableStateFlow<SongEntity?>(null)
    val currentSong: StateFlow<SongEntity?> = _currentSong.asStateFlow()

    private val _currentPosition = MutableStateFlow(0L)
    val currentPosition: StateFlow<Long> = _currentPosition.asStateFlow()

    private val _duration = MutableStateFlow(0L)
    val duration: StateFlow<Long> = _duration.asStateFlow()

    private val _repeatMode = MutableStateFlow(0) // 0: OFF, 1: ALL, 2: ONE
    val repeatMode: StateFlow<Int> = _repeatMode.asStateFlow()

    private val _isShuffleEnabled = MutableStateFlow(false)
    val isShuffleEnabled: StateFlow<Boolean> = _isShuffleEnabled.asStateFlow()

    private val _playbackSpeed = MutableStateFlow(1.0f)
    val playbackSpeed: StateFlow<Float> = _playbackSpeed.asStateFlow()

    private var playlist = mutableListOf<SongEntity>()
    private var currentIndex = 0

    init {
        setupExoPlayer()
    }

    private fun setupExoPlayer() {
        exoPlayer.addListener(object : androidx.media3.common.Player.Listener {
            override fun onIsPlayingChanged(isPlaying: Boolean) {
                _isPlaying.value = isPlaying
            }

            override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
                updateCurrentSongInfo()
            }

            override fun onPositionDiscontinuity(
                oldPos: androidx.media3.common.Player.PositionInfo,
                newPos: androidx.media3.common.Player.PositionInfo,
                reason: Int
            ) {
                _currentPosition.value = exoPlayer.currentPosition
            }
        })
    }

    fun playSong(song: SongEntity) {
        _currentSong.value = song
        val mediaItem = MediaItem.fromUri(song.path)
        exoPlayer.setMediaItem(mediaItem)
        exoPlayer.prepare()
        exoPlayer.play()
        _isPlaying.value = true

        viewModelScope.launch {
            _duration.value = exoPlayer.duration
        }
    }

    fun playPlaylist(songs: List<SongEntity>, startIndex: Int = 0) {
        playlist = songs.toMutableList()
        currentIndex = startIndex
        if (songs.isNotEmpty()) {
            playSong(songs[startIndex])
        }
    }

    fun togglePlayPause() {
        if (exoPlayer.isPlaying) {
            exoPlayer.pause()
        } else {
            exoPlayer.play()
        }
    }

    fun next() {
        if (playlist.isNotEmpty()) {
            currentIndex = (currentIndex + 1) % playlist.size
            playSong(playlist[currentIndex])
        }
    }

    fun previous() {
        if (playlist.isNotEmpty()) {
            currentIndex = if (currentIndex == 0) playlist.size - 1 else currentIndex - 1
            playSong(playlist[currentIndex])
        }
    }

    fun seekTo(position: Long) {
        exoPlayer.seekTo(position)
        _currentPosition.value = position
    }

    fun setRepeatMode(mode: Int) {
        _repeatMode.value = mode
        exoPlayer.repeatMode = when (mode) {
            1 -> androidx.media3.common.Player.REPEAT_MODE_ALL
            2 -> androidx.media3.common.Player.REPEAT_MODE_ONE
            else -> androidx.media3.common.Player.REPEAT_MODE_OFF
        }
    }

    fun toggleShuffle() {
        val newState = !_isShuffleEnabled.value
        _isShuffleEnabled.value = newState
        exoPlayer.shuffleModeEnabled = newState
    }

    fun setPlaybackSpeed(speed: Float) {
        _playbackSpeed.value = speed
        exoPlayer.setPlaybackSpeed(speed)
    }

    private fun updateCurrentSongInfo() {
        if (playlist.isNotEmpty() && currentIndex < playlist.size) {
            _currentSong.value = playlist[currentIndex]
        }
    }

    override fun onCleared() {
        exoPlayer.release()
        super.onCleared()
    }
}

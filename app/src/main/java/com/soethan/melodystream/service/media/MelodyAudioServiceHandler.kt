package com.soethan.melodystream.service.media

import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MelodyAudioServiceHandler @Inject constructor(
    private val player: ExoPlayer
) : Player.Listener {
    private val _audioState: MutableStateFlow<AppMediaState> =
        MutableStateFlow(AppMediaState.Initial)

    val audioState: StateFlow<AppMediaState> = _audioState.asStateFlow()

    private var job: Job? = null

    init {
        player.addListener(this)
        job = Job()
    }


    fun addMediaItem(mediaItem: MediaItem){
        player.setMediaItem(mediaItem)
        player.prepare()
    }

    fun setMediaItemList(mediaItems:List<MediaItem>){
        player.setMediaItems(mediaItems)
        player.prepare()
    }

    override fun onPlaybackStateChanged(playbackState: Int) {
       when(playbackState){
           ExoPlayer.STATE_BUFFERING -> _audioState.value  =
               AppMediaState.Buffering(player.currentPosition)
           ExoPlayer.STATE_READY -> _audioState.value = AppMediaState.Ready(player.duration)
       }
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onIsPlayingChanged(isPlaying: Boolean) {
        _audioState.value = AppMediaState.Playing(isPlaying = isPlaying)
        if (isPlaying){
            GlobalScope.launch(Dispatchers.Main){
                startProgressUpdate()
            }
        }else{
            stopProgressUpdate()
        }
    }


    private suspend fun startProgressUpdate() = job.run {
        while (true){
            delay(500)
            _audioState.value = AppMediaState.Progress(player.currentPosition)
        }
    }


    private fun stopProgressUpdate(){
        job?.cancel()
        _audioState.value = AppMediaState.Playing(isPlaying = false)

    }
}


sealed class AppMediaState {
    object Initial : AppMediaState()


    data class Ready(val duration: Long) : AppMediaState()
    data class Progress(val progress: Long) : AppMediaState()

    data class Buffering(val progress: Long) : AppMediaState()

    data class Playing(val isPlaying: Boolean) : AppMediaState()
}


sealed class AppPlayerEvent {
    data object PlayPause : AppPlayerEvent()
    data object Backward : AppPlayerEvent()

    data object Forward : AppPlayerEvent()

    data object Stop : AppPlayerEvent()

    data class UpdateProgress(val newProgress: Float) : AppPlayerEvent()
}
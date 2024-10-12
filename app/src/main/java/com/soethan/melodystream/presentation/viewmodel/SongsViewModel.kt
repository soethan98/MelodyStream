package com.soethan.melodystream.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soethan.melodystream.data.repository.AudioRepository
import com.soethan.melodystream.data.repository.ITrackRepository
import com.soethan.melodystream.data.repository.TrackRepository
import com.soethan.melodystream.presentation.UIState
import com.soethan.melodystream.presentation.model.UiSongInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SongsViewModel @Inject constructor(
    private val trackRepository: ITrackRepository
) : ViewModel() {

    private val _audioList = MutableStateFlow<UIState<List<UiSongInfo>>>(UIState.Idle)
    val audioList: StateFlow<UIState<List<UiSongInfo>>>
        get() = _audioList

    init {
        loadMusicFiles()
    }

    fun loadMusicFiles() {
        Log.i("SongsViewModel", "loadMusicFiles: ")

        viewModelScope.launch {
            val musicList = trackRepository.getAllTracks()
            _audioList.value = UIState.Content(data = musicList.map { UiSongInfo(songInfo = it) })
        }
    }
}

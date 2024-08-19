package com.soethan.melodystream.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soethan.melodystream.Audio
import com.soethan.melodystream.data.repository.AudioRepository
import com.soethan.melodystream.presentation.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AudioViewModel @Inject constructor(
    private val audioRepository: AudioRepository
) : ViewModel() {

    private val _audioList = MutableStateFlow<UIState<List<Audio>>>(UIState.Idle)
    val audioList: StateFlow<UIState<List<Audio>>>
        get() = _audioList

    init {
        loadMusicFiles()
    }

    fun loadMusicFiles() {
        viewModelScope.launch {
            val musicList = audioRepository.getAudioData()
            _audioList.value = UIState.Content(data = musicList)
        }
    }
}



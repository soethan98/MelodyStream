package com.soethan.melodystream.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soethan.melodystream.data.model.ArtistInfo
import com.soethan.melodystream.data.repository.ArtistRepository
import com.soethan.melodystream.data.repository.AudioRepository
import com.soethan.melodystream.presentation.UIState
import com.soethan.melodystream.presentation.model.UiSongInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtistViewModel @Inject constructor(
    private val artistRepository: ArtistRepository
) : ViewModel() {

    private val _audioList = MutableStateFlow<UIState<List<ArtistInfo>>>(UIState.Idle)
    val audioList: StateFlow<UIState<List<ArtistInfo>>>
        get() = _audioList

    init {
        loadMusicFiles()
    }

    fun loadMusicFiles() {
        Log.i("ArtistViewModel", "loadMusicFiles: ")

        viewModelScope.launch {
            val musicList = artistRepository.getAllArtists()
            _audioList.value = UIState.Content(data = musicList)
        }
    }
}

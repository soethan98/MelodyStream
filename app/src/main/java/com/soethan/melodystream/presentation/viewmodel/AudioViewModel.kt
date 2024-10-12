package com.soethan.melodystream.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soethan.melodystream.AudioInfo
import com.soethan.melodystream.data.model.SongInfo
import com.soethan.melodystream.data.repository.AudioRepository
import com.soethan.melodystream.presentation.UIState
import com.soethan.melodystream.presentation.data
import com.soethan.melodystream.presentation.model.UiArtistInfo
import com.soethan.melodystream.presentation.model.UiSongInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AudioViewModel @Inject constructor(
    private val audioRepository: AudioRepository
) : ViewModel() {

    private val _audioList = MutableStateFlow<UIState<List<UiSongInfo>>>(UIState.Idle)
    val audioList: StateFlow<UIState<List<UiSongInfo>>>
        get() = _audioList


    private val _artistList = MutableStateFlow<UIState<List<UiArtistInfo>>>(UIState.Idle)
    val artistList: StateFlow<UIState<List<UiArtistInfo>>>
        get() = _artistList

    init {
        loadMusicFiles()
    }

    fun loadMusicFiles() {
        viewModelScope.launch {
//            val musicList = audioRepository.getAudioData()
//            _audioList.value = UIState.Content(data = musicList.map { UiSongInfo(songInfo = it) })
        }
    }


    fun getArtistList() {
        if (audioList.value.data() != null) {
//            val songsByArtist = audioList.value.data()?.groupBy { it.songInfo.artist }
//            _artistList.value = UIState.Content(
//                songsByArtist.entries.map {
//                    UiArtistInfo(it.key)
//                })
//
//        }
        }
    }
}



package com.soethan.melodystream.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soethan.melodystream.data.model.GenreInfo
import com.soethan.melodystream.data.repository.AudioRepository
import com.soethan.melodystream.data.repository.IGenreRepository
import com.soethan.melodystream.presentation.UIState
import com.soethan.melodystream.presentation.model.UiSongInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenresViewModel @Inject constructor(
    private val genreRepository: IGenreRepository
) : ViewModel() {

    private val _audioList = MutableStateFlow<UIState<List<GenreInfo>>>(UIState.Idle)
    val audioList: StateFlow<UIState<List<GenreInfo>>>
        get() = _audioList

    init {
        loadMusicFiles()
    }

    fun loadMusicFiles() {
        Log.i("GenresViewModel", "loadMusicFiles: ")

        viewModelScope.launch {
            val musicList = genreRepository.getAllGenres()
            _audioList.value = UIState.Content(data = musicList)
        }
    }
}

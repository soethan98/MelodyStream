package com.soethan.melodystream.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soethan.melodystream.data.model.AlbumInfo
import com.soethan.melodystream.data.repository.IAlbumRepository
import com.soethan.melodystream.presentation.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AlbumsViewModel @Inject constructor(
    private val albumRepository: IAlbumRepository
) : ViewModel() {

    private val _audioList = MutableStateFlow<UIState<List<AlbumInfo>>>(UIState.Idle)
    val audioList: StateFlow<UIState<List<AlbumInfo>>>
        get() = _audioList

    init {
        loadMusicFiles()
    }

    fun loadMusicFiles() {
        Log.i("AlbumsViewModel", "loadMusicFiles: ")
        viewModelScope.launch {
            val musicList = albumRepository.getAllAlbums()
            _audioList.value = UIState.Content(data = musicList )
        }
    }
}

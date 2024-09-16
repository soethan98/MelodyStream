package com.soethan.melodystream.presentation.model

import com.soethan.melodystream.data.model.SongInfo

data class UiSongInfo(
    val isFavorite: Boolean = false,
    val isPlaying: Boolean = false,
    val songInfo: SongInfo
)
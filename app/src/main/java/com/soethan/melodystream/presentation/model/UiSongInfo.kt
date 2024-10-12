package com.soethan.melodystream.presentation.model

import com.soethan.melodystream.data.model.SongInfo
import com.soethan.melodystream.media.model.Track

data class UiSongInfo(
    val isFavorite: Boolean = false,
    val isPlaying: Boolean = false,
    val songInfo: Track
)
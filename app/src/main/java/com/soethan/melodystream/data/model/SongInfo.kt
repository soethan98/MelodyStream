package com.soethan.melodystream.data.model

import android.graphics.Bitmap
import android.net.Uri

data class SongInfo(
    val uri: Uri,
    val displayName: String,
    val id: Long,
    val artist: String,
    val data: String,
    val duration: Int,
    val title: String,
    val albumArtCover: Bitmap?,
    val albumName: String,

    )
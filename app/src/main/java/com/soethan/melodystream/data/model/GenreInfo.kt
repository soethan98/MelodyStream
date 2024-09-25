package com.soethan.melodystream.data.model

import androidx.annotation.DrawableRes
import com.soethan.melodystream.R

data class GenreInfo(
    val id: Long,
    val name: String,
    @DrawableRes val defaultAlbumArtRes: Int = R.drawable.ic_genre,
)
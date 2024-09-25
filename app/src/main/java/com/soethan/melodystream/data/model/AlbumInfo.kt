package com.soethan.melodystream.data.model

import androidx.annotation.DrawableRes
import com.soethan.melodystream.R

data class AlbumInfo(
    val albumId: Long = 0,
    val albumName: String = "",
    val artistId: Long? = 0,
    val artistName: String,
    val numberOfSongs: Int,
    val coverArt:String?,
    @DrawableRes val defaultAlbumArtRes: Int = R.drawable.ic_album
)
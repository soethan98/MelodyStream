package com.soethan.melodystream.data.model

import com.soethan.melodystream.R

data class ArtistInfo(
    var artistId: Long = 0,
    var artistName: String,
    val defaultAlbumArtRes: Int = R.drawable.ic_artist,
    val numberOfAlbums:Int,
    val numberOfTracks:Int
)
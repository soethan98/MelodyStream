package com.soethan.melodystream.media.model

import android.graphics.Bitmap
import android.net.Uri
import com.soethan.melodystream.data.model.AlbumInfo
import com.soethan.melodystream.data.model.ArtistInfo

data class Track(


    val id: Long,
    val mediaStoreId: Long,
    val title: String,
    val artist: String,
    val path: String,
    val duration: Int,
    val album: String,
    val genre: String,
    val coverArt: String,
    val playListId: Int,
    var albumId: Long,
    var artistId: Long,
    var genreId: Long,
    var year: Int,

    ) {
    fun getAlbum(): AlbumInfo {
        return AlbumInfo.Builder.albumId(albumId)
            .artistId(artistId)
            .albumName(album)
            .artistName(artist)
            .coverArt(coverArt)
            .build()
    }

    fun getArtist(): ArtistInfo {

        return ArtistInfo.Builder.setArtistId(artistId = artistId)
            .setArtistName(artistName = artist)
            .setCoverArt(coverArt = coverArt)
            .build()

    }
}



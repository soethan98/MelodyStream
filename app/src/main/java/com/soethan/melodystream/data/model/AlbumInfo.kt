package com.soethan.melodystream.data.model

import androidx.annotation.DrawableRes
import com.soethan.melodystream.R
import com.soethan.melodystream.media.model.Track

data class AlbumInfo(
    val albumId: Long = 0,
    val albumName: String = "",
    val artistId: Long? = 0,
    val artistName: String,
    val numberOfSongs: Int,
    val coverArt: String?,
    @DrawableRes val defaultAlbumArtRes: Int = R.drawable.ic_album
) {


    var tracksList: MutableList<Track> = mutableListOf()
    var artistsList: MutableList<ArtistInfo> = mutableListOf()

    object Builder {
        var albumId: Long = 0
        var albumName: String = ""
        var artistId: Long = 0
        var artistName: String = ""
        val numberOfSongs: Int = 0
        var coverArt: String? = null
        var defaultAlbumArtRes: Int = R.drawable.ic_album


        fun albumId(id: Long): Builder {
            albumId = id
            return this
        }

        fun albumName(name: String): Builder {
            albumName = name
            return this
        }

        fun artistName(name: String): Builder {
            artistName = name
            return this
        }

        fun artistId(id: Long): Builder {
            artistId = id
            return this
        }

        fun coverArt(name: String): Builder {
            coverArt = name
            return this

        }

        fun setDefaultAlbumArtRes(@DrawableRes defaultAlbumArtRes: Int) =
            apply { this.defaultAlbumArtRes = defaultAlbumArtRes }

        // Build method to return the final AlbumInfo object
        fun build(): AlbumInfo {
            val albumInfo = AlbumInfo(
                albumId = albumId,
                albumName = albumName,
                artistId = artistId,
                artistName = artistName,
                numberOfSongs = numberOfSongs,
                coverArt = coverArt,
                defaultAlbumArtRes = defaultAlbumArtRes
            )
            albumInfo.artistsList.add(
                ArtistInfo(
                    artistId,
                    artistName = artistName,
                    numberOfAlbums = 1,
                    numberOfTracks = numberOfSongs,
                    coverArt = coverArt
                )
            )

            return albumInfo
        }
    }

    fun getArtist(): ArtistInfo {
        val artist = ArtistInfo(
            artistId = artistId ?: 0L,
            artistName = artistName,
            numberOfTracks = 0,
            numberOfAlbums = 0,
            coverArt = coverArt
        )

        artist.albumsList.add(this)
        return artist
    }

    override fun equals(other: Any?): Boolean {
        return albumId == (other as AlbumInfo).albumId
    }

    override fun hashCode(): Int {
        return albumId.hashCode()
    }

}
package com.soethan.melodystream.data.model

import com.soethan.melodystream.R
import com.soethan.melodystream.media.model.Track

data class ArtistInfo(
    var artistId: Long = 0,
    var artistName: String,
    val defaultAlbumArtRes: Int = R.drawable.ic_artist,
    val numberOfAlbums: Int,
    val numberOfTracks: Int,
    val coverArt: String?
) {

    var albumsList: MutableList<AlbumInfo> = mutableListOf()
    var tracksList: MutableList<Track> = mutableListOf()

    object Builder {
        private var artistId: Long = 0
        private var artistName: String = ""
        private var defaultAlbumArtRes: Int = R.drawable.ic_artist
        private var numberOfAlbums: Int = 0
        private var numberOfTracks: Int = 0
        private var coverArt: String? = null

        // Setters for each property in the Builder class
        fun setArtistId(artistId: Long) = apply { this.artistId = artistId }
        fun setArtistName(artistName: String) = apply { this.artistName = artistName }
        fun setDefaultAlbumArtRes(defaultAlbumArtRes: Int) =
            apply { this.defaultAlbumArtRes = defaultAlbumArtRes }

        fun setNumberOfAlbums(numberOfAlbums: Int) = apply { this.numberOfAlbums = numberOfAlbums }
        fun setNumberOfTracks(numberOfTracks: Int) = apply { this.numberOfTracks = numberOfTracks }
        fun setCoverArt(coverArt: String?) = apply { this.coverArt = coverArt }

        // Build method to create the ArtistInfo object
        fun build(): ArtistInfo {
            return ArtistInfo(
                artistId = artistId,
                artistName = artistName,
                defaultAlbumArtRes = defaultAlbumArtRes,
                numberOfAlbums = numberOfAlbums,
                numberOfTracks = numberOfTracks,
                coverArt = coverArt
            )
        }
    }


    override fun equals(other: Any?): Boolean {
        return artistId == (other as ArtistInfo).artistId
    }

    override fun hashCode(): Int {
        return artistId.hashCode()
    }

}



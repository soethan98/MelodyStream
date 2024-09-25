package com.soethan.melodystream.data.repository

import android.content.Context
import android.provider.MediaStore
import com.soethan.melodystream.data.model.ArtistInfo
import com.soethan.melodystream.utils.extensions.getIntValue
import com.soethan.melodystream.utils.extensions.getLongValue
import com.soethan.melodystream.utils.extensions.getStringValue
import com.soethan.melodystream.utils.extensions.queryCursor
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ArtistRepository @Inject constructor(@ApplicationContext val context: Context) :
    IArtistRepository {
    override suspend fun getAllArtists(): List<ArtistInfo> {
        val artists = arrayListOf<ArtistInfo>()
        val uri = MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI
        val projection = arrayOf(
            MediaStore.Audio.Artists._ID,
            MediaStore.Audio.Artists.ARTIST,
            MediaStore.Audio.Artists.NUMBER_OF_TRACKS,
            MediaStore.Audio.Artists.NUMBER_OF_ALBUMS
        )

        context.queryCursor(uri, projection, showErrors = false) { cursor ->
            val id = cursor.getLongValue(MediaStore.Audio.Artists._ID)
            val title =
                cursor.getStringValue(MediaStore.Audio.Artists.ARTIST) ?: MediaStore.UNKNOWN_STRING
            val albumCnt = cursor.getIntValue(MediaStore.Audio.Artists.NUMBER_OF_TRACKS)
            val trackCnt = cursor.getIntValue(MediaStore.Audio.Artists.NUMBER_OF_ALBUMS)
            val artist = ArtistInfo(
                artistId = id,
                artistName = title,
                numberOfAlbums = albumCnt,
                numberOfTracks = trackCnt
            )
            artists.add(artist)
        }

        return artists
    }

    override suspend fun getArtistById() {

    }
}
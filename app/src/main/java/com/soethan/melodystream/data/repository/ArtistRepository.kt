package com.soethan.melodystream.data.repository

import android.content.Context
import android.graphics.Bitmap
import android.provider.MediaStore
import com.soethan.melodystream.data.model.ArtistInfo
import com.soethan.melodystream.media.model.Track
import com.soethan.melodystream.utils.extensions.getIntValue
import com.soethan.melodystream.utils.extensions.getLongValue
import com.soethan.melodystream.utils.extensions.getStringValue
import com.soethan.melodystream.utils.extensions.queryCursor
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ArtistRepository @Inject constructor(
    @ApplicationContext val context: Context,
    val albumRepository: IAlbumRepository,
    val trackRepository: ITrackRepository
) :
    IArtistRepository {
    override suspend fun getAllArtists(): List<ArtistInfo> {
        val tracks = trackRepository.getAllTracks()

        val artistMap = tracks.groupBy { it.artistId }
        val artists = artistMap.map { (artistId, tracks) ->
            val artistInfo = tracks.first().getArtist()
            artistInfo.tracksList = tracks.toMutableList()
            artistInfo.copy(
                numberOfTracks = tracks.size
            )
        }
        return artists


//        val uri = MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI
//        val projection = arrayOf(
//            MediaStore.Audio.Artists._ID,
//            MediaStore.Audio.Artists.ARTIST,
//            MediaStore.Audio.Artists.NUMBER_OF_TRACKS,
//            MediaStore.Audio.Artists.NUMBER_OF_ALBUMS
//        )
//
//        // Retrieve albums by artist name
//
//        context.queryCursor(uri, projection, showErrors = false) { cursor ->
//            val id = cursor.getLongValue(MediaStore.Audio.Artists._ID)
//            val title =
//                cursor.getStringValue(MediaStore.Audio.Artists.ARTIST) ?: MediaStore.UNKNOWN_STRING
//            val albumCnt = cursor.getIntValue(MediaStore.Audio.Artists.NUMBER_OF_TRACKS)
//            val trackCnt = cursor.getIntValue(MediaStore.Audio.Artists.NUMBER_OF_ALBUMS)
//            val artist = ArtistInfo(
//                artistId = id,
//                artistName = title,
//                numberOfAlbums = albumCnt,
//                numberOfTracks = trackCnt
//            )
//            artists.add(artist)
//        }

    }

    override suspend fun getArtistById() {

    }


}
package com.soethan.melodystream.data.repository

import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import com.soethan.melodystream.data.model.AlbumInfo
import com.soethan.melodystream.media.IAlbum
import com.soethan.melodystream.utils.extensions.getIntValue
import com.soethan.melodystream.utils.extensions.getLongValue
import com.soethan.melodystream.utils.extensions.getStringValue
import com.soethan.melodystream.utils.extensions.queryCursor
import com.soethan.melodystream.utils.helpers.isQPlus
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class AlbumRepository @Inject constructor(@ApplicationContext val context: Context) : IAlbum {

    val artworkUri = Uri.parse("content://media/external/audio/albumart")


    override suspend fun getAllAlbums(): List<AlbumInfo> {
        val albumList = mutableListOf<AlbumInfo>()
        val uri = MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI
        val projection = arrayOf(
            MediaStore.Audio.Albums._ID,
            MediaStore.Audio.Albums.ARTIST,
            MediaStore.Audio.Albums.ALBUM,
            MediaStore.Audio.Albums.NUMBER_OF_SONGS
        )

        if (isQPlus()) {
            projection.plus(MediaStore.Audio.Albums.ARTIST_ID)
        }
        // Query MediaStore for album data
        context.queryCursor(
            uri,
            projection,
            selection = null,
            selectionArgs = null,
            showErrors = false
        ) { cursor ->
            val id = cursor.getLongValue(MediaStore.Audio.Albums._ID)
            val albumName =
                cursor.getStringValue(MediaStore.Audio.Albums.ALBUM) ?: MediaStore.UNKNOWN_STRING
            val coverArt = ContentUris.withAppendedId(artworkUri, id).toString()
            val artistName =
                cursor.getStringValue(MediaStore.Audio.Albums.ARTIST) ?: MediaStore.UNKNOWN_STRING

            val trackCnt = cursor.getIntValue(MediaStore.Audio.Albums.NUMBER_OF_SONGS)
            val artistID = if (isQPlus()) {
                cursor.getLongValue(MediaStore.Audio.Albums.ARTIST_ID)
            } else {
                /// TODO : Support for artist name for device below android 10
                null

            }

            if (trackCnt > 0) {
                val album = AlbumInfo(
                    albumId = id,
                    albumName = albumName,
                    artistId = artistID,
                    artistName = artistName,
                    coverArt = coverArt,
                    numberOfSongs = trackCnt
                )
                albumList.add(album)
            }


        }




        return albumList

    }

    override suspend fun getAlbumById(albumId: Long): AlbumInfo {
        TODO("Not yet implemented")
    }


}
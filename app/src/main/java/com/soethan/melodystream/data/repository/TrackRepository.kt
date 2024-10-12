package com.soethan.melodystream.data.repository

import android.content.ContentUris
import android.content.Context
import android.graphics.Bitmap
import android.media.MediaPlayer.TrackInfo
import android.media.ThumbnailUtils
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import android.util.Size
import androidx.core.net.toUri
import com.soethan.melodystream.media.model.Track
import com.soethan.melodystream.utils.extensions.getCoverArtHeight
import com.soethan.melodystream.utils.extensions.getIntValue
import com.soethan.melodystream.utils.extensions.getLongValue
import com.soethan.melodystream.utils.extensions.getStringValue
import com.soethan.melodystream.utils.extensions.queryCursor
import com.soethan.melodystream.utils.helpers.isQPlus
import com.soethan.melodystream.utils.helpers.isRPlus
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import javax.inject.Inject

class TrackRepository @Inject constructor(@ApplicationContext val context: Context) :
    ITrackRepository {
    val artworkUri = Uri.parse("content://media/external/audio/albumart")


    override suspend fun getTrackById(trackID: Long) {
        TODO("Not yet implemented")
    }

    private var selectionClause: String? = "${MediaStore.Audio.AudioColumns.IS_MUSIC} = ?"
    private var selectionArg = arrayOf("1")

    private val sortOrder = "${MediaStore.Audio.AudioColumns.DISPLAY_NAME} ASC"


    override suspend fun getAllTracks(): List<Track> {
        val tracks = arrayListOf<Track>()
        val uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val projection = arrayListOf(
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.DURATION,
            MediaStore.Audio.Media.DATA,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.ALBUM,
            MediaStore.Audio.Media.ALBUM_ID,
            MediaStore.Audio.Media.ARTIST_ID,
            MediaStore.Audio.Media.TRACK,
            MediaStore.Audio.Media.YEAR,
            MediaStore.Audio.Media.DATE_ADDED
        )

        if (isQPlus()) {
            projection.add(MediaStore.Audio.Media.BUCKET_DISPLAY_NAME)
        }
        if (isRPlus()) {
            projection.add(MediaStore.Audio.Media.GENRE)
            projection.add(MediaStore.Audio.Media.GENRE_ID)
        }


        context.queryCursor(
            uri,
            projection = projection.toTypedArray(),
            showErrors = true,
            selection = selectionClause,
            selectionArgs = selectionArg,
            sortOrder = sortOrder
        ) { cursor ->
            val id = cursor.getLongValue(MediaStore.Audio.Media._ID)
            val title = cursor.getStringValue(MediaStore.Audio.Media.TITLE)
            val duration = cursor.getIntValue(MediaStore.Audio.Media.DURATION) / 1000
            val trackId = cursor.getIntValue(MediaStore.Audio.Media.TRACK) % 1000
            val path = cursor.getStringValue(MediaStore.Audio.Media.DATA).orEmpty()

            val artist =
                cursor.getStringValue(MediaStore.Audio.Media.ARTIST) ?: MediaStore.UNKNOWN_STRING
            val folderName = if (isQPlus()) {
                cursor.getStringValue(MediaStore.Audio.Media.BUCKET_DISPLAY_NAME)
                    ?: MediaStore.UNKNOWN_STRING
            } else {
                ""
            }

            val album = cursor.getStringValue(MediaStore.Audio.Media.ALBUM) ?: folderName
            val albumId = cursor.getLongValue(MediaStore.Audio.Media.ALBUM_ID)
            val artistId = cursor.getLongValue(MediaStore.Audio.Media.ARTIST_ID)
            val year = cursor.getIntValue(MediaStore.Audio.Media.YEAR)
            val dateAdded = cursor.getIntValue(MediaStore.Audio.Media.DATE_ADDED)
            val coverUri = ContentUris.withAppendedId(artworkUri, albumId)
            val coverArt = coverUri.toString()

            val genre: String
            val genreId: Long
            if (isRPlus()) {
                genre = cursor.getStringValue(MediaStore.Audio.Media.GENRE).orEmpty()
                genreId = cursor.getLongValue(MediaStore.Audio.Media.GENRE_ID)
            } else {
                genre = ""
                genreId = 0
            }
            if (!title.isNullOrEmpty()) {
                val track = Track(
                    id = 0,
                    mediaStoreId = id,
                    title = title,
                    artist = artist,
                    path = path,
                    duration = duration,
                    album = album,
                    genre = genre,

                    coverArt = coverArt,
                    albumId = albumId,
                    artistId = artistId,
                    genreId = genreId,
                    year = year,
                    playListId = 0
                )

                tracks.add(track)
            }

            Log.i("TrackRepository", "getAllTracks:${artistId} --${artist} ")

        }
        return tracks
    }

    override suspend fun loadTrackCoverArt(track: Track?): Bitmap? {
        if (track == null) {
            return track
        }
        val artworkUri = track.coverArt
        if (artworkUri.startsWith("content://")) {
            try {
                return MediaStore.Images.Media.getBitmap(
                    context.contentResolver,
                    artworkUri.toUri()
                )
            } catch (ignored: Exception) {
            }
        }

        if (isQPlus()) {
            val coverArtHeight = context.resources.getCoverArtHeight()
            val size = Size(coverArtHeight, coverArtHeight)
            if (artworkUri.startsWith("content://")) {
                try {
                    return context.contentResolver.loadThumbnail(artworkUri.toUri(), size, null)
                } catch (ignored: Exception) {
                }
            }

            val path = track.path
            if (path.isNotEmpty() && File(path).exists()) {
                try {
                    return ThumbnailUtils.createAudioThumbnail(File(track.path), size, null)
                } catch (ignored: OutOfMemoryError) {
                } catch (ignored: Exception) {
                }
            }
        }
        return null
    }

}
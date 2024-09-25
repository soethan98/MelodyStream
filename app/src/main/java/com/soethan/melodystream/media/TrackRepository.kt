package com.soethan.melodystream.media

import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.annotation.WorkerThread
import com.soethan.melodystream.AudioInfo
import com.soethan.melodystream.media.model.Track
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class TrackRepository @Inject constructor(@ApplicationContext val context: Context) : ITrack {
    private var mCursor: Cursor? = null


    private val projection: Array<String> = arrayOf(
        MediaStore.Audio.AudioColumns.DISPLAY_NAME,
        MediaStore.Audio.AudioColumns._ID,
        MediaStore.Audio.AudioColumns.ARTIST,
        MediaStore.Audio.AudioColumns.DATA,
        MediaStore.Audio.AudioColumns.DURATION,
        MediaStore.Audio.AudioColumns.TITLE,
        MediaStore.Audio.AudioColumns.ALBUM
    )

    private var selectionClause: String? = "${MediaStore.Audio.AudioColumns.IS_MUSIC} = ?"
    private var selectionArg = arrayOf("1")

    private val sortOrder = "${MediaStore.Audio.AudioColumns.DISPLAY_NAME} ASC"


    @WorkerThread
    fun getAlbumArt(uri: Uri, context: Context): Bitmap? {
        return try {
            MediaMetadataRetriever().use { mmr ->
                mmr.setDataSource(context, Uri.parse("content://media/external/audio/albumart/3"))
                val data = mmr.embeddedPicture
                data?.let { BitmapFactory.decodeByteArray(it, 0, it.size) }
            }
        } catch (e: Exception) {
            // Consider returning a default bitmap or throwing a custom exception
            null
        }

    }

    @WorkerThread
    override fun getAllTracks(): List<Track> {
        val trackList = mutableListOf<Track>()

        mCursor = context.contentResolver.query(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            projection,
            selectionClause,
            selectionArg,
            sortOrder
        )

        mCursor?.use { cursor ->
            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.AudioColumns._ID)
            val displayNameColumn =
                cursor.getColumnIndexOrThrow(MediaStore.Audio.AudioColumns.DISPLAY_NAME)
            val artistIDColumn =
                cursor.getColumnIndexOrThrow(MediaStore.Audio.AudioColumns.ARTIST_ID)
            val artistNameColumn =
                cursor.getColumnIndexOrThrow(MediaStore.Audio.AudioColumns.ARTIST)
            val albumIDColumn =
                cursor.getColumnIndexOrThrow(MediaStore.Audio.AudioColumns.ALBUM_ID)
            val albumNameColumn =
                cursor.getColumnIndexOrThrow(MediaStore.Audio.AudioColumns.ALBUM)
            val dataColumn =
                cursor.getColumnIndexOrThrow(MediaStore.Audio.AudioColumns.DATA)
            val durationColumn =
                cursor.getColumnIndexOrThrow(MediaStore.Audio.AudioColumns.DURATION)
            val titleColumn =
                cursor.getColumnIndexOrThrow(MediaStore.Audio.AudioColumns.TITLE)

            cursor.apply {
                if (count == 0) {
                    Log.e("Cursor", "getCursorData: Cursor is Empty")
                } else {
                    while (cursor.moveToNext()) {

                        val displayName = getString(displayNameColumn)
                        val id = getLong(idColumn)
                        val artistID = getLong(artistIDColumn)
                        val artistName = getString(artistNameColumn)
                        val data = getString(dataColumn)
                        val duration = getInt(durationColumn)
                        val title = getString(titleColumn)
                        val albumID = getLong(albumIDColumn)
                        val albumName = getString(albumNameColumn)
                        val uri = ContentUris.withAppendedId(
                            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                            id
                        )
                        val albumArtCover = getAlbumArt(uri, context = context)
                        trackList += Track(
                            uri, displayName, id, artistID, artistName, data, duration, title,
                            albumArtCover = albumArtCover,
                            albumName = albumName,
                            albumId = albumID
                        )

                    }
                }
            }
        }
        return trackList
    }

}
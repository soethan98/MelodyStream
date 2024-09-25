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
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ContentResolverHelper @Inject constructor(@ApplicationContext val context: Context) {
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
    fun getAudioData(): List<AudioInfo> {
        return getCursorData()
    }

    private fun getCursorData(): MutableList<AudioInfo> {
        val audioList = mutableListOf<AudioInfo>()

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
            val artistColumn =
                cursor.getColumnIndexOrThrow(MediaStore.Audio.AudioColumns.ARTIST)
            val dataColumn =
                cursor.getColumnIndexOrThrow(MediaStore.Audio.AudioColumns.DATA)
            val durationColumn =
                cursor.getColumnIndexOrThrow(MediaStore.Audio.AudioColumns.DURATION)
            val titleColumn =
                cursor.getColumnIndexOrThrow(MediaStore.Audio.AudioColumns.TITLE)
            val albumNameColumn =
                cursor.getColumnIndexOrThrow(MediaStore.Audio.AudioColumns.ALBUM)

            cursor.apply {
                if (count == 0) {
                    Log.e("Cursor", "getCursorData: Cursor is Empty")
                } else {
                    while (cursor.moveToNext()) {

                        val displayName = getString(displayNameColumn)
                        val id = getLong(idColumn)
                        val artist = getString(artistColumn)
                        val data = getString(dataColumn)
                        val duration = getInt(durationColumn)
                        val title = getString(titleColumn)
                        val albumName = getString(albumNameColumn)
                        val uri = ContentUris.withAppendedId(
                            MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI,
                            15
                        )
                        val albumArtCover = getAlbumArt(uri, context = context)
                        audioList += AudioInfo(
                            uri, displayName, id, artist, data, duration, title,
                            albumArtCover = albumArtCover,
                            albumName = albumName
                        )

                    }
                }
            }
        }
        return audioList
    }

    @WorkerThread
    fun getAlbumArt(uri: Uri, context: Context): Bitmap? {
        return try {
//            Log.i("GetAlbumArt", "getAlbumArt: $uri")
            MediaMetadataRetriever().use { mmr ->
                mmr.setDataSource(context,uri)
                val data = mmr.embeddedPicture
                data?.let { BitmapFactory.decodeByteArray(it, 0, it.size) }
            }
        } catch (e: Exception) {
            // Consider returning a default bitmap or throwing a custom exception
            null
        }

    }

}
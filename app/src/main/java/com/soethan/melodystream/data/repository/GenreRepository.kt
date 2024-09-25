package com.soethan.melodystream.data.repository

import android.content.Context
import android.provider.MediaStore
import com.soethan.melodystream.data.model.GenreInfo
import com.soethan.melodystream.utils.extensions.getIntValue
import com.soethan.melodystream.utils.extensions.getLongValue
import com.soethan.melodystream.utils.extensions.getStringValue
import com.soethan.melodystream.utils.extensions.queryCursor
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class GenreRepository @Inject constructor(@ApplicationContext val context: Context) :
    IGenreRepository {
    override fun getAllGenres(): List<GenreInfo> {
        val genres = arrayListOf<GenreInfo>()
        val uri = MediaStore.Audio.Genres.EXTERNAL_CONTENT_URI
        val projection = arrayListOf(MediaStore.Audio.Genres._ID, MediaStore.Audio.Genres.NAME)
        context.queryCursor(uri, projection.toTypedArray(), showErrors = true) { cursor ->
            val id = cursor.getLongValue(MediaStore.Audio.Genres._ID)
            val title = cursor.getStringValue(MediaStore.Audio.Genres.NAME)

            if (!title.isNullOrEmpty()) {
                val genre = GenreInfo(id = id, name = title)
                genres.add(genre)
            }
        }

        return genres
    }
}
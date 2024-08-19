package com.soethan.melodystream.data.repository

import android.content.Context
import android.net.Uri
import com.soethan.melodystream.Audio
import com.soethan.melodystream.ContentResolverHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AudioRepository @Inject constructor(
    private val contentResolverHelper: ContentResolverHelper
) {
    suspend fun getAudioData(): List<Audio> = withContext(Dispatchers.IO) {
        contentResolverHelper.getAudioData()
    }

    suspend fun loadCoverBitmap(context:Context,uri:Uri){
        return withContext(Dispatchers.IO) {
            contentResolverHelper.getAlbumArt(context = context, uri = uri)
        }
    }
}
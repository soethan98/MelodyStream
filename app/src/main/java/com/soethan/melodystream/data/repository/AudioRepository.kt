package com.soethan.melodystream.data.repository

import android.content.Context
import android.net.Uri
import com.soethan.melodystream.media.ContentResolverHelper
import com.soethan.melodystream.data.mapper.AudioMapper
import com.soethan.melodystream.data.model.SongInfo
import com.soethan.melodystream.media.IAlbum
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AudioRepository @Inject constructor(
    private val contentResolverHelper: ContentResolverHelper,
    private val albumRepository: IAlbum,
    private val audioMapper: AudioMapper,
    private val artistRepository: IArtistRepository
) {
    suspend fun getAudioData(): List<SongInfo> = withContext(Dispatchers.IO) {
        val audioList = contentResolverHelper.getAudioData()
        val albumList = artistRepository.getAllArtists()

         audioList.map { audioMapper.toSong(it) }
    }

    suspend fun loadCoverBitmap(context: Context, uri: Uri) {
        return withContext(Dispatchers.IO) {
            contentResolverHelper.getAlbumArt(context = context, uri = uri)
        }
    }
}
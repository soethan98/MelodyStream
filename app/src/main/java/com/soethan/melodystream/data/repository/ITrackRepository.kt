package com.soethan.melodystream.data.repository

import android.graphics.Bitmap
import com.soethan.melodystream.media.model.Track

interface ITrackRepository {
    suspend fun getTrackById(trackID:Long)
    suspend fun getAllTracks() : List<Track>

    suspend fun loadTrackCoverArt(track:Track?) : Bitmap?
}
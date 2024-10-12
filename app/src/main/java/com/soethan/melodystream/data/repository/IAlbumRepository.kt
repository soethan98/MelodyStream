package com.soethan.melodystream.data.repository

import com.soethan.melodystream.data.model.AlbumInfo

interface IAlbumRepository {
    suspend fun getAllAlbums() : List<AlbumInfo>
    suspend fun getAlbumById(albumId: Long)
}
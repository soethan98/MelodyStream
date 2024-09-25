package com.soethan.melodystream.data.repository

interface IAlbumRepository {
    suspend fun getAllAlbums()
    suspend fun getAlbumById(albumId: Long)
}
package com.soethan.melodystream.media

import com.soethan.melodystream.data.model.AlbumInfo

interface IAlbum {
    suspend fun getAllAlbums(): List<AlbumInfo>
    suspend fun getAlbumById(albumId: Long): AlbumInfo
}
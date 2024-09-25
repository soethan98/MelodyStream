package com.soethan.melodystream.data.repository

import com.soethan.melodystream.data.model.ArtistInfo

interface IArtistRepository {
    suspend fun getAllArtists() : List<ArtistInfo>
    suspend fun getArtistById()
}
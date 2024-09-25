package com.soethan.melodystream.data.repository

import com.soethan.melodystream.data.model.GenreInfo

interface IGenreRepository {
    fun getAllGenres(): List<GenreInfo>
}
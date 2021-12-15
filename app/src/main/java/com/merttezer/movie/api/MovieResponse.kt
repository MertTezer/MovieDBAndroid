package com.merttezer.movie.api

import com.merttezer.movie.data.MovieModel

data class MovieResponse(
    val results: List<MovieModel>
)
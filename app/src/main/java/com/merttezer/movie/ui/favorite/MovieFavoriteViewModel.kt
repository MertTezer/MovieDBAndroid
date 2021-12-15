package com.merttezer.movie.ui.favorite

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.merttezer.movie.data.dao.FavoriteMovieRepository
import javax.inject.Inject

class MovieFavoriteViewModel @ViewModelInject constructor(
    private val repository: FavoriteMovieRepository
): ViewModel() {

    val movies = repository.getFavoriteMovies()
}
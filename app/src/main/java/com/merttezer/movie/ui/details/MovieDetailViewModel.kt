package com.merttezer.movie.ui.details

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.merttezer.movie.data.dao.FavoriteMovieRepository
import com.merttezer.movie.data.MovieFavoriteModel
import com.merttezer.movie.data.MovieModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDetailViewModel @ViewModelInject constructor(
    private val repository: FavoriteMovieRepository
): ViewModel() {

    fun addFavorite(movie: MovieModel){
        CoroutineScope(Dispatchers.IO).launch {
            repository.addFavorite(
                MovieFavoriteModel(
                    movie.id,
                    movie.title,
                    movie.overview,
                    movie.backdrop_path
                )
            )
        }
    }

    suspend fun checkMovie(id: String) = repository.checkMovie(id)

    fun removeFromFavorite(id: String){
        CoroutineScope(Dispatchers.IO).launch {
            repository.removeFromFavorite(id)
        }
    }
}
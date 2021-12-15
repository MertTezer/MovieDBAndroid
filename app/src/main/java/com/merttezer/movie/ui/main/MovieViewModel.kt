package com.merttezer.movie.ui.main

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.merttezer.movie.data.MovieRepository

class MovieViewModel @ViewModelInject constructor(
    private val repository: MovieRepository,
    @Assisted state: SavedStateHandle
): ViewModel(){
    companion object{
        private const val CURRENT_QUERY = "currentQuery"
        private const val EMPTY_QUERY = ""
    }

    private val currentQuery = state.getLiveData(CURRENT_QUERY, EMPTY_QUERY)
    val movies = currentQuery.switchMap { query ->
        if (!query.isEmpty()){
            repository.getSearchMovie(query)
        }else{
            repository.getNowPlayingMovies()
        }
    }

    fun searchMovies(query : String){
        currentQuery.value = query
    }
}
package com.merttezer.movie.data.dao

import com.merttezer.movie.data.MovieFavoriteModel
import javax.inject.Inject


class FavoriteMovieRepository @Inject constructor(
    private val favoriteMovieDao: FavoriteMovieDao
) {

    suspend fun addFavorite(favoriteModel: MovieFavoriteModel) = favoriteMovieDao.addFavorite(favoriteModel)
    fun getFavoriteMovies() = favoriteMovieDao.getFavoriteMovie()
    suspend fun checkMovie(id: String) = favoriteMovieDao.checkMovie(id)
    suspend fun removeFromFavorite(id: String){
        favoriteMovieDao.removeFromFavorite(id)
    }
}
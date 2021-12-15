package com.merttezer.movie.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.merttezer.movie.data.MovieFavoriteModel

@Dao
interface FavoriteMovieDao {

    @Insert
    suspend fun addFavorite(favoriteModel: MovieFavoriteModel)

    @Query("SELECT * FROM favoriteMovie")
    fun getFavoriteMovie(): LiveData<List<MovieFavoriteModel>>

    @Query("SELECT count(*) FROM favoriteMovie WHERE favoriteMovie.movieID = :id")
    suspend fun checkMovie(id: String): Int


    @Query("DELETE FROM favoriteMovie WHERE favoriteMovie.movieID = :id" )
    suspend fun removeFromFavorite(id: String) : Int





//    @Insert
//    suspend fun addToFavorite(favoriteMovie: FavoriteMovie)
//
//    @Query("SELECT * FROM favorite_movie")
//    fun getFavoriteMovie(): LiveData<List<FavoriteMovie>>
//
//    @Query("SELECT count(*) FROM favorite_movie WHERE favorite_movie.id_movie = :id")
//    suspend fun checkMovie(id: String): Int
//
//    @Query("DELETE FROM favorite_movie WHERE favorite_movie.id_movie = :id" )
//    suspend fun removeFromFavorite(id: String) : Int


}
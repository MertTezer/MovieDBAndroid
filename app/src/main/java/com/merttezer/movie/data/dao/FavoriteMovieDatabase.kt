package com.merttezer.movie.data.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.merttezer.movie.data.MovieFavoriteModel
import com.merttezer.movie.data.dao.FavoriteMovieDao
import com.merttezer.movie.ui.favorite.MovieFavoriteFragment

@Database(
    entities = [MovieFavoriteModel::class],
    version = 1
)
abstract class FavoriteMovieDatabase: RoomDatabase() {
    abstract fun getFavoriteMovieDao(): FavoriteMovieDao
}
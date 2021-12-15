package com.merttezer.movie.di

import android.content.Context
import androidx.room.Room
import com.merttezer.movie.data.dao.FavoriteMovieDatabase
import com.merttezer.movie.api.MovieAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder().baseUrl(MovieAPI.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()

    @Provides
    @Singleton
    fun provideMovieApi(retrofit: Retrofit): MovieAPI = retrofit.create(MovieAPI::class.java)


    @Provides
    @Singleton
    fun provideFavoriteDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(app, FavoriteMovieDatabase::class.java, "MovieDatabase").build()

    @Provides
    @Singleton
    fun provideFavoriteDao(database: FavoriteMovieDatabase) = database.getFavoriteMovieDao()
}
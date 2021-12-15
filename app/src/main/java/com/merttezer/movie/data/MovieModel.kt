package com.merttezer.movie.data

import android.os.Parcelable
import androidx.navigation.NavType
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieModel(

    val id: String,
    val overview: String,
    val backdrop_path: String,
    val title: String
):Parcelable{
    //val baseUrl get() = "https://image.tmdb.org/t/p/w500"  // API NULL DÖNÜYOR

    val baseUrl get() = "https://image.tmdb.org/t/p/original"
}
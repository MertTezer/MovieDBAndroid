package com.merttezer.movie.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Entity(tableName = "favoriteMovie")
@Parcelize
data class MovieFavoriteModel(
    val movieID: String,
    val title: String,
    val overview: String,
    val backdrop_path: String
): Serializable, Parcelable{
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    val baseUrl get() = "https://image.tmdb.org/t/p/original"
}

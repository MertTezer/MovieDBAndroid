package com.merttezer.movie.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.merttezer.movie.api.MovieAPI
import com.merttezer.movie.data.MovieModel
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_PAGE_INDEX = 1

class MoviePagingSource(private val movieAPI: MovieAPI, private val query: String?) :
    PagingSource<Int, MovieModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieModel> {
        return try {
            val position = params.key ?: STARTING_PAGE_INDEX
            val response =
                if (query != null) {
                    movieAPI.searchMovie(query, position)
                } else
                    movieAPI.getPlayingMovies(position)
            val movies = response.results

            LoadResult.Page(
                data = movies,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (movies.isEmpty()) null else position + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieModel>): Int? {
        return 1
    }


}
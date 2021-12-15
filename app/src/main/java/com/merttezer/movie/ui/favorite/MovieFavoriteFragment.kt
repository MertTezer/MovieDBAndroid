package com.merttezer.movie.ui.favorite

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.merttezer.movie.data.MovieFavoriteModel
import com.merttezer.movie.data.MovieModel
import com.merttezer.movie.R
import com.merttezer.movie.databinding.FavoriteFragmentBinding
import com.merttezer.movie.databinding.MovieFragmentBinding
import com.merttezer.movie.ui.adapter.FavoriteAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFavoriteFragment: Fragment(R.layout.favorite_fragment) {

    private val viewModel by viewModels<MovieFavoriteViewModel>()
    private var binding: MovieFragmentBinding?= null
    private val _binding get() = binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FavoriteFragmentBinding.bind(view)
        val adapter = FavoriteAdapter()

        viewModel.movies.observe(viewLifecycleOwner){
            adapter.setMovieList(it)
            binding.apply {
                RecyclerMovieFavoriteList.setHasFixedSize(true)
                RecyclerMovieFavoriteList.adapter = adapter

            }
        }

//        adapter.setOnItemClickCallback(object : FavoriteAdapter.OnItemClickCallback{
//            override fun onItemClick(favoriteMovie: MovieFavoriteModel) {
//                val movie = MovieModel(
//                    favoriteMovie.movieID,
//                    //favoriteMovie.overview,
//                    favoriteMovie.backdrop_path,
//                    favoriteMovie.title
//                )
//                val action = MovieFavoriteFragmentDirections.actionNavFavoriteToNavDetails(movie)
//                findNavController().navigate(action)
//            }
//
//        })
//    }

        adapter.setOnItemClickCallback(object : FavoriteAdapter.OnItemClickCallback{
            override fun onItemClick(favoriteMovie: MovieFavoriteModel) {
                val movie = MovieModel(
                    favoriteMovie.movieID,
                    favoriteMovie.overview,
                    favoriteMovie.backdrop_path,
                    favoriteMovie.title
                )
                val action = MovieFavoriteFragmentDirections.actionNavFavoriteToNavDetails(movie)
                findNavController().navigate(action)
            }

        })
    }



}
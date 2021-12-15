package com.merttezer.movie.ui.details

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.merttezer.movie.R
import com.merttezer.movie.databinding.MovieDetailFragmentBinding
import com.merttezer.movie.ui.favorite.MovieFavoriteViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class MovieDetailFragment : Fragment(R.layout.movie_detail_fragment) {
    private val args: MovieDetailFragmentArgs by navArgs()
    private val viewModel by viewModels<MovieDetailViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = MovieDetailFragmentBinding.bind(view)

        binding.apply {
            val movie = args.movie

            Glide.with(this@MovieDetailFragment)
                .load("${movie.baseUrl}${movie.backdrop_path}")
                .error(R.drawable.ic_error)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        movieDescriptionDetail.isVisible = true
                        movieNameDetail.isVisible = true

                        return false
                    }

                }).into(moviePosterDetail)


            var isChecked = false
            CoroutineScope(Dispatchers.IO).launch {
                val count = viewModel.checkMovie(movie.id)
                withContext(Dispatchers.Main){
                    if (count > 0){
                        movieFavoriteToggleButton.isChecked = true
                        isChecked = true
                    }else {
                        movieFavoriteToggleButton.isChecked = false
                        isChecked = false
                    }
                }
            }
            movieDescriptionDetail.text = movie.overview
            movieNameDetail.text = movie.title

            movieFavoriteToggleButton.setOnClickListener {
                isChecked = !isChecked
                if (isChecked){
                    viewModel.addFavorite(movie)
                }else{
                    viewModel.removeFromFavorite(movie.id)
                }

                movieFavoriteToggleButton.isChecked = isChecked
            }

        }
    }
}
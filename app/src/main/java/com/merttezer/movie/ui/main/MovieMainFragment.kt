package com.merttezer.movie.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.paging.PagingData
import com.merttezer.movie.data.MovieModel
import com.merttezer.movie.R
import com.merttezer.movie.databinding.MovieFragmentBinding
import com.merttezer.movie.ui.adapter.MovieAdapter

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieMainFragment: Fragment(R.layout.movie_fragment), MovieAdapter.OnItemClick {

    private val viewModel by viewModels<MovieViewModel>()
    private var _binding: MovieFragmentBinding ?= null
    private val binding get() = _binding!!
    val adapter = MovieAdapter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = MovieFragmentBinding.bind(view)


        binding.apply {
            RecyclerMovieList.setHasFixedSize(true)
            RecyclerMovieList.adapter=adapter
        }

        adapter.addLoadStateListener { loadState ->
            binding.apply {
                movieProgressBar.isVisible = loadState.source.refresh is LoadState.Loading
                RecyclerMovieList.isVisible = loadState.source.refresh is LoadState.NotLoading
                movieRetryButton.isVisible = loadState.source.refresh is LoadState.Error
                movieError.isVisible = loadState.source.refresh is LoadState.Error

                if (loadState.source.refresh is LoadState.NotLoading && loadState.append.endOfPaginationReached && adapter.itemCount < 1){
                    RecyclerMovieList.isVisible = false
                }else{
                    movieRetryButton.setOnClickListener{
                        adapter.retry()
                    }
                }

            }

        }
        viewModel.movies.observe(viewLifecycleOwner){
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }

        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.search_menu, menu)

        val searchItem = menu.findItem(R.id.searchButton)
        val searchView = searchItem.actionView as androidx.appcompat.widget.SearchView

        searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null){
                    binding.RecyclerMovieList.scrollToPosition(0)
                    viewModel.searchMovies(query)
                    searchView.clearFocus()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
//                if(newText?.isEmpty()!!){
//                    adapter.submitData(lifecycle, PagingData.empty())
//                }
                return true
            }

        })
    }

    override fun itemClick(movie: MovieModel) {
        val action = MovieMainFragmentDirections.actionNavMovieToNavDetails(movie)
        findNavController().navigate(action)
    }
}



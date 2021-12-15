package com.merttezer.movie.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.merttezer.movie.data.MovieModel
import com.merttezer.movie.R
import com.merttezer.movie.databinding.ItemMovieBinding

class MovieAdapter(private val listener: OnItemClick): PagingDataAdapter<MovieModel, MovieAdapter.MovieViewHolder>(
    COMPARATOR
) {

    inner class MovieViewHolder(private val binding: ItemMovieBinding): RecyclerView.ViewHolder(binding.root){
        init{
            binding.root.setOnClickListener{
                val position= bindingAdapterPosition
                if (position !=RecyclerView.NO_POSITION){
                    val item= getItem(position)
                    if(item != null){
                        listener.itemClick(item)
                    }
                }

            }
        }

        fun bind(movie: MovieModel){
            with(binding){
                Glide.with(itemView)
                    .load("${movie.baseUrl}${movie.backdrop_path}")
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_error)
                    .into(moviePoster)


                movieName.text= movie.title
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem !=null){
            holder.bind(currentItem)
        }
    }

    companion object{
        private val COMPARATOR= object : DiffUtil.ItemCallback<MovieModel>(){
            override fun areItemsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean = oldItem.id==newItem.id


            override fun areContentsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean = oldItem == newItem



        }
    }


    interface  OnItemClick{
        fun itemClick(movie : MovieModel)
    }


}
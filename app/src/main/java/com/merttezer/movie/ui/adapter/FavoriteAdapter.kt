package com.merttezer.movie.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.merttezer.movie.data.MovieFavoriteModel
import com.merttezer.movie.databinding.ItemMovieBinding

class FavoriteAdapter: RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    private lateinit var list : List<MovieFavoriteModel>
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    fun setMovieList(list: List<MovieFavoriteModel>){
        this.list = list
        notifyDataSetChanged()
    }
    inner class FavoriteViewHolder(private val binding: ItemMovieBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(favoriteMovie: MovieFavoriteModel){
            with(binding){
                Glide.with(itemView)
                    .load("${favoriteMovie.baseUrl}${favoriteMovie.backdrop_path}")
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(moviePoster)

                movieName.text= favoriteMovie.title
                binding.root.setOnClickListener {

                    binding.root.setOnClickListener { onItemClickCallback?.onItemClick(favoriteMovie)
                    }
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size



    interface OnItemClickCallback {
        fun onItemClick(favoriteMovie: MovieFavoriteModel)
    }
}
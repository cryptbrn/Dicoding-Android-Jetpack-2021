package com.example.filmify.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.filmify.R
import com.example.filmify.databinding.ItemMoviesBinding
import com.example.filmify.model.Movies
import com.example.filmify.ui.DetailActivity

class FavoritePagedListAdapter : PagedListAdapter<Movies, FavoritePagedListAdapter.FavoriteViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movies>(){
            override fun areItemsTheSame(oldItem: Movies, newItem: Movies): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movies, newItem: Movies): Boolean {
                return oldItem == newItem
            }

        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoritePagedListAdapter.FavoriteViewHolder {
        val binding = ItemMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteViewHolder(binding)

    }


    override fun onBindViewHolder(holder: FavoritePagedListAdapter.FavoriteViewHolder, position: Int) {
        holder.bind(getItem(position) as Movies)
    }

    inner class FavoriteViewHolder(private val binding: ItemMoviesBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(movie: Movies){
            with(binding) {
                if(movie.name!=null){
                    itemTvTitle.text = movie.name
                }
                else {
                    itemTvTitle.text = movie.title
                }
                itemTvDescription.text = movie.overview
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra("id",movie.id)
                    if(movie.name!=null){
                        intent.putExtra("type","tvshow")
                    }
                    else{
                        intent.putExtra("type","movie")
                    }
                    itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w500"+movie.poster_path)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                    .into(itemIvPoster)
            }
        }

    }
}
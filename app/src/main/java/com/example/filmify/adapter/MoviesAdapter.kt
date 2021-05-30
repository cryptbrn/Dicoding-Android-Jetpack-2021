package com.example.filmify.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.filmify.R
import com.example.filmify.data.remote.ApiResponse
import com.example.filmify.databinding.ItemMoviesBinding
import com.example.filmify.ui.DetailActivity

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {
    private var listMovies = ArrayList<ApiResponse.MoviesResponse>()

    fun setMovies(movies: List<ApiResponse.MoviesResponse>?) {
        if (movies == null) return
        this.listMovies.clear()
        this.listMovies.addAll(movies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val binding = ItemMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movie = listMovies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = listMovies.size


    class MoviesViewHolder(private val binding: ItemMoviesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: ApiResponse.MoviesResponse) {
            with(binding) {
                itemTvTitle.text = movie.title
                itemTvDescription.text = movie.overview
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context,DetailActivity::class.java)
                    intent.putExtra("id",movie.id)
                    intent.putExtra("type","movie")
                    itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                        .load("https://image.tmdb.org/t/p/w500"+movie.poster_path)
                        .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                                .error(R.drawable.ic_error))
                        .into(itemIvPoster)
            }
        }
    }
}
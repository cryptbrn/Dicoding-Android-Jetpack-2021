package com.example.filmify.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.filmify.R
import com.example.filmify.data.TvShows.TvShows
import com.example.filmify.databinding.ItemMoviesBinding

class TvShowsAdapter : RecyclerView.Adapter<TvShowsAdapter.TvShowsViewHolder>() {
    private var listTvShow = ArrayList<TvShows>()

    fun setTvShows(tvshows: List<TvShows>?) {
        if (tvshows == null) return
        this.listTvShow.clear()
        this.listTvShow.addAll(tvshows)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowsViewHolder {
        val binding = ItemMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TvShowsViewHolder, position: Int) {
        val tvshow = listTvShow[position]
        holder.bind(tvshow)
    }

    override fun getItemCount(): Int = listTvShow.size


    class TvShowsViewHolder(private val binding: ItemMoviesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tvshow: TvShows) {
            with(binding) {
                itemTvTitle.text = tvshow.title
                itemTvDescription.text = tvshow.description
                Glide.with(itemView.context)
                        .load(tvshow.imagePath)
                        .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                                .error(R.drawable.ic_error))
                        .into(itemIvPoster)
            }
        }
    }
}
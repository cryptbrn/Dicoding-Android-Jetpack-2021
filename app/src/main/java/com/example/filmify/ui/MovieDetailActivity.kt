package com.example.filmify.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.filmify.R
import com.example.filmify.data.Movies
import com.example.filmify.databinding.ActivityDetailBinding
import com.example.filmify.ui.viewModel.MoviesViewModel

class MovieDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[MoviesViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val movieId = extras.getString("movie_id")
            if (movieId != null) {
                val movie = viewModel.getMovies(movieId.toInt())
                setMovie(movie)
            }
        }



    }

    @SuppressLint("SetTextI18n")
    private fun setMovie(movie: Movies.Movies) {

        with(binding){
            detailTvTitle.text = movie.title
            detailTvDescription.text = movie.description
            detailTvLanguage.text = movie.originalLanguage
            detailTvRating.text = "User Rating : " + movie.rating
            detailTvStatus.text = movie.status
            detailTvYear.text = movie.releaseYear

            Glide.with(this@MovieDetailActivity)
                .load(movie.imagePath)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error))
                .into(detailIvPoster)
        }
    }


}
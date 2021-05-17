package com.example.filmify.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.filmify.R
import com.example.filmify.data.Movies
import com.example.filmify.databinding.ActivityDetailBinding
import com.example.filmify.ui.viewModel.DetailViewModel

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[DetailViewModel::class.java]
        val extras = intent.extras
        if (extras != null) {
            val id = extras.getString("id")
            if (id != null) {
                val movie = viewModel.getDetails(id)
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
            supportActionBar?.title = movie.title
            Glide.with(this@DetailActivity)
                    .load(movie.imagePath)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error))
                    .into(detailIvPoster)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
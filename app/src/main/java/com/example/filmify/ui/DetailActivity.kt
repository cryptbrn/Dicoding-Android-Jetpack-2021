package com.example.filmify.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.WindowId
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.filmify.R
import com.example.filmify.data.Movies
import com.example.filmify.data.remote.ApiResponse
import com.example.filmify.databinding.ActivityDetailBinding
import com.example.filmify.ui.viewModel.DetailViewModel
import com.example.filmify.ui.viewModel.TvShowViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    private val viewModel: DetailViewModel by viewModels()
    private lateinit var type: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val extras = intent.extras
        if (extras != null) {
            val id = extras.getInt("id")
            type = extras.getString("type").toString()
            if (id != null && type != null) {
                viewModel.getDetails(id, type)
                detailResponse()
            }
        }
    }

    private fun detailResponse() {
        viewModel.detail.observe({lifecycle}, {
            if(it.success){
                setMovie(it.result!!)
            }
        })
    }

    @SuppressLint("SetTextI18n")
    private fun setMovie(movie: ApiResponse.MoviesResponse) {
        with(binding){
            if(type=="movie"){
                detailTvTitle.text = movie.title
                detailTvYear.text = movie.release_date
                detailTvOriTitle.text = movie.original_title
                supportActionBar?.title = movie.title
            }
            else if(type=="tvshow"){
                detailTvTitle.text = movie.name
                detailTvYear.text = movie.first_air_date
                detailTvOriTitle.text = movie.original_name
                supportActionBar?.title = movie.name
            }
            detailTvDescription.text = movie.overview
            detailTvLanguage.text = movie.original_language
            detailTvRating.text = "User Rating : ${movie.vote_average}"

            Glide.with(this@DetailActivity)
                    .load("https://image.tmdb.org/t/p/w500"+movie.poster_path)
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
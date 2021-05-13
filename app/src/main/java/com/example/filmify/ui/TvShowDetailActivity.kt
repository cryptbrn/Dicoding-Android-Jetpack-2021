package com.example.filmify.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.filmify.R
import com.example.filmify.data.TvShows
import com.example.filmify.databinding.ActivityDetailBinding
import com.example.filmify.ui.viewModel.TvShowViewModel

class TvShowDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[TvShowViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val tvShowId = extras.getString("tv_show_id")
            if (tvShowId != null) {
                val tvShow = viewModel.getTvShows(tvShowId.toInt())
                setTvShow(tvShow)
            }
        }


    }

    private fun setTvShow(tvShow: TvShows.TvShows) {
        with(binding){
            detailTvTitle.text = tvShow.title
            detailTvDescription.text = tvShow.description
            detailTvLanguage.text = tvShow.originalLanguage
            detailTvRating.text = "User Rating : " + tvShow.rating
            detailTvStatus.text = tvShow.status
            detailTvYear.text = tvShow.releaseYear
            supportActionBar?.setTitle(tvShow.title)
            Glide.with(this@TvShowDetailActivity)
                .load(tvShow.imagePath)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
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
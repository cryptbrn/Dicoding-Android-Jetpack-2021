package com.example.filmify.ui

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.filmify.R
import com.example.filmify.databinding.ActivityDetailBinding
import com.example.filmify.model.Movies
import com.example.filmify.ui.viewModel.DetailViewModel
import com.example.filmify.utils.EspressoIdlingResource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    private val viewModel: DetailViewModel by viewModels()
    private lateinit var type: String
    private var id: Int? = null
    private var status: Boolean = false
    private lateinit var data: Movies


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val extras = intent.extras
        if (extras != null) {
            id = extras.getInt("id")
            type = extras.getString("type").toString()
            if (id != null) {
                showProgress(true)
                getStatus()
            }
        }
        onClick()
    }

    private fun onClick() {
        binding.detailIvFav.setOnClickListener {
            if(status){
                viewModel.deleteMovie(data)
            }
            else {
                viewModel.insertMovie(data)
            }
            getStatus()
        }
    }

    private fun getDetails() {
        if(status){
            viewModel.getSavedMovie(id!!)
        }
        else {
            if(getConnectionType()){
                viewModel.getDetails(id!!, type)
            }
            else {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Internet Connection Lost")
                builder.setPositiveButton("REFRESH"){ _, _ ->
                    getDetails()
                }
                val alertDialog: AlertDialog = builder.create()
                alertDialog.setCancelable(false)
                alertDialog.show()
            }
        }
        detailResponse()
    }

    private fun detailResponse() {
        if(status){
            viewModel.data.observe({ lifecycle }, {
                if(it!=null){
                    if(!EspressoIdlingResource.idlingResource.isIdleNow){
                        EspressoIdlingResource.decrement()
                    }
                    data = it
                    setMovie(it)
                }
            })
        }
        else {
            viewModel.detail.observe({ lifecycle }, {
                if(!EspressoIdlingResource.idlingResource.isIdleNow){
                    EspressoIdlingResource.decrement()
                }
                if (it.success) {
                    data = it.result!!
                    setMovie(it.result)
                } else {
                    showProgress(false)
                    Toast.makeText(this, "Can't load detail from Internet", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    private fun getStatus() {
        viewModel.getSavedMovie(id!!)
        viewModel.data.observe({ lifecycle }, {
            status = it != null
            getDetails()
            setButton()
        })
    }

    private fun setButton() {
        if(status){
            binding.detailIvFav.setImageResource(R.drawable.ic_favorite)
            binding.detailIvFav.tag = R.drawable.ic_favorite
        }
        else {
            binding.detailIvFav.setImageResource(R.drawable.ic_favorite_border)
            binding.detailIvFav.tag = R.drawable.ic_favorite_border
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setMovie(movie: Movies) {
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
        showProgress(false)
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

    private fun showProgress(show: Boolean){
        binding.detailProgress.visibility = if(show) View.VISIBLE else View.GONE
        disableTouch(show)
    }

    private fun disableTouch(status: Boolean){
        if(status){
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
            )
        } else {
            window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        }
    }

    private fun getConnectionType(): Boolean {
        var result = false
        val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        cm.run {
            cm.getNetworkCapabilities(cm.activeNetwork)?.run {
                when {
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        result = true
                    }
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        result = true
                    }
                    hasTransport(NetworkCapabilities.TRANSPORT_VPN) -> {
                        result = true
                    }
                }
            }
        }
        return result
    }
}
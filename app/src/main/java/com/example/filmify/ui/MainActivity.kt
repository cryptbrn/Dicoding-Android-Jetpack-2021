package com.example.filmify.ui

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.filmify.R
import com.example.filmify.adapter.HomePagerAdapter
import com.example.filmify.databinding.ActivityMainBinding
import com.example.filmify.ui.viewModel.MoviesViewModel
import com.example.filmify.ui.viewModel.TvShowViewModel
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val moviesViewModel: MoviesViewModel by viewModels()
    private val tvShowsViewModel: TvShowViewModel by viewModels()



    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val adapter= HomePagerAdapter(supportFragmentManager, lifecycle)
        binding.viewPager.adapter = adapter


        TabLayoutMediator(binding.tabLayout, binding.viewPager){ tab, position->
            when(position){
                0 -> {
                    tab.text = getString(R.string.movies)
                }
                1 -> {
                    tab.text = getString(R.string.tv_shows)
                }
            }
        }.attach()
    }

    fun getMoviesViewModels () = moviesViewModel

    fun getTvShowsViewModels () = tvShowsViewModel

    fun disableTouch(status: Boolean){
        if(status){
            window.setFlags(
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
            )
        } else {
            window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        }
    }

    fun getConnectionType(): Boolean {
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
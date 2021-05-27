package com.example.filmify.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.filmify.R
import com.example.filmify.adapter.HomePagerAdapter
import com.example.filmify.databinding.ActivityMainBinding
import com.example.filmify.ui.viewModel.MoviesViewModel
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val moviesViewModel: MoviesViewModel by viewModels()



    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter= HomePagerAdapter(supportFragmentManager,lifecycle)
        binding.viewPager.adapter = adapter


        TabLayoutMediator(binding.tabLayout, binding.viewPager){tab,position->
            when(position){
                0->{
                    tab.text=getString(R.string.movies)
                }
                1->{
                    tab.text=getString(R.string.tv_shows)
                }
            }
        }.attach()
    }

    fun getMoviesViewModels () = moviesViewModel

}
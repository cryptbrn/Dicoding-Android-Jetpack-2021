package com.example.filmify.ui.viewModel

import androidx.lifecycle.ViewModel
import com.example.filmify.data.Movies.Movies
import com.example.filmify.utils.DataDummy

class MoviesViewModel : ViewModel(){
    fun getMovies(): List<Movies> = DataDummy.generateDummyMovies()
}
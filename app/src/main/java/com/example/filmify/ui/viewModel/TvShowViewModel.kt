package com.example.filmify.ui.viewModel

import androidx.lifecycle.ViewModel
import com.example.filmify.data.Movies.Movies
import com.example.filmify.utils.DataDummy

class TvShowViewModel : ViewModel() {
    fun getTvShows(): List<Movies> = DataDummy.generateDummyTvShows()
}
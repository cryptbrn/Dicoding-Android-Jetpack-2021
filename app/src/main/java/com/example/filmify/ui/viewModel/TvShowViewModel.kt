package com.example.filmify.ui.viewModel

import androidx.lifecycle.ViewModel
import com.example.filmify.data.TvShows.TvShows
import com.example.filmify.utils.DataDummy

class TvShowViewModel : ViewModel() {
    fun getTvShows(): List<TvShows> = DataDummy.generateDummyTvShows()
}
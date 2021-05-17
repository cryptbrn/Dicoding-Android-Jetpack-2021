package com.example.filmify.ui.viewModel

import androidx.lifecycle.ViewModel
import com.example.filmify.data.Movies.Movies
import com.example.filmify.utils.DataDummy

class DetailViewModel : ViewModel() {
    private lateinit var movie: Movies

    fun getDetails(id: String): Movies {
        val movieList = arrayListOf<Movies>()
        movieList.addAll(DataDummy.generateDummyMovies())
        movieList.addAll(DataDummy.generateDummyTvShows())
        for (data in movieList){
            if(data.id == id){
                movie = data
                break
            }
        }
        return movie
    }


}
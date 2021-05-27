package com.example.filmify.ui.viewModel

import androidx.lifecycle.ViewModel
import com.example.filmify.data.Movies.Movie
import com.example.filmify.utils.DataDummy

class DetailViewModel : ViewModel() {
    private lateinit var movie: Movie

    fun getDetails(id: String): Movie {
        val movieList = arrayListOf<Movie>()
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
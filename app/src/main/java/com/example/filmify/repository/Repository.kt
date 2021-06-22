
package com.example.filmify.repository

import androidx.paging.LivePagedListBuilder
import com.example.filmify.db.MovieDao
import com.example.filmify.model.ApiResponse
import com.example.filmify.model.Movies
import com.example.filmify.service.ApiService
import com.example.filmify.utils.EspressoIdlingResource
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(private val api : ApiService, private val movieDao: MovieDao) {
    suspend fun getMovies(): Response<ApiResponse.Results> {
        EspressoIdlingResource.increment()
        val response = api.getMovies()
        if(!EspressoIdlingResource.idlingResource.isIdleNow){
            EspressoIdlingResource.decrement()
        }
        return response
    }
    suspend fun getTvShows(): Response<ApiResponse.Results> {
        EspressoIdlingResource.increment()
        val response = api.getTvShows()
        if(!EspressoIdlingResource.idlingResource.isIdleNow){
            EspressoIdlingResource.decrement()
        }
        return response
    }
    suspend fun getMovie(url: String): Response<Movies> {
        EspressoIdlingResource.increment()
        val response = api.getMovie(url)
        if(!EspressoIdlingResource.idlingResource.isIdleNow){
            EspressoIdlingResource.decrement()
        }
        return response
    }
    suspend fun getTvShow(url: String): Response<Movies> {
        EspressoIdlingResource.increment()
        val response = api.getTvShow(url)
        if(!EspressoIdlingResource.idlingResource.isIdleNow){
            EspressoIdlingResource.decrement()
        }
        return response
    }
    suspend fun saveMovie(movie: Movies) {
        EspressoIdlingResource.increment()
        val response = movieDao.insertMovie(movie)
        if(!EspressoIdlingResource.idlingResource.isIdleNow){
            EspressoIdlingResource.decrement()
        }
        return response
    }
    suspend fun deleteMovie(movie: Movies) {
        EspressoIdlingResource.increment()
        val response = movieDao.deleteMovie(movie)
        if(!EspressoIdlingResource.idlingResource.isIdleNow){
            EspressoIdlingResource.decrement()
        }
        return response
    }
    fun getSavedMovies() = LivePagedListBuilder(movieDao.getAllMovies(), 5).build()
    fun getSavedTvShows() = LivePagedListBuilder(movieDao.getAllTvShows(), 5).build()
    fun getSavedMovie(id: Int) = movieDao.getMovie(id)


}
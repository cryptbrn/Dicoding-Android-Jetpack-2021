package com.example.filmify.repository

import com.example.filmify.model.ApiResponse
import com.example.filmify.model.Movies
import com.example.filmify.service.ApiService
import com.example.filmify.utils.EspressoIdlingResource
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val api: ApiService)  {
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
}
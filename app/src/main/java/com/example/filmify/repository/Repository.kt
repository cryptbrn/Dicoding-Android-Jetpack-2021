package com.example.filmify.repository

import com.example.filmify.service.ApiService
import retrofit2.http.Url
import javax.inject.Inject

class Repository @Inject constructor(private val api : ApiService) {
    suspend fun getMovies() = api.getMovies()
    suspend fun getTvShows() = api.getTvShows()
    suspend fun getMovie(url: String) = api.getMovie(url)
    suspend fun getTvShow(url: String) = api.getTvShow(url)

}
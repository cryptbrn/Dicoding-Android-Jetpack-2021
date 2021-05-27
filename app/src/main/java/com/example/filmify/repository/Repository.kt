package com.example.filmify.repository

import com.example.filmify.service.ApiService
import javax.inject.Inject

class Repository @Inject constructor(private val api : ApiService) {
    suspend fun getMovies() = api.getMovies();
    suspend fun getTvShows() = api.getTvShows();

}
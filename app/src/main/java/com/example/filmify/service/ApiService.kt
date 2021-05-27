package com.example.filmify.service

import com.example.filmify.data.remote.ApiResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("movie/popular")
    suspend fun getMovies():
            Response<ApiResponse.Result>

    @GET("tv/popular")
    suspend fun getTvShows():
            Response<ApiResponse.Result>
}
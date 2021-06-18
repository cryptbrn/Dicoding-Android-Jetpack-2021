package com.example.filmify.service

import com.example.filmify.model.ApiResponse
import com.example.filmify.model.Movies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {

    @GET("movie/popular")
    suspend fun getMovies():
            Response<ApiResponse.Results>

    @GET("tv/popular")
    suspend fun getTvShows():
            Response<ApiResponse.Results>

    @GET()
    suspend fun getTvShow(@Url url: String):
            Response<Movies>

    @GET()
    suspend fun getMovie(@Url url: String):
            Response<Movies>
}
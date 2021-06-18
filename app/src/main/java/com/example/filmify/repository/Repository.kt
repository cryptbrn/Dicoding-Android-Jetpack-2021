
package com.example.filmify.repository

import com.example.filmify.db.MovieDao
import com.example.filmify.model.Movies
import com.example.filmify.service.ApiService
import javax.inject.Inject

class Repository @Inject constructor(private val api : ApiService, private val movieDao: MovieDao) {
    suspend fun getMovies() = api.getMovies()
    suspend fun getTvShows() = api.getTvShows()
    suspend fun getMovie(url: String) = api.getMovie(url)
    suspend fun getTvShow(url: String) = api.getTvShow(url)
    suspend fun saveMovie(movie: Movies) = movieDao.insertMovie(movie)
    suspend fun deleteMovie(movie: Movies) = movieDao.insertMovie(movie)
    fun getSavedMovies() = movieDao.getAllMovies()
    fun getSavedTvShows() = movieDao.getAllTvShows()


}

package com.example.filmify.repository

import androidx.paging.LivePagedListBuilder
import com.example.filmify.db.MovieDao
import com.example.filmify.model.ApiResponse
import com.example.filmify.model.Movies
import com.example.filmify.service.ApiService
import com.example.filmify.utils.EspressoIdlingResource
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(private val remote : RemoteDataSource, private val movieDao: MovieDao) {
    suspend fun getMovies() = remote.getMovies()
    suspend fun getTvShows() = remote.getTvShows()
    suspend fun getMovie(url: String) = remote.getMovie(url)
    suspend fun getTvShow(url: String) = remote.getTvShow(url)
    suspend fun saveMovie(movie: Movies) = movieDao.insertMovie(movie)
    suspend fun deleteMovie(movie: Movies) = movieDao.deleteMovie(movie)
    fun getSavedMovies() = LivePagedListBuilder(movieDao.getAllMovies(), 5).build()
    fun getSavedTvShows() = LivePagedListBuilder(movieDao.getAllTvShows(), 5).build()
    fun getSavedMovie(id: Int) = movieDao.getMovie(id)


}
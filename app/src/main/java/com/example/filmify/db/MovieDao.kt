package com.example.filmify.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.filmify.model.Movies

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: Movies)

    @Query("SELECT * FROM movies WHERE title IS NOT NULL")
    fun getAllMovies(): LiveData<List<Movies>>

    @Query("SELECT * FROM movies WHERE name IS NOT NULL")
    fun getAllTvShows(): LiveData<List<Movies>>

    @Delete
    suspend fun deleteMovie(movie: Movies)


}
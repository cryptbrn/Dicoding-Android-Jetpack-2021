package com.example.filmify.db

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.example.filmify.model.Movies

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: Movies)

    @Query("SELECT * FROM movies WHERE title IS NOT NULL")
    fun getAllMovies(): DataSource.Factory<Int, Movies>

    @Query("SELECT * FROM movies WHERE name IS NOT NULL")
    fun getAllTvShows(): DataSource.Factory<Int, Movies>

    @Delete
    suspend fun deleteMovie(movie: Movies)

    @Query("SELECT * FROM movies WHERE id=:id")
    fun getMovie(id:Int): LiveData<Movies>

}
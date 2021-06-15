package com.example.filmify.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.filmify.model.Movies

@Dao
interface MoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(movies: Movies)

    @Query("SELECT * FROM movies")
    fun getAllMovies(): LiveData<List<Movies>>

    @Delete
    suspend fun deleteMovie()


}
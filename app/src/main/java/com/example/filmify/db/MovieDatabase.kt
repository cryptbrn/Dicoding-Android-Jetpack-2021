package com.example.filmify.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.filmify.model.Movies

@Database(
    entities = [Movies::class],
    version = 1
)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun getMoviesDao(): MovieDao
}
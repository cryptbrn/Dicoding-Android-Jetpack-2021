package com.example.filmify.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.filmify.model.Movies

@Database(
    entities = [Movies::class],
    version = 1
)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun getMoviesDao(): MovieDao

//    companion object{
//        private var instance: MovieDatabase? = null
//        private var LOCK = Any()
//
//        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
//            instance ?: createDatabase(context).also { instance = it}
//        }
//
//        private fun createDatabase(context: Context) =
//            Room.databaseBuilder(
//                context.applicationContext,
//                MovieDatabase::class.java,
//                "movies_db.db"
//            ).build()
//    }

}
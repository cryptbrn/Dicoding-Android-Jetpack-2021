package com.example.filmify.di.modules

import android.content.Context
import androidx.room.Room
import com.example.filmify.db.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Singleton
    @Provides
    fun provideMovieDatabase(
        @ApplicationContext app: Context,
    ) = Room.databaseBuilder(
        app,
        MovieDatabase::class.java,
        "movies_db.db"
    ).build()

    @Singleton
    @Provides
    fun provideMovieDao(db: MovieDatabase) = db.getMoviesDao()
}
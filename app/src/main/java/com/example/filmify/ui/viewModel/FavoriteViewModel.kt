package com.example.filmify.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.filmify.model.Movies
import com.example.filmify.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    fun getSavedMovies(): LiveData<PagedList<Movies>> {
        return repository.getSavedMovies()
    }

    fun getSavedTvShows(): LiveData<PagedList<Movies>> {
        return repository.getSavedTvShows()
    }
}
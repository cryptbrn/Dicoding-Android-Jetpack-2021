package com.example.filmify.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.filmify.model.ApiResponse
import com.example.filmify.model.Movies
import com.example.filmify.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    val tvShows = LivePagedListBuilder(repository.getSavedTvShows(), 5).build()
    val movies = LivePagedListBuilder(repository.getSavedMovies(), 5).build()
}
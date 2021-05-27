package com.example.filmify.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.filmify.data.Movies.Movie
import com.example.filmify.data.remote.ApiResponse
import com.example.filmify.repository.Repository
import com.example.filmify.utils.DataDummy
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject


@HiltViewModel
class MoviesViewModel @Inject constructor(private val repository: Repository) : ViewModel(){
    private val _movies: MutableLiveData<ApiResponse.Result> = MutableLiveData()
    val movies: LiveData<ApiResponse.Result> = _movies

    fun getMovies() = viewModelScope.launch {
        val response = repository.getMovies()
        _movies.postValue(handleMoviesResponse(response))
    }

    private fun handleMoviesResponse(response: Response<ApiResponse.Result>): ApiResponse.Result? {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return ApiResponse.Result(true,resultResponse.results)
            }
        }
        return ApiResponse.Result(false,null)
    }


}
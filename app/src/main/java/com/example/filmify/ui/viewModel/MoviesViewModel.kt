package com.example.filmify.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.filmify.data.remote.ApiResponse
import com.example.filmify.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject


@HiltViewModel
class MoviesViewModel @Inject constructor(private val repository: Repository) : ViewModel(){
    private val _movies: MutableLiveData<ApiResponse.Results> = MutableLiveData()
    val movies: LiveData<ApiResponse.Results> = _movies

    fun getMovies() = viewModelScope.launch {
        val response = repository.getMovies()
        _movies.postValue(handleMoviesResponse(response))
    }

    private fun handleMoviesResponse(response: Response<ApiResponse.Results>): ApiResponse.Results {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return ApiResponse.Results(true,resultResponse.results)
            }
        }
        return ApiResponse.Results(false,null)
    }


}
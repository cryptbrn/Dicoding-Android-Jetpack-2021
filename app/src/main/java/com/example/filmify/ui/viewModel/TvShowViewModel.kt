package com.example.filmify.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.filmify.model.ApiResponse
import com.example.filmify.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class TvShowViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _tvShows: MutableLiveData<ApiResponse.Results> = MutableLiveData()
    val tvShows: LiveData<ApiResponse.Results> = _tvShows

    fun getTvShows() = viewModelScope.launch {
        val response = repository.getTvShows()
        _tvShows.postValue(handleTvShowsResponse(response))
    }

    private fun handleTvShowsResponse(response: Response<ApiResponse.Results>): ApiResponse.Results {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return ApiResponse.Results(true,resultResponse.results)
            }
        }
        return ApiResponse.Results(false,null)
    }
}
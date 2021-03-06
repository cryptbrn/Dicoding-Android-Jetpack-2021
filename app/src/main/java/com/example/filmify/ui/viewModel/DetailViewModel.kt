package com.example.filmify.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.filmify.model.ApiResponse
import com.example.filmify.model.Movies
import com.example.filmify.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val repository: Repository): ViewModel() {
    private val _detail: MutableLiveData<ApiResponse.Result> = MutableLiveData()
    val detail: LiveData<ApiResponse.Result> = _detail

    lateinit var data: LiveData<Movies>

    fun getDetails(id: Int, type: String){
        if(type=="movie"){
            getMovie(id)
        }
        else if(type=="tvshow"){
            getTvShow(id)
        }
    }

    fun getSavedMovie(id: Int){
        data = repository.getSavedMovie(id)
    }



    private fun getMovie(id: Int) = viewModelScope.launch {
        val response = repository.getMovie("https://api.themoviedb.org/3/movie/$id")
        _detail.postValue(handleDetailResponse(response))
    }

    private fun getTvShow(id: Int) = viewModelScope.launch {
        val response = repository.getTvShow("https://api.themoviedb.org/3/tv/$id")
        _detail.postValue(handleDetailResponse(response))
    }

    fun insertMovie(movie: Movies) = viewModelScope.launch {
        repository.saveMovie(movie)
    }

    fun deleteMovie(movie: Movies) = viewModelScope.launch {
        repository.deleteMovie(movie)
    }

    private fun handleDetailResponse(response: Response<Movies>): ApiResponse.Result {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return ApiResponse.Result(true, Movies(resultResponse.id,resultResponse.original_language,resultResponse.overview,
                        resultResponse.poster_path,resultResponse.release_date,resultResponse.first_air_date,resultResponse.original_name,
                        resultResponse.original_title,resultResponse.title,resultResponse.name,resultResponse.vote_average))
            }
        }
        return ApiResponse.Result(false,null)
    }


}
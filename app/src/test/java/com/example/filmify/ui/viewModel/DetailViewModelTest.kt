package com.example.filmify.ui.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.filmify.CoroutinesRule
import com.example.filmify.model.Movies
import com.example.filmify.repository.Repository
import com.example.filmify.utils.DataDummy
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {
    private lateinit var viewModel: DetailViewModel
    private var dummyMovieDetail: Movies = DataDummy.generateDummyMovies()[0]
    private var dummyTvShowDetail: Movies = DataDummy.generateDummyTvShows()[0]

    @get:Rule
    var mainCoroutineRule = CoroutinesRule.MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    @Before
    fun setup() {
        viewModel = DetailViewModel(repository)
    }

    @ExperimentalCoroutinesApi
    private fun CoroutinesRule.MainCoroutineRule.runBlockingTest(block: suspend () -> Unit) =
        this.testDispatcher.runBlockingTest {
            block()
        }


    @Test
    fun getMovieDetails() = mainCoroutineRule.runBlockingTest {
        val response : Response<Movies> = Response.success(DataDummy.generateDummyMovies()[0])
        `when`(repository.getMovie("https://api.themoviedb.org/3/movie/1")).thenReturn(response)
        viewModel.getDetails(1,"movie")
        verify(repository).getMovie("https://api.themoviedb.org/3/movie/1")
        val detail = viewModel.detail
        assertNotNull(detail)
        assertEquals(detail.value!!.result!!.id, dummyMovieDetail.id)
        assertEquals(detail.value!!.result!!.original_language, dummyMovieDetail.original_language)
        assertEquals(detail.value!!.result!!.overview, dummyMovieDetail.overview)
        assertEquals(detail.value!!.result!!.poster_path, dummyMovieDetail.poster_path)
        assertEquals(detail.value!!.result!!.release_date, dummyMovieDetail.release_date)
        assertEquals(detail.value!!.result!!.original_title, dummyMovieDetail.original_title)
        assertEquals(detail.value!!.result!!.title, dummyMovieDetail.title)
        assertEquals(detail.value!!.result!!.vote_average, dummyMovieDetail.vote_average, 0.0)
    }


    @Test
    fun getTvShowDetails() = mainCoroutineRule.runBlockingTest {
        val response : Response<Movies> = Response.success(DataDummy.generateDummyTvShows()[0])
        `when`(repository.getTvShow("https://api.themoviedb.org/3/tv/1")).thenReturn(response)
        viewModel.getDetails(1,"tvshow")
        verify(repository).getTvShow("https://api.themoviedb.org/3/tv/1")
        val detail = viewModel.detail
        assertNotNull(detail)
        assertEquals(detail.value!!.result!!.id, dummyTvShowDetail.id)
        assertEquals(detail.value!!.result!!.original_language, dummyTvShowDetail.original_language)
        assertEquals(detail.value!!.result!!.overview, dummyTvShowDetail.overview)
        assertEquals(detail.value!!.result!!.poster_path, dummyTvShowDetail.poster_path)
        assertEquals(detail.value!!.result!!.first_air_date, dummyTvShowDetail.first_air_date)
        assertEquals(detail.value!!.result!!.original_name, dummyTvShowDetail.original_name)
        assertEquals(detail.value!!.result!!.name, dummyTvShowDetail.name)
        assertEquals(detail.value!!.result!!.vote_average, dummyTvShowDetail.vote_average, 0.0)
    }

    @Test
    fun insertMovie() = mainCoroutineRule.runBlockingTest {
        val data: MutableList<Movies> = mutableListOf()
        `when`(repository.saveMovie(dummyMovieDetail)).then {
            data.add(dummyMovieDetail)
        }
        viewModel.insertMovie(dummyMovieDetail)
        verify(repository).saveMovie(dummyMovieDetail)
        assertNotNull(data)
        assertEquals(data.size,1)
    }

    @Test
    fun deleteMovie() = mainCoroutineRule.runBlockingTest {
        val data: MutableList<Movies> = mutableListOf(dummyMovieDetail)
        `when`(repository.deleteMovie(dummyMovieDetail)).then {
            data.remove(dummyMovieDetail)
        }
        viewModel.deleteMovie(dummyMovieDetail)
        verify(repository).deleteMovie(dummyMovieDetail)
        assertNotNull(data)
        assertEquals(data.size,0)
    }

    @Test
    fun getSavedMovie() {
        val response = MutableLiveData<Movies>()
        response.value = DataDummy.generateDummyMovies()[0]
        `when`(repository.getSavedMovie(1)).thenReturn(response)
        viewModel.getSavedMovie(1)
        val data = viewModel.data
        verify(repository).getSavedMovie(1)
        assertNotNull(data)
        assertEquals(dummyMovieDetail.title, data.value!!.title)
    }

}
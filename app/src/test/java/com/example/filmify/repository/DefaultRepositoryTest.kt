package com.example.filmify.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.filmify.CoroutinesRule
import com.example.filmify.data.remote.ApiResponse
import com.example.filmify.service.ApiService
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
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DefaultRepositoryTest{
    private lateinit var repository: Repository
    private var dummyMovieDetail: ApiResponse.MoviesResponse = DataDummy.generateDummyMovies()[0]
    private var dummyTvShowDetail: ApiResponse.MoviesResponse = DataDummy.generateDummyTvShows()[0]

    @get:Rule
    var mainCoroutineRule = CoroutinesRule.MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var api: ApiService

    @Before
    fun setup() {
        repository = Repository(api)
    }

    @ExperimentalCoroutinesApi
    private fun CoroutinesRule.MainCoroutineRule.runBlockingTest(block: suspend () -> Unit) =
        this.testDispatcher.runBlockingTest {
            block()
        }

    @Test
    fun getMovies() = mainCoroutineRule.runBlockingTest {
        val response : Response<ApiResponse.Results> = Response.success(
            ApiResponse.Results(true,
                DataDummy.generateDummyMovies()))

        Mockito.`when`(api.getMovies()).thenReturn(response)
        val movies = repository.getMovies()
        Mockito.verify(api).getMovies()
        assertNotNull(movies)
        assertEquals(true,movies.isSuccessful)
        assertEquals(10, movies.body()!!.results!!.size)
    }

    @Test
    fun getTvShows() = mainCoroutineRule.runBlockingTest {
        val response : Response<ApiResponse.Results> = Response.success(
            ApiResponse.Results(true,
                DataDummy.generateDummyTvShows()))
        Mockito.`when`(api.getTvShows()).thenReturn(response)
        val tvShows = repository.getTvShows()
        Mockito.verify(api).getTvShows()
        assertEquals(true,tvShows.isSuccessful)
        assertEquals(10, tvShows.body()!!.results!!.size)
    }


    @Test
    fun getMovie() = mainCoroutineRule.runBlockingTest {
        val response : Response<ApiResponse.MoviesResponse> = Response.success(DataDummy.generateDummyMovies()[0])
        Mockito.`when`(api.getMovie("https://api.themoviedb.org/3/movie/1")).thenReturn(response)
        val movie = repository.getMovie("https://api.themoviedb.org/3/movie/1")
        Mockito.verify(api).getMovie("https://api.themoviedb.org/3/movie/1")
        assertNotNull(movie)
        assertEquals(true,movie.isSuccessful)
        assertEquals(movie.body()!!.id, dummyMovieDetail.id)
        assertEquals(movie.body()!!.original_language, dummyMovieDetail.original_language)
        assertEquals(movie.body()!!.overview, dummyMovieDetail.overview)
        assertEquals(movie.body()!!.poster_path, dummyMovieDetail.poster_path)
        assertEquals(movie.body()!!.release_date, dummyMovieDetail.release_date)
        assertEquals(movie.body()!!.original_title, dummyMovieDetail.original_title)
        assertEquals(movie.body()!!.title, dummyMovieDetail.title)
        assertEquals(movie.body()!!.vote_average, dummyMovieDetail.vote_average, 0.0)
    }


    @Test
    fun getTvShow() = mainCoroutineRule.runBlockingTest {
        val response : Response<ApiResponse.MoviesResponse> = Response.success(DataDummy.generateDummyTvShows()[0])
        Mockito.`when`(api.getTvShow("https://api.themoviedb.org/3/tv/1")).thenReturn(response)
        val tvShow = repository.getTvShow("https://api.themoviedb.org/3/tv/1")
        Mockito.verify(api).getTvShow("https://api.themoviedb.org/3/tv/1")
        assertNotNull(tvShow)
        assertEquals(true,tvShow.isSuccessful)
        assertEquals(tvShow.body()!!.id, dummyTvShowDetail.id)
        assertEquals(tvShow.body()!!.original_language, dummyTvShowDetail.original_language)
        assertEquals(tvShow.body()!!.overview, dummyTvShowDetail.overview)
        assertEquals(tvShow.body()!!.poster_path, dummyTvShowDetail.poster_path)
        assertEquals(tvShow.body()!!.release_date, dummyTvShowDetail.release_date)
        assertEquals(tvShow.body()!!.original_title, dummyTvShowDetail.original_title)
        assertEquals(tvShow.body()!!.title, dummyTvShowDetail.title)
        assertEquals(tvShow.body()!!.vote_average, dummyTvShowDetail.vote_average, 0.0)
    }

}
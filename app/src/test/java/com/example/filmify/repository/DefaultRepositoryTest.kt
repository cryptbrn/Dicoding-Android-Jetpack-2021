package com.example.filmify.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.filmify.CoroutinesRule
import com.example.filmify.db.MovieDao
import com.example.filmify.model.ApiResponse
import com.example.filmify.model.Movies
import com.example.filmify.service.ApiService
import com.example.filmify.utils.DataDummy
import com.example.filmify.utils.LiveDataTestUtil
import com.example.filmify.utils.PagedListUtil
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DefaultRepositoryTest{
    private lateinit var repository: Repository
    private var dummyMoviesDetail = DataDummy.generateDummyMovies()
    private var dummyTvShowsDetail = DataDummy.generateDummyTvShows()
    private var dummyMovieDetail: Movies = DataDummy.generateDummyMovies()[0]
    private var dummyTvShowDetail: Movies = DataDummy.generateDummyTvShows()[0]

    @get:Rule
    var mainCoroutineRule = CoroutinesRule.MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var remote: RemoteDataSource

    @Mock
    private lateinit var dao: MovieDao

    @Before
    fun setup() {
        repository = Repository(remote, dao)
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

        `when`(remote.getMovies()).thenReturn(response)
        val movies = repository.getMovies()
        verify(remote).getMovies()
        assertNotNull(movies)
        assertEquals(true,movies.isSuccessful)
        assertEquals(10, movies.body()!!.results!!.size)
    }

    @Test
    fun getTvShows() = mainCoroutineRule.runBlockingTest {
        val response : Response<ApiResponse.Results> = Response.success(
            ApiResponse.Results(true,
                DataDummy.generateDummyTvShows()))
        `when`(remote.getTvShows()).thenReturn(response)
        val tvShows = repository.getTvShows()
        verify(remote).getTvShows()
        assertEquals(true,tvShows.isSuccessful)
        assertEquals(10, tvShows.body()!!.results!!.size)
    }


    @Test
    fun getMovie() = mainCoroutineRule.runBlockingTest {
        val response : Response<Movies> = Response.success(DataDummy.generateDummyMovies()[0])
        `when`(remote.getMovie("https://api.themoviedb.org/3/movie/1")).thenReturn(response)
        val movie = repository.getMovie("https://api.themoviedb.org/3/movie/1")
        verify(remote).getMovie("https://api.themoviedb.org/3/movie/1")
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
        val response : Response<Movies> = Response.success(DataDummy.generateDummyTvShows()[0])
        `when`(remote.getTvShow("https://api.themoviedb.org/3/tv/1")).thenReturn(response)
        val tvShow = repository.getTvShow("https://api.themoviedb.org/3/tv/1")
        verify(remote).getTvShow("https://api.themoviedb.org/3/tv/1")
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

    @Test
    fun saveMovie() = mainCoroutineRule.runBlockingTest {
        val data: MutableList<Movies> = mutableListOf()
        `when`(dao.insertMovie(dummyMovieDetail)).then {
            data.add(dummyMovieDetail)
        }
        repository.saveMovie(dummyMovieDetail)
        verify(dao).insertMovie(dummyMovieDetail)
        assertNotNull(data)
        assertEquals(data.size,1)
    }

    @Test
    fun deleteMovie() = mainCoroutineRule.runBlockingTest {
        val data: MutableList<Movies> = mutableListOf(dummyMovieDetail)
        `when`(dao.deleteMovie(dummyMovieDetail)).then {
            data.remove(dummyMovieDetail)
        }
        repository.deleteMovie(dummyMovieDetail)
        verify(dao).deleteMovie(dummyMovieDetail)
        assertNotNull(data)
        assertEquals(data.size,0)
    }

    @Test
    fun getSavedMovies() {
        val response = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, Movies>
        `when`(dao.getAllMovies()).thenReturn(response)
        repository.getSavedMovies()
        val movies = PagedListUtil.mockPagedList(DataDummy.generateDummyMovies())
        verify(dao).getAllMovies()
        assertNotNull(movies)
        assertEquals(dummyMoviesDetail.size, movies.size)
    }

    @Test
    fun getSavedTvShows() {
        val response = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, Movies>
        `when`(dao.getAllTvShows()).thenReturn(response)
        repository.getSavedTvShows()
        val tvShows = PagedListUtil.mockPagedList(DataDummy.generateDummyTvShows())
        verify(dao).getAllTvShows()
        assertNotNull(tvShows)
        assertEquals(dummyTvShowsDetail.size, tvShows.size)
    }

    @Test
    fun getSavedMovie() {
        val response = MutableLiveData<Movies>()
        response.value = DataDummy.generateDummyMovies()[0]
        `when`(dao.getMovie(1)).thenReturn(response)
        val movie = LiveDataTestUtil.getValue(repository.getSavedMovie(1))
        verify(dao).getMovie(1)
        assertNotNull(movie)
        assertEquals(dummyMovieDetail.title, movie.title)
    }

}
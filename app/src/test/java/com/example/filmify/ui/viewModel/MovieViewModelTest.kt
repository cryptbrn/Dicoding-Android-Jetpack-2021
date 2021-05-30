package com.example.filmify.ui.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.filmify.CoroutinesRule
import com.example.filmify.data.remote.ApiResponse
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
class MovieViewModelTest {
    private lateinit var viewModel: MoviesViewModel

    @get:Rule
    var mainCoroutineRule = CoroutinesRule.MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    @Before
    fun setup() {
        viewModel = MoviesViewModel(repository)
    }

    @ExperimentalCoroutinesApi
    private fun CoroutinesRule.MainCoroutineRule.runBlockingTest(block: suspend () -> Unit) =
        this.testDispatcher.runBlockingTest {
            block()
        }

    @Test
    fun getMovies() = mainCoroutineRule.runBlockingTest {
        val response : Response<ApiResponse.Results> = Response.success(ApiResponse.Results(true,DataDummy.generateDummyMovies()))
        `when`(repository.getMovies()).thenReturn(response)
        viewModel.getMovies()
        verify(repository).getMovies()
        val movies = viewModel.movies
        assertNotNull(movies)
        assertEquals(10, movies.value!!.results!!.size)
    }
}


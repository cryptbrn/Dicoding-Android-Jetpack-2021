package com.example.filmify.ui.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.filmify.CoroutinesRule
import com.example.filmify.model.ApiResponse
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
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest {
    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    var mainCoroutineRule = CoroutinesRule.MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    @Before
    fun setup() {
        viewModel = TvShowViewModel(repository)
    }

    @ExperimentalCoroutinesApi
    private fun CoroutinesRule.MainCoroutineRule.runBlockingTest(block: suspend () -> Unit) =
        this.testDispatcher.runBlockingTest {
            block()
        }

    @Test
    fun getTvShows() = mainCoroutineRule.runBlockingTest {
        val response : Response<ApiResponse.Results> = Response.success(
            ApiResponse.Results(true,
                DataDummy.generateDummyTvShows()))
        Mockito.`when`(repository.getTvShows()).thenReturn(response)
        viewModel.getTvShows()
        Mockito.verify(repository).getTvShows()
        val tvShows = viewModel.tvShows
        assertNotNull(tvShows)
        assertEquals(10, tvShows.value!!.results!!.size)
    }
}
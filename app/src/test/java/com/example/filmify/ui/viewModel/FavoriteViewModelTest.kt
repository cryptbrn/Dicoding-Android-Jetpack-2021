package com.example.filmify.ui.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.DataSource
import androidx.paging.PagedList
import com.example.filmify.model.Movies
import com.example.filmify.repository.Repository
import com.example.filmify.utils.DataDummy
import com.example.filmify.utils.PagedListUtil
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class FavoriteViewModelTest {
    private lateinit var viewModel: FavoriteViewModel
    private var dummyMoviesDetail = DataDummy.generateDummyMovies()
    private var dummyTvShowsDetail = DataDummy.generateDummyTvShows()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    @Before
    fun setup() {
        viewModel = FavoriteViewModel(repository)
    }

    @Test
    fun getSavedMovies() {
        val response = MutableLiveData(PagedListUtil.mockPagedList(DataDummy.generateDummyMovies()))
        `when`(repository.getSavedMovies()).thenReturn(response)
        val movies = viewModel.getSavedMovies()
        verify(repository).getSavedMovies()
        assertNotNull(movies)
        assertEquals(dummyMoviesDetail.size, movies.value!!.size)
    }

    @Test
    fun getSavedTvShows() {
        val response = MutableLiveData(PagedListUtil.mockPagedList(DataDummy.generateDummyTvShows()))
        `when`(repository.getSavedTvShows()).thenReturn(response)
        val tvShows = viewModel.getSavedTvShows()
        verify(repository).getSavedTvShows()
        assertNotNull(tvShows)
        assertEquals(dummyTvShowsDetail.size, tvShows.value!!.size)
    }


}
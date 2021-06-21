package com.example.filmify.ui.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.filmify.model.Movies
import com.example.filmify.repository.Repository
import com.example.filmify.utils.DataDummy
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class FavoriteViewModelTest {
    private lateinit var viewModel: FavoriteViewModel
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var observer: Observer<PagedList<Movies>>

    @Mock
    private lateinit var pagedList: PagedList<Movies>


    @Before
    fun setup() {
        viewModel = FavoriteViewModel(repository)
    }

    @Test
    fun getSavedMovies() {
        val data = pagedList
        `when`(data.size).thenReturn(10)
        val dummyFavoriteMovies = MutableLiveData<PagedList<Movies>>()
        dummyFavoriteMovies.value = data

        `when`(repository.getSavedMovies()).thenReturn(dummyFavoriteMovies)
        val movies = viewModel.getSavedMovies()
        verify(repository).getSavedMovies()
        assertNotNull(movies)
        assertEquals(10, movies.value!!.size)

        viewModel.getSavedMovies().observeForever(observer)
        verify(observer).onChanged(pagedList)
    }

    @Test
    fun getSavedTvShows() {
        val data = pagedList
        `when`(data.size).thenReturn(10)
        val dummyFavoriteTvShows = MutableLiveData<PagedList<Movies>>()
        dummyFavoriteTvShows.value = data

        `when`(repository.getSavedTvShows()).thenReturn(dummyFavoriteTvShows)
        val tvShows = viewModel.getSavedTvShows()
        verify(repository).getSavedTvShows()
        assertNotNull(tvShows)
        assertEquals(10, tvShows.value!!.size)

        viewModel.getSavedTvShows().observeForever(observer)
        verify(observer).onChanged(pagedList)
    }




}
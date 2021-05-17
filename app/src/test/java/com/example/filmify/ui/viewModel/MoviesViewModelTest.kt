package com.example.filmify.ui.viewModel

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class MoviesViewModelTest {
    private lateinit var viewModel: MoviesViewModel

    @Before
    fun setup() {
        viewModel = MoviesViewModel()
    }

    @Test
    fun getMovies() {
        val movies = viewModel.getMovies()
        assertNotNull(movies)
        assertEquals(10,movies.size)
    }
}
package com.example.filmify.ui.viewModel

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test


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
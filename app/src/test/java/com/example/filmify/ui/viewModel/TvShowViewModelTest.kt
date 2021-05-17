package com.example.filmify.ui.viewModel

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class TvShowViewModelTest {
    private lateinit var viewModel: TvShowViewModel

    @Before
    fun setup() {
        viewModel = TvShowViewModel()
    }


    @Test
    fun getTvShows() {
        val tvShows = viewModel.getTvShows()
        assertNotNull(tvShows)
        assertEquals(10,tvShows.size)
    }
}
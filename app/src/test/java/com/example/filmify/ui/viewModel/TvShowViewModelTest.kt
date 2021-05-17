package com.example.filmify.ui.viewModel

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test


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
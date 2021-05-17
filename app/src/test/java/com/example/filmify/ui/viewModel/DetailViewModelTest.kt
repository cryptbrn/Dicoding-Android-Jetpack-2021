package com.example.filmify.ui.viewModel

import com.example.filmify.data.Movies.Movies
import com.example.filmify.utils.DataDummy
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class DetailViewModelTest {
    private lateinit var viewModel: DetailViewModel
    private var dummyDetail: Movies = DataDummy.generateDummyTvShows()[5]

    @Before
    fun setup() {
        viewModel = DetailViewModel()
    }

    @Test
    fun getDetails() {
        val detail = viewModel.getDetails(dummyDetail.id)
        assertNotNull(detail)
        assertEquals(detail.id, dummyDetail.id)
        assertEquals(detail.title, dummyDetail.title)
        assertEquals(detail.description, dummyDetail.description)
        assertEquals(detail.releaseYear, dummyDetail.releaseYear)
        assertEquals(detail.rating, dummyDetail.rating)
        assertEquals(detail.status, dummyDetail.status)
        assertEquals(detail.originalLanguage, dummyDetail.originalLanguage)
        assertEquals(detail.imagePath, dummyDetail.imagePath)


    }
}
package com.example.filmify.ui.fragment

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.filmify.R
import com.example.filmify.ui.MainActivity
import com.example.filmify.utils.DataDummy
import org.junit.Rule
import org.junit.Test


class MovieFragmentTest {
    private val dummyMovies = DataDummy.generateDummyMovies()


    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun loadMovies (){
        onView(withId(R.id.rv_movies))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovies.size))
    }



    @Test
    fun loadMovieDetail(){
        onView(withId(R.id.rv_movies))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    click()
                )
            )
        onView(withId(R.id.detail_tv_title))
            .check(matches(isDisplayed()))
        onView(withId(R.id.detail_tv_title))
            .check(matches(withText(dummyMovies[0].title)))
        onView(withId(R.id.detail_tv_description))
            .check(matches(isDisplayed()))
        onView(withId(R.id.detail_tv_description))
            .check(matches(withText(dummyMovies[0].description)))
        onView(withId(R.id.detail_iv_poster))
            .check(matches(isDisplayed()))
        onView(withId(R.id.detail_tv_language))
            .check(matches(isDisplayed()))
        onView(withId(R.id.detail_tv_language))
            .check(matches(withText(dummyMovies[0].originalLanguage)))
        onView(withId(R.id.detail_tv_rating))
            .check(matches(isDisplayed()))
        onView(withId(R.id.detail_tv_rating))
            .check(matches(withText("User Rating : ${dummyMovies[0].rating}")))
        onView(withId(R.id.detail_tv_status))
            .check(matches(isDisplayed()))
        onView(withId(R.id.detail_tv_status))
            .check(matches(withText(dummyMovies[0].status)))
        onView(withId(R.id.detail_tv_year))
            .check(matches(isDisplayed()))
        onView(withId(R.id.detail_tv_year))
            .check(matches(withText(dummyMovies[0].releaseYear)))
    }
}
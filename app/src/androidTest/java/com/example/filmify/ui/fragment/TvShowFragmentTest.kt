package com.example.filmify.ui.fragment

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.filmify.R
import com.example.filmify.ui.MainActivity
import com.example.filmify.utils.DataDummy
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TvShowFragmentTest {
    private val dummyTvShows = DataDummy.generateDummyTvShows()


    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setup() {
        onView(withId(R.id.view_pager))
            .perform(swipeLeft())
    }

    @Test
    fun loadTvShows (){
        onView(withId(R.id.rv_tv_shows))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_shows))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTvShows.size))
    }

    @Test
    fun loadTvShowsDetail(){
        onView(withId(R.id.rv_tv_shows))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))


        onView(withId(R.id.detail_tv_title))
            .check(matches(isDisplayed()))
        onView(withId(R.id.detail_tv_title))
            .check(matches(ViewMatchers.withText(dummyTvShows[0].title)))
        onView(withId(R.id.detail_tv_description))
            .check(matches(isDisplayed()))
        onView(withId(R.id.detail_tv_description))
            .check(matches(ViewMatchers.withText(dummyTvShows[0].description)))
        onView(withId(R.id.detail_iv_poster))
            .check(matches(isDisplayed()))
        onView(withId(R.id.detail_tv_language))
            .check(matches(isDisplayed()))
        onView(withId(R.id.detail_tv_language))
            .check(matches(ViewMatchers.withText(dummyTvShows[0].originalLanguage)))
        onView(withId(R.id.detail_tv_rating))
            .check(matches(isDisplayed()))
        onView(withId(R.id.detail_tv_rating))
            .check(matches(ViewMatchers.withText("User Rating : ${dummyTvShows[0].rating}")))
        onView(withId(R.id.detail_tv_status))
            .check(matches(isDisplayed()))
        onView(withId(R.id.detail_tv_status))
            .check(matches(ViewMatchers.withText(dummyTvShows[0].status)))
        onView(withId(R.id.detail_tv_year))
            .check(matches(isDisplayed()))
        onView(withId(R.id.detail_tv_year))
            .check(matches(ViewMatchers.withText(dummyTvShows[0].releaseYear)))
    }
}
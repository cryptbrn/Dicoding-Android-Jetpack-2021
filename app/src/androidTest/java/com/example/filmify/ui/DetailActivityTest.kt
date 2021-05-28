package com.example.filmify.ui

import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.filmify.R
import com.example.filmify.utils.DataDummy
import org.junit.Rule
import org.junit.Test


class DetailActivityTest {
    private val dummyTvShows = DataDummy.generateDummyTvShows()
    private val intent = Intent(ApplicationProvider.getApplicationContext(), DetailActivity::class.java)
            .putExtra("id", dummyTvShows[0].id)


    @get:Rule
    var activityRule = ActivityScenarioRule<MainActivity>(intent)



    @Test
    fun checkDetail() {
        onView(withId(R.id.detail_tv_title))
                .check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.detail_tv_title))
                .check(matches(withText(dummyTvShows[0].title)))
        onView(withId(R.id.detail_tv_description))
                .check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.detail_tv_description))
                .check(matches(withText(dummyTvShows[0].description)))
        onView(withId(R.id.detail_iv_poster))
                .check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.detail_tv_language))
                .check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.detail_tv_language))
                .check(matches(withText(dummyTvShows[0].originalLanguage)))
        onView(withId(R.id.detail_tv_rating))
                .check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.detail_tv_rating))
                .check(matches(withText("User Rating : ${dummyTvShows[0].rating}")))
        onView(withId(R.id.detail_tv_ori_title))
                .check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.detail_tv_ori_title))
                .check(matches(withText(dummyTvShows[0].status)))
        onView(withId(R.id.detail_tv_year))
                .check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.detail_tv_year))
                .check(matches(withText(dummyTvShows[0].releaseYear)))

    }






}
package com.example.filmify.ui

import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.filmify.R
import com.example.filmify.data.remote.ApiResponse
import com.example.filmify.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class DetailActivityTest {
    private val dummyTvShows = ApiResponse.MoviesResponse(
        60735,
        "en",
        "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
        "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
        null,
        "2014-10-07",
        "The Flash",
        null,
        null,
        "The Flash",
        7.7
    )
    private val intent = Intent(
        ApplicationProvider.getApplicationContext(),
        DetailActivity::class.java
    )
            .putExtra("id", 60735).putExtra("type", "tvshow")


    @get:Rule
    var activityRule = ActivityScenarioRule<MainActivity>(intent)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }



    @Test
    fun checkDetail() {
        onView(withId(R.id.detail_tv_title))
                .check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.detail_tv_title))
                .check(matches(withText(dummyTvShows.name)))
        onView(withId(R.id.detail_tv_description))
                .check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.detail_tv_description))
                .check(matches(withText(dummyTvShows.overview)))
        onView(withId(R.id.detail_iv_poster))
                .check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.detail_tv_language))
                .check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.detail_tv_language))
                .check(matches(withText(dummyTvShows.original_language)))
        onView(withId(R.id.detail_tv_rating))
                .check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.detail_tv_rating))
                .check(matches(withText("User Rating : ${dummyTvShows.vote_average}")))
        onView(withId(R.id.detail_tv_ori_title))
                .check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.detail_tv_ori_title))
                .check(matches(withText(dummyTvShows.original_name)))
        onView(withId(R.id.detail_tv_year))
                .check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.detail_tv_year))
                .check(matches(withText(dummyTvShows.first_air_date)))

    }






}
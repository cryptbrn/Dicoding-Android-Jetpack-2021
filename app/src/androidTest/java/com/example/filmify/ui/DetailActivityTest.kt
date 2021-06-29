package com.example.filmify.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.filmify.R
import com.example.filmify.model.Movies
import com.example.filmify.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class DetailActivityTest {
    private val dummyMovies = Movies(
        337404,
        "en",
        "In 1970s London amidst the punk rock revolution, a young grifter named Estella is determined to make a name for herself with her designs. She befriends a pair of young thieves who appreciate her appetite for mischief, and together they are able to build a life for themselves on the London streets. One day, Estella’s flair for fashion catches the eye of the Baroness von Hellman, a fashion legend who is devastatingly chic and terrifyingly haute. But their relationship sets in motion a course of events and revelations that will cause Estella to embrace her wicked side and become the raucous, fashionable and revenge-bent Cruella.",
        "/rTh4K5uw9HypmpGslcKd4QfHl93.jpg",
        "2021-05-26",
        null,
        null,
        "Cruella",
        "Cruella",
        null,
        8.6
    )
    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

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
        onView(withId(R.id.rv_movies))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1,
                click()
            ))
        onView(withId(R.id.detail_tv_title))
            .check(matches(isDisplayed()))
        onView(withId(R.id.detail_tv_title))
            .check(matches(withText(dummyMovies.title)))
        onView(withId(R.id.detail_tv_description))
            .check(matches(isDisplayed()))
        onView(withId(R.id.detail_tv_description))
            .check(matches(withText(dummyMovies.overview)))
        onView(withId(R.id.detail_iv_poster))
            .check(matches(isDisplayed()))
        onView(withId(R.id.detail_tv_language))
            .check(matches(isDisplayed()))
        onView(withId(R.id.detail_tv_language))
            .check(matches(withText(dummyMovies.original_language)))
        onView(withId(R.id.detail_tv_rating))
            .check(matches(isDisplayed()))
        onView(withId(R.id.detail_tv_rating))
            .check(matches(withText("User Rating : ${dummyMovies.vote_average}")))
        onView(withId(R.id.detail_tv_ori_title))
            .check(matches(isDisplayed()))
        onView(withId(R.id.detail_tv_ori_title))
            .check(matches(withText(dummyMovies.original_title)))
        onView(withId(R.id.detail_tv_year))
            .check(matches(isDisplayed()))
        onView(withId(R.id.detail_tv_year))

    }


    @Test
    fun checkInsertDeleteFavorite(){
        onView(withId(R.id.rv_movies))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1,
                click()
            ))
        onView(withId(R.id.detail_iv_fav)).perform(click())
        pressBack()
        onView(withId(R.id.favorite_btn)).perform(click())
        onView(withId(R.id.rv_fav_movies))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
                click()
            ))
        onView(withId(R.id.detail_tv_title))
            .check(matches(isDisplayed()))
        onView(withId(R.id.detail_tv_title))
            .check(matches(withText(dummyMovies.title)))
        onView(withId(R.id.detail_tv_description))
            .check(matches(isDisplayed()))
        onView(withId(R.id.detail_tv_description))
            .check(matches(withText(dummyMovies.overview)))
        onView(withId(R.id.detail_iv_poster))
            .check(matches(isDisplayed()))
        onView(withId(R.id.detail_tv_language))
            .check(matches(isDisplayed()))
        onView(withId(R.id.detail_tv_language))
            .check(matches(withText(dummyMovies.original_language)))
        onView(withId(R.id.detail_tv_rating))
            .check(matches(isDisplayed()))
        onView(withId(R.id.detail_tv_rating))
            .check(matches(withText("User Rating : ${dummyMovies.vote_average}")))
        onView(withId(R.id.detail_tv_ori_title))
            .check(matches(isDisplayed()))
        onView(withId(R.id.detail_tv_ori_title))
            .check(matches(withText(dummyMovies.original_title)))
        onView(withId(R.id.detail_tv_year))
            .check(matches(isDisplayed()))
        onView(withId(R.id.detail_tv_year))
            .check(matches(withText(dummyMovies.release_date)))
        onView(withId(R.id.detail_iv_fav)).perform(click())
        pressBack()
    }



}
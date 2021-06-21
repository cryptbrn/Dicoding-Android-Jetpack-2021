package com.example.filmify.ui

import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.filmify.R
import com.example.filmify.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FavoriteActivityTest{
    @get:Rule
    var activityRule = ActivityScenarioRule(FavoriteActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun checkViewPager(){
        Espresso.onView(ViewMatchers.withId(R.id.view_pager))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.view_pager))
            .perform(ViewActions.swipeLeft())
        Espresso.onView(ViewMatchers.withId(R.id.view_pager))
            .perform(ViewActions.swipeRight())
    }

    @Test
    fun checkTabLayout(){
        Espresso.onView(ViewMatchers.withId(R.id.tab_layout))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}
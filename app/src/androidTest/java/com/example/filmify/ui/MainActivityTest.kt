package com.example.filmify.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.action.ViewActions.swipeRight
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.filmify.R
import com.example.filmify.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class MainActivityTest {
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
    fun checkViewPager(){
        onView(withId(R.id.view_pager))
            .check(matches(isDisplayed()))
        onView(withId(R.id.view_pager))
            .perform(swipeLeft())
        onView(withId(R.id.view_pager))
            .perform(swipeRight())
    }

    @Test
    fun checkTabLayout(){
        onView(withId(R.id.tab_layout))
            .check(matches(isDisplayed()))
    }
}
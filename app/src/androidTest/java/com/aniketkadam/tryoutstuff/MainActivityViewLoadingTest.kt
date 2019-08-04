package com.aniketkadam.tryoutstuff

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.Thread.sleep
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class MainActivityViewLoadingTest {

    @get:Rule
    val activityTestRule = ActivityTestRule(MainActivity::class.java, false, false)

    @get:Rule
    val animationDisabler = AnimationDisablerRule()

    @Test
    fun checkIfLaunches() {
        activityTestRule.launchActivity(null)
        onView(withText("Shuri")).check(matches(isDisplayed()))
    }

    @Test
    fun clicking_on_an_element_loads_the_detail_view() {
        activityTestRule.launchActivity(null)
        onView(withText("Shuri")).perform(click())
        onView(withContentDescription("Shuri")).check(matches(isDisplayed()))
    }

    @Test
    fun scrolling_to_the_next_view_and_advancing_on_the_next_view_then_clicking_back_takes_you_to_the_point_on_the_first_view_that_the_second_view_was() {
        activityTestRule.launchActivity(null)

        onView(withId(R.id.recyclerView))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<ImageDataViewHolder>(50, scrollTo()),
                RecyclerViewActions.actionOnItemAtPosition<ImageDataViewHolder>(50, click())
            )

        onView(withId(R.id.recyclerView)).perform(
            RecyclerViewActions.actionOnItemAtPosition<ImageDataViewHolder>(
                80,
                scrollTo()
            )
        )
        pressBack()
        onView(withText("Binary")).check(matches(isDisplayed()))
    }

}
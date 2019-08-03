package com.aniketkadam.tryoutstuff

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityViewLoadingTest {

    @get:Rule
    val activityTestRule = ActivityTestRule(MainActivity::class.java, false, false)

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

}
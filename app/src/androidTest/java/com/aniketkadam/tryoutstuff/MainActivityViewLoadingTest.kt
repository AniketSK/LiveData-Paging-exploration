package com.aniketkadam.tryoutstuff

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
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

    @Test
    fun checkIfLaunches() {
        activityTestRule.launchActivity(null)
        onView(withText("Shuri")).check(matches(isDisplayed()))
        sleep(TimeUnit.MILLISECONDS.convert(1, TimeUnit.HOURS))
    }

}
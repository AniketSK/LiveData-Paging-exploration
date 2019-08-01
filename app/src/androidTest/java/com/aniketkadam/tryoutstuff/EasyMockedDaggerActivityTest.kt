package com.aniketkadam.tryoutstuff

import androidx.room.Room
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.aniketkadam.tryoutstuff.data.ImageData
import com.aniketkadam.tryoutstuff.network.ImageApi
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito.`when`


@RunWith(AndroidJUnit4::class)
class EasyMockedDaggerActivityTest {

    @get:Rule
    val rule = EspressoDaggerMockRule()

    @Mock
    lateinit var imageApi: ImageApi

    @get:Rule
    val activityTestRule = ActivityTestRule(MainActivity::class.java, false, false)

    @Test
    fun checkIfLaunches() {

        `when`(imageApi.findImages(anyString())).thenReturn(
            Single.just(
                listOf(
                    ImageData(
                        "1",
                        "Shuri",
                        "https://image.ibb.co/hFrbCp/shuri.jpg"
                    ),
                    ImageData(
                        "2",
                        "A-Bomb",
                        "https://www.superherodb.com/pictures2/portraits/10/100/10060.jpg"
                    )
                )
            )
        )

        activityTestRule.launchActivity(null)
        onView(withText("Hello World!")).check(matches(isDisplayed()))
    }
}
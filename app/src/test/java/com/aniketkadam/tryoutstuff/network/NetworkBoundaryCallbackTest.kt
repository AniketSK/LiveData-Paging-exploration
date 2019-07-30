package com.aniketkadam.tryoutstuff.network

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.aniketkadam.tryoutstuff.data.ImageData
import com.aniketkadam.tryoutstuff.data.ImageDataDao
import com.aniketkadam.tryoutstuff.data.LiveDataTestUtil
import io.reactivex.Single
import io.reactivex.schedulers.TestScheduler
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import java.lang.Thread.sleep
import java.util.concurrent.TimeUnit
import org.mockito.Mockito.`when` as When

class NetworkBoundaryCallbackTest {

    lateinit var networkBoundaryCallback: NetworkBoundaryCallback

    @get:Rule
    val schedulerRule = InstantTaskExecutorRule()

    @Mock
    lateinit var service: ImageApi
    @Mock
    lateinit var db: ImageDataDao

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        networkBoundaryCallback = NetworkBoundaryCallback(service, db)
    }

    @Test
    fun `onZeroItemsLoaded called just once for multiple invocations`() {
        initializeService()
        networkBoundaryCallback.onZeroItemsLoaded()
        networkBoundaryCallback.onZeroItemsLoaded()

        verify(service, times(1)).findImages(ArgumentMatchers.anyString())

    }

    @Test
    fun `onZeroItemsLoaded the data is saved to the database`() {
        initializeService()
        networkBoundaryCallback.onZeroItemsLoaded()
        verify(db, times(1)).insert(ArgumentMatchers.anyList())
    }

    @Test
    fun `onItemAtEndLoaded more data is loaded from the network and saved to the db`() {
        initializeService()
        networkBoundaryCallback.onItemAtEndLoaded(ImageData("someId", "what", "compassionatecoding.com"))
        verify(service, times(1)).findImages(ArgumentMatchers.anyString())
        verify(db, times(1)).insert(ArgumentMatchers.anyList())
    }


    @Test
    fun `onItemAtEndLoaded only loads data once for multiple invocations and completes the initial request`() {

        val scheduler = TestScheduler() // Prevent time from advancing with a test scheduler
        // Delay the response enough that the call wouldn't have finished.
        When(service.findImages(ArgumentMatchers.anyString())).thenReturn(
            Single.just(
                listOf(
                    ImageData(
                        "hi",
                        "first",
                        "www.google.com"
                    )
                )
            ).delay(1, TimeUnit.SECONDS, scheduler)
        )

        networkBoundaryCallback.onItemAtEndLoaded(ImageData("someId", "what", "compassionatecoding.com"))

        assertThat(
            LiveDataTestUtil.getValue(networkBoundaryCallback.networkState),
            equalTo<NetworkState>(NetworkState.Loading)
        ) // Since the delay was called earlier, this should still be loading

        networkBoundaryCallback.onItemAtEndLoaded(
            ImageData(
                "someId",
                "what",
                "compassionatecoding.com"
            )
        ) // Since it's already loading this should do nothing

        verify(service, times(1)).findImages(ArgumentMatchers.anyString())

        // Let time pass and see if it completes after
        scheduler.advanceTimeBy(10, TimeUnit.SECONDS)
        sleep(50) // Fails without a bit of sleep

        assertThat(
            LiveDataTestUtil.getValue(networkBoundaryCallback.networkState),
            equalTo<NetworkState>(NetworkState.Complete)
        )

    }

    private fun initializeService() {

        When(service.findImages(ArgumentMatchers.anyString())).thenReturn(
            Single.just(
                listOf(
                    ImageData(
                        "hi",
                        "first",
                        "www.google.com"
                    )
                )
            )
        )
    }
}
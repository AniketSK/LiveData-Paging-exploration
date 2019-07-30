package com.aniketkadam.tryoutstuff.data

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.LargeTest
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.io.IOException
import org.hamcrest.Matchers.`is` as Is

@LargeTest
@RunWith(RobolectricTestRunner::class)
class ImageDataDaoTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var imageDataDao: ImageDataDao
    private lateinit var db: ImageDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, ImageDatabase::class.java).allowMainThreadQueries().build()
        imageDataDao = db.imageDataDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() = db.close()

    @Test
    fun writeImageAndRead() {
        val sampleData = ImageData("what", "Hello", "www.imagedb.com")

        imageDataDao.insert(sampleData)

        val result: List<ImageData>? = LiveDataTestUtil.getValue(imageDataDao.getImageDataForTest())
        assertThat(sampleData, Is(result?.get(0)))

    }

    @Test
    fun writeImageListandRead() {
        val sampleData: List<ImageData> = (1..10).map { ImageData(it.toString(), "Hello ${it}", "imagedb.com") }
        imageDataDao.insert(sampleData)

        val result: List<ImageData>? = LiveDataTestUtil.getValue(imageDataDao.getImageDataForTest())
        assertThat(sampleData.size, Is(result?.size))
    }
}
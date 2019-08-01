package com.aniketkadam.tryoutstuff.di

import android.content.res.AssetManager
import androidx.test.platform.app.InstrumentationRegistry
import com.aniketkadam.tryoutstuff.data.*
import com.aniketkadam.tryoutstuff.network.ImageApi
import com.aniketkadam.tryoutstuff.network.NetworkBoundaryCallback
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.Module
import dagger.Provides
import io.reactivex.Single
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

@Module
class MainActivityModuleForTest {

    @Provides
    fun provideRepository(
        imageDataDao: ImageDataDao,
        boundaryCallback: NetworkBoundaryCallback
    ) = Repository(imageDataDao, boundaryCallback)

    @Provides
    fun getImageDao(db: ImageDatabase) = db.imageDataDao()

    @Provides
    fun getImageApi(): ImageApi {
        val api = mock(ImageApi::class.java)

        val data: List<ImageData> =
            InstrumentationRegistry.getInstrumentation().context.assets.readAssetsFile("mocks/imagedata.json").let {
                Gson().fromJson(it, object : TypeToken<List<ImageData>>() {}.type)
            }


        `when`(api.findImages(ArgumentMatchers.anyString())).thenAnswer {
            val requested = it.getArgument<String>(0).toInt()
            Single.just(
                data.filter { it.id.toInt() >= requested && it.id.toInt() < requested + REPOSITORY_PREFETCH_DISTANCE }
            )
        }
        return api
    }
}

fun AssetManager.readAssetsFile(fileName: String): String = open(fileName).bufferedReader().use { it.readText() }

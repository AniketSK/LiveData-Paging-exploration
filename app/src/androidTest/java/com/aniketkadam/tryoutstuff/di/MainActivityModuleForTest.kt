package com.aniketkadam.tryoutstuff.di

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import androidx.test.platform.app.InstrumentationRegistry
import com.aniketkadam.tryoutstuff.MainActivity
import com.aniketkadam.tryoutstuff.MainVM
import com.aniketkadam.tryoutstuff.data.*
import com.aniketkadam.tryoutstuff.di.vm.ViewModelFactory
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
import javax.inject.Named

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
            InstrumentationRegistry.getInstrumentation().context.readAssetsFile("mocks/imagedata.json")


        `when`(api.findImages(ArgumentMatchers.anyString())).thenAnswer {
            val requested = it.getArgument<String>(0).toInt()
            Single.just(
                data.filter { it.id.toInt() >= requested && it.id.toInt() < requested + REPOSITORY_PREFETCH_DISTANCE }
            )
        }
        return api
    }

    @Provides
    @Named(ACTIVITY_VM)
    fun getMainVm(
        mainActivity: MainActivity,
        factory: ViewModelFactory
    ): MainVM =
        ViewModelProviders.of(mainActivity, factory).get(MainVM::class.java)

    @Provides
    @Named(FRAGMENT_VM)
    fun provideMainVmForFragments(mainActivity: MainActivity): MainVM =
        ViewModelProviders.of(mainActivity).get(MainVM::class.java)
}

private inline fun <reified T : Any> Context.readAssetsFile(fileName: String): T =
    assets.open(fileName).bufferedReader().use { it.readText() }.let {
        Gson().fromJson(it, object : TypeToken<T>() {}.type)
    }

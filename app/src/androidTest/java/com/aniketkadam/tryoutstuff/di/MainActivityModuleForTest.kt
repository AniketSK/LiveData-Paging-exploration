package com.aniketkadam.tryoutstuff.di

import com.aniketkadam.tryoutstuff.data.ImageData
import com.aniketkadam.tryoutstuff.data.ImageDataDao
import com.aniketkadam.tryoutstuff.data.ImageDatabase
import com.aniketkadam.tryoutstuff.data.Repository
import com.aniketkadam.tryoutstuff.network.ImageApi
import com.aniketkadam.tryoutstuff.network.NetworkBoundaryCallback
import dagger.Module
import dagger.Provides
import io.reactivex.Single
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*

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
        `when`(api.findImages(ArgumentMatchers.anyString())).thenReturn(
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
        return api
    }
}

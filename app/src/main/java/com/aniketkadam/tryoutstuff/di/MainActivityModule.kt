package com.aniketkadam.tryoutstuff.di

import com.aniketkadam.tryoutstuff.data.ImageDataDao
import com.aniketkadam.tryoutstuff.data.ImageDatabase
import com.aniketkadam.tryoutstuff.data.Repository
import com.aniketkadam.tryoutstuff.network.ImageApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MainActivityModule {

    @Provides
    fun provideRepository(imageDataDao: ImageDataDao, imageApi: ImageApi) = Repository(imageDataDao, imageApi)

    @Provides
    fun getImageDao(db: ImageDatabase) = db.imageDataDao()

    @Provides
    fun getImageApi(retrofit: Retrofit): ImageApi = retrofit.create(ImageApi::class.java)
}
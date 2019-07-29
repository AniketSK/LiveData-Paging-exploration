package com.aniketkadam.tryoutstuff.di

import com.aniketkadam.tryoutstuff.data.ImageDataDao
import com.aniketkadam.tryoutstuff.data.ImageDatabase
import com.aniketkadam.tryoutstuff.data.Repository
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @Provides
    fun provideRepository(imageDataDao: ImageDataDao) = Repository(imageDataDao)

    @Provides
    fun getImageDao(db: ImageDatabase) = db.imageDataDao()
}
package com.aniketkadam.tryoutstuff.di

import com.aniketkadam.tryoutstuff.Repository
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @Provides
    fun provideRepository() = Repository()
}
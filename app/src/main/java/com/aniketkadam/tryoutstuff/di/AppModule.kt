package com.aniketkadam.tryoutstuff.di

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module


@Module(includes = [ViewModelModule::class])
abstract class AppModule {

    @Binds
    internal abstract fun provideContext(application: Application): Context

}

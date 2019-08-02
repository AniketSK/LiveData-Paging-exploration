package com.aniketkadam.tryoutstuff.di

import com.aniketkadam.tryoutstuff.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [MainActivityModule::class, MainActivityFragmentsBuilder::class])
    abstract fun bindMainActivity(): MainActivity
}
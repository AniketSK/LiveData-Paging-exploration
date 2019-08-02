package com.aniketkadam.tryoutstuff.di


import com.aniketkadam.tryoutstuff.MainActivity

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderForTest {

    @ContributesAndroidInjector(modules = [MainActivityModuleForTest::class, MainActivityFragmentsBuilder::class])
    abstract fun bindMainActivity(): MainActivity
}
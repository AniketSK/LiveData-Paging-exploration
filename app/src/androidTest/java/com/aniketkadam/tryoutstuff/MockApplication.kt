package com.aniketkadam.tryoutstuff

import com.aniketkadam.tryoutstuff.di.DaggerAppComponentForTest
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class MockApplication : DaggerApplication() {
    private val applicationInjector by lazy {
        DaggerAppComponentForTest.builder().application(this).build()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = applicationInjector

}
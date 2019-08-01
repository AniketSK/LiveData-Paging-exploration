package com.aniketkadam.tryoutstuff

import com.aniketkadam.tryoutstuff.di.DaggerAppComponentForTest
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

/**
 * Used as the application when you want to replace the modules entirely that the app is using.
 * This follows the traditional way of replacing dagger modules for a test by re-implementing all
 * concrete components and modules.
 */
class DaggerStubTestApplication : DaggerApplication() {
    private val applicationInjector by lazy {
        DaggerAppComponentForTest.builder().application(this).build()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = applicationInjector

}
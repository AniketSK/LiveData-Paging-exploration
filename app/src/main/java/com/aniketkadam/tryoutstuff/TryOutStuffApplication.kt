package com.aniketkadam.tryoutstuff

import android.content.Context
import androidx.multidex.MultiDex
import com.aniketkadam.tryoutstuff.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class TryOutStuffApplication : DaggerApplication() {

    private val applicationInjector by lazy {
        DaggerAppComponent.builder().application(this).build()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = applicationInjector

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}
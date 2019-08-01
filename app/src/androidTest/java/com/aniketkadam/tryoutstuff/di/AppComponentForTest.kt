package com.aniketkadam.tryoutstuff.di

import android.app.Application
import com.aniketkadam.tryoutstuff.DaggerStubTestApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBuilderForTest::class,
        DatabaseModuleForTest::class,
        AppModule::class
    ]
)
interface AppComponentForTest : AndroidInjector<DaggerStubTestApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponentForTest
    }
}

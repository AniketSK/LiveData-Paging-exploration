package com.aniketkadam.tryoutstuff.di

import android.app.Application
import com.aniketkadam.tryoutstuff.MockApplication
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
interface AppComponentForTest : AndroidInjector<MockApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): AppComponentForTest.Builder

        fun build(): AppComponentForTest
    }
}

package com.aniketkadam.tryoutstuff.di

import android.app.Application
import com.aniketkadam.tryoutstuff.TryOutStuffApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityBuilder::class,
        DatabaseModule::class,
        NetworkingModule::class] // Any non-abstract class addition here requires reading the comment in the Builder
)
interface AppComponent : AndroidInjector<TryOutStuffApplication> {
    override fun inject(instance: TryOutStuffApplication)

    @Component.Builder
    interface Builder {

        /**
         * Methods for Instrumentation Testing
         * Any non abstract class that's being passed a module to the AppComponent, needs to have a builder
         * defined here so that if you want to mock it with DaggerMock,
         * (note the test rule for it in EspressoDaggerMockRule )
         * Then daggermock can inject the modules here.
         * If new modules are added to this class, they must have corresponding builders so androidTest
         * can continue to work.
         */
        fun databaseModule(databaseModule: DatabaseModule): Builder

        fun networkingModule(networkingModule: NetworkingModule): Builder

        // Standard dagger-android methods from here on
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}
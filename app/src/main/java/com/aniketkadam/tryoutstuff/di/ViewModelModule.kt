package com.aniketkadam.tryoutstuff.di

import androidx.lifecycle.ViewModel
import com.aniketkadam.tryoutstuff.MainVM
import com.aniketkadam.tryoutstuff.di.vm.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Allows adding just a line here for the viewmodel and it's injectable
 * Based on
 * https://github.com/googlesamples/android-architecture-components/blob/17c315a050745c61ae8e79000bc0251305bd148b/GithubBrowserSample/app/src/main/java/com/android/example/github/di/ViewModelModule.kt
 */
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainVM::class)
    abstract fun bindMainViewModel(mainVM: MainVM): ViewModel
}
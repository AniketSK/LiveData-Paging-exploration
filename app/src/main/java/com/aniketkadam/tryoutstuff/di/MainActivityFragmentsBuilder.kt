package com.aniketkadam.tryoutstuff.di

import com.aniketkadam.tryoutstuff.DetailViewFragment
import com.aniketkadam.tryoutstuff.ListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityFragmentsBuilder {

    @ContributesAndroidInjector
    abstract fun bindListFragment(): ListFragment

    @ContributesAndroidInjector
    abstract fun bindDetailFragment(): DetailViewFragment

}
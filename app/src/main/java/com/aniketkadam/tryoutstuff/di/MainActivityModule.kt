package com.aniketkadam.tryoutstuff.di

import androidx.lifecycle.ViewModelProviders
import com.aniketkadam.tryoutstuff.MainActivity
import com.aniketkadam.tryoutstuff.MainVM
import com.aniketkadam.tryoutstuff.data.ImageDataDao
import com.aniketkadam.tryoutstuff.data.ImageDatabase
import com.aniketkadam.tryoutstuff.data.Repository
import com.aniketkadam.tryoutstuff.di.vm.ViewModelFactory
import com.aniketkadam.tryoutstuff.network.ImageApi
import com.aniketkadam.tryoutstuff.network.NetworkBoundaryCallback
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named

const val ACTIVITY_VM = "ACTIVITY_VM"
const val FRAGMENT_VM = "FRAGMENT_VM"

@Module
class MainActivityModule {

    @Provides
    fun provideRepository(
        imageDataDao: ImageDataDao,
        boundaryCallback: NetworkBoundaryCallback
    ) = Repository(imageDataDao, boundaryCallback)

    @Provides
    fun getImageDao(db: ImageDatabase) = db.imageDataDao()

    @Provides
    fun getImageApi(retrofit: Retrofit): ImageApi = retrofit.create(ImageApi::class.java)

    @Provides
    @Named(ACTIVITY_VM)
    fun getMainVm(
        mainActivity: MainActivity,
        factory: ViewModelFactory
    ): MainVM =
        ViewModelProviders.of(mainActivity, factory).get(MainVM::class.java)

    @Provides
    @Named(FRAGMENT_VM)
    fun provideMainVmForFragments(mainActivity: MainActivity): MainVM =
        ViewModelProviders.of(mainActivity).get(MainVM::class.java)
}
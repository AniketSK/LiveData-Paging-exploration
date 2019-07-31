package com.aniketkadam.tryoutstuff.di

import android.content.Context
import androidx.room.Room
import com.aniketkadam.tryoutstuff.data.ImageDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModuleForTest {
    @Singleton
    @Provides
    fun provideDatabase(c: Context): ImageDatabase =
        Room.inMemoryDatabaseBuilder(c, ImageDatabase::class.java)
            .build()
}
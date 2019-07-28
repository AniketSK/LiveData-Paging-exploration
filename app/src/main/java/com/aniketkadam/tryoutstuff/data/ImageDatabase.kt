package com.aniketkadam.tryoutstuff.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(ImageData::class), version = 1)
abstract class ImageDatabase : RoomDatabase() {
    abstract fun imageDataDao(): ImageDataDao
}
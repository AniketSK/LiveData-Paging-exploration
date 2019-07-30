package com.aniketkadam.tryoutstuff.data

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable

@Dao
interface ImageDataDao {

    @Insert
    fun insert(imageData: ImageData): Completable

    @Insert
    fun insert(imageDataList: List<ImageData>): Completable

    @Query("SELECT * FROM imagedata")
    fun getAllImageData(): DataSource.Factory<Int, ImageData>

    @Query("SELECT * FROM imagedata")
    fun getImageDataForTest(): LiveData<List<ImageData>>
}
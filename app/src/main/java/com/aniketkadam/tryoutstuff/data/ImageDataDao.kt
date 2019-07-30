package com.aniketkadam.tryoutstuff.data

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ImageDataDao {

    @Insert
    fun insert(imageData: ImageData)

    @Insert
    fun insert(imageDataList: List<ImageData>)

    @Query("SELECT * FROM imagedata")
    fun getAllImageData(): DataSource.Factory<Int, ImageData>

    @Query("SELECT * FROM imagedata")
    fun getImageDataForTest(): LiveData<List<ImageData>>
}
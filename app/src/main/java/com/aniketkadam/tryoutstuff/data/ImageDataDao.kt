package com.aniketkadam.tryoutstuff.data

import androidx.lifecycle.LiveData
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
    fun getAllImageData(): LiveData<List<ImageData>>

}
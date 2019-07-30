package com.aniketkadam.tryoutstuff.data

import com.aniketkadam.tryoutstuff.network.ImageApi
import javax.inject.Inject


class Repository @Inject constructor(private val imageDataDao: ImageDataDao, private val imageApi: ImageApi) {
    
}
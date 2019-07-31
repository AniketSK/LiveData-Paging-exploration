package com.aniketkadam.tryoutstuff

import androidx.lifecycle.ViewModel
import com.aniketkadam.tryoutstuff.data.ImageListResult
import com.aniketkadam.tryoutstuff.data.Repository
import javax.inject.Inject

class MainVM @Inject constructor(val respository: Repository) : ViewModel() {

    private val _imageListResult: ImageListResult by lazy {
        respository.getPagedImageResult()
    }

    val imageList = _imageListResult.imageData
    val networkState = _imageListResult.networkState

}
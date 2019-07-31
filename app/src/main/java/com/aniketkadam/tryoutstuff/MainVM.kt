package com.aniketkadam.tryoutstuff

import androidx.lifecycle.ViewModel
import com.aniketkadam.tryoutstuff.data.Repository
import javax.inject.Inject

class MainVM @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _imageListResult by lazy {
        repository.getPagedImageResult()
    }

    val imageList = _imageListResult.imageData
    val networkState = _imageListResult.networkState

}
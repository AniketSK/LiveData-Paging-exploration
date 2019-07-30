package com.aniketkadam.tryoutstuff.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.aniketkadam.tryoutstuff.network.NetworkState

data class ImageListResult(
    val imageData: LiveData<PagedList<ImageData>>,
    val networkState: LiveData<NetworkState>
)
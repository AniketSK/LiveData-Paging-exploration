package com.aniketkadam.tryoutstuff.data

import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.aniketkadam.tryoutstuff.network.NetworkBoundaryCallback
import javax.inject.Inject


class Repository @Inject constructor(
    private val imageDataDao: ImageDataDao,
    private val boundaryCallback: NetworkBoundaryCallback
) {

    fun getPagedImageResult(): ImageListResult {
        val source = imageDataDao.getAllImageData()
        val networkErrors = boundaryCallback.networkState

        val config = PagedList.Config.Builder().setPageSize(20)
            .setPrefetchDistance(30)
            .setInitialLoadSizeHint(30)
            .setMaxSize(100)
            .build()

        val data = LivePagedListBuilder(source, config)
            .setBoundaryCallback(boundaryCallback)
            .build()

        return ImageListResult(data, networkErrors)
    }

}
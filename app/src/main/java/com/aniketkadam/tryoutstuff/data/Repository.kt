package com.aniketkadam.tryoutstuff.data

import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.aniketkadam.tryoutstuff.network.NetworkBoundaryCallback
import javax.inject.Inject

const val REPOSITORY_PREFETCH_DISTANCE = 30
class Repository @Inject constructor(
    private val imageDataDao: ImageDataDao,
    private val boundaryCallback: NetworkBoundaryCallback
) {

    fun getPagedImageResult(): ImageListResult {
        val source = imageDataDao.getAllImageData()
        val networkErrors = boundaryCallback.networkState

        val config = PagedList.Config.Builder().setPageSize(20)
            .setPrefetchDistance(REPOSITORY_PREFETCH_DISTANCE)
            .setInitialLoadSizeHint(30)
            .setEnablePlaceholders(true)
            .setMaxSize(100)
            .build()

        val data = LivePagedListBuilder(source, config)

            .setBoundaryCallback(boundaryCallback)
            .build()

        return ImageListResult(data, networkErrors)
    }

}
package com.aniketkadam.tryoutstuff.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.aniketkadam.tryoutstuff.data.ImageData
import com.aniketkadam.tryoutstuff.data.ImageDataDao
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class NetworkBoundaryCallback @Inject constructor(
    private val service: ImageApi,
    private val db: ImageDataDao
) : PagedList.BoundaryCallback<ImageData>() {

    val networkState: LiveData<NetworkState>
        get() = _networkState

    private val _networkState = MutableLiveData<NetworkState>()
        .apply {
            postValue(NetworkState.Idle)
        }

    private val initialLoad: Disposable? by lazy {
        loadDataIfNotLoading("1")
    }

    private var loadMoreItemsAtEnd: Disposable? = null

    override fun onZeroItemsLoaded() {
        super.onZeroItemsLoaded()
        initialLoad
    }

    override fun onItemAtEndLoaded(itemAtEnd: ImageData) {
        super.onItemAtEndLoaded(itemAtEnd)
        loadDataIfNotLoading("${itemAtEnd.id.toInt() + 1}")?.let {
            loadMoreItemsAtEnd = it
        } // Unless a reference is held, it could be GC'd. Subsequent calls shouldn't null it out
    }

    private fun loadDataIfNotLoading(id: String): Disposable? {
        if (networkState.value == NetworkState.Loading)
            return null
        else {
            _networkState.value = NetworkState.Loading
        }

        return service.findImages(id)
            .subscribeOn(Schedulers.io())
            .doOnEvent { data, _ -> db.insert(data) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { _networkState.value = NetworkState.Complete },
                onError = { _networkState.value = NetworkState.Error(it) }
            )
    }
}

sealed class NetworkState {
    object Idle : NetworkState()
    object Loading : NetworkState()
    object Complete : NetworkState()
    data class Error(val t: Throwable) : NetworkState()
}
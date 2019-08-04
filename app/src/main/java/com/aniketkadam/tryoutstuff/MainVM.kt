package com.aniketkadam.tryoutstuff

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aniketkadam.tryoutstuff.data.Repository
import javax.inject.Inject

class MainVM @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _imageListResult by lazy {
        repository.getPagedImageResult()
    }

    val imageList = _imageListResult.imageData
    val networkState = _imageListResult.networkState // This should only be shown as the last item in the recyclerview. So it's only visible when scrolled to bottom.
                                                                            // needs a different way to show loading when the expanded view is open though.

    private val _itemToNavigate = MutableLiveData<PositionOnFragment?>().apply { value = null }

    val itemToNavigate: LiveData<PositionOnFragment?>
        get() = _itemToNavigate

    fun setItemToNavigate(pf: PositionOnFragment) {
        _itemToNavigate.value = pf
    }
}

sealed class ActiveFragment {
    object List : ActiveFragment()
    object Selection : ActiveFragment()
}

// Which fragment to load, and what position to load on it.
data class PositionOnFragment(val af: ActiveFragment, val position: Int)
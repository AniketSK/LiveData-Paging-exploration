package com.aniketkadam.tryoutstuff

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aniketkadam.tryoutstuff.data.ImageData
import com.aniketkadam.tryoutstuff.data.Repository
import javax.inject.Inject

class MainVM @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _imageListResult by lazy {
        repository.getPagedImageResult()
    }

    val imageList = _imageListResult.imageData
    val networkState = _imageListResult.networkState

    val selectedItem = MutableLiveData<ImageData>()

    // When an item is selected, launch the fragment that is supposed to show it.
    private val _navigate = MutableLiveData<ActiveFragment>().apply { value = ActiveFragment.List }
    val navigate: LiveData<ActiveFragment>
        get() = _navigate
}

sealed class ActiveFragment {
    object List : ActiveFragment()
    object Selection : ActiveFragment()
}
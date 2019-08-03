package com.aniketkadam.tryoutstuff

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.aniketkadam.tryoutstuff.data.ImageData
import com.aniketkadam.tryoutstuff.data.Repository
import javax.inject.Inject

class MainVM @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _imageListResult by lazy {
        repository.getPagedImageResult()
    }

    val imageList = _imageListResult.imageData
    val networkState = _imageListResult.networkState // This should only be shown as the last item in the recyclerview. So it's only visible when scrolled to bottom.
                                                                            // needs a different way to show loading when the expanded view is open though.

    val selectedItem = MutableLiveData<ImageData?>().apply { value = null }

    // When an item is selected, launch the fragment that is supposed to show it.
    val navigate: LiveData<ActiveFragment> =
        Transformations.map(selectedItem) { if (it == null) ActiveFragment.List else ActiveFragment.Selection }
}

sealed class ActiveFragment {
    object List : ActiveFragment()
    object Selection : ActiveFragment()
}
package com.aniketkadam.tryoutstuff

import com.aniketkadam.tryoutstuff.data.ImageData
import io.reactivex.Flowable

data class MainViewState(val imageList: Flowable<ImageData>)
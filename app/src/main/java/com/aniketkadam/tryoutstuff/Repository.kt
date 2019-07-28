package com.aniketkadam.tryoutstuff

import com.aniketkadam.tryoutstuff.data.ImageData
import io.reactivex.Flowable


class Repository() {

    fun getDatabase(): Flowable<List<ImageData>> = Flowable.just(emptyList())
}
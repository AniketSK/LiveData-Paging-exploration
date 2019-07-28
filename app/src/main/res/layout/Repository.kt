package com.aniketkadam.tryoutstuff

import io.reactivex.Flowable


class Repository() {

    fun getDatabase(): Flowable<List<ImageData>> = Flowable.just(emptyList())
}
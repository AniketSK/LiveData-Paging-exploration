package com.aniketkadam.tryoutstuff.network

import com.aniketkadam.tryoutstuff.data.ImageData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
const val IMAGES_LOCATION = "someUrl"
interface ImageApi {

    @GET(IMAGES_LOCATION)
    fun findImages(@Query("id") id: String): Single<List<ImageData>>
}
package com.aniketkadam.tryoutstuff.network

import com.aniketkadam.tryoutstuff.data.ImageData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageApi {

    @GET("someUrl")
    fun findImages(@Query("id") id: String): Single<ImageData>
}
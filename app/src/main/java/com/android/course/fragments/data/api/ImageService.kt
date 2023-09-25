package com.android.course.fragments.data.api

import com.android.course.fragments.domain.model.PexelRs
import retrofit2.http.GET
import retrofit2.http.Header

interface ImageService {

    @GET("search?query=cats")
    suspend fun getAllImages(@Header("Authorization") key: String): PexelRs
}
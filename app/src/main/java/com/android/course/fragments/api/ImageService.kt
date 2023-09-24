package com.android.course.fragments.api

import com.android.course.fragments.model.PexelRs
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface ImageService {

    @GET("search?query=cats")
    fun getAllImages(@Header("Authorization") key: String): Call<PexelRs>
}
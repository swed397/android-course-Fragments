package com.android.course.fragments.model

import com.google.gson.annotations.SerializedName

data class PexelImageSource(

    @SerializedName("id")
    val id: Long,

    @SerializedName("src")
    val src: ImageDimension
)

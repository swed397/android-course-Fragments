package com.android.course.fragments.domain.model

import com.google.gson.annotations.SerializedName

data class ImageDimension(

    @SerializedName("large")
    val large: String,

    @SerializedName("tiny")
    val tiny: String
)
package com.android.course.fragments.model

import com.google.gson.annotations.SerializedName

data class PexelRs(

    @SerializedName("photos")
    val images: List<PexelImageSource>
)

package com.android.course.fragments.model

import android.graphics.Bitmap
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PhoneImages(
    val id: Long,
    val name: String,
    val image: Bitmap
) : Parcelable

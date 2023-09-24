package com.android.course.fragments.model

import android.graphics.Bitmap
import android.os.Parcelable
import com.android.course.fragments.repo.Images
import kotlinx.parcelize.Parcelize

@Parcelize
data class PhoneImages(
    val id: Long,
    val name: String,
    override val image: Bitmap
) : Parcelable, Images

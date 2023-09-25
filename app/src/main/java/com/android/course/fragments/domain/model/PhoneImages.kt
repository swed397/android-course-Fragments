package com.android.course.fragments.domain.model

import android.graphics.Bitmap
import com.android.course.fragments.data.repo.Images

data class PhoneImages(
    val id: Long,
    val name: String,
    override val image: Bitmap
) : Images

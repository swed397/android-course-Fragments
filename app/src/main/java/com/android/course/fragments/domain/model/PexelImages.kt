package com.android.course.fragments.domain.model

import android.graphics.Bitmap
import com.android.course.fragments.data.repo.Images

data class PexelImages(
    val id: Long,
    override val image: Bitmap
) : Images

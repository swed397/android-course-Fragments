package com.android.course.fragments.domain.model

import android.graphics.Bitmap

data class PhoneContact(
    val name: String,
    val phoneNumber: String,
    val contactPhoto: Bitmap?
)

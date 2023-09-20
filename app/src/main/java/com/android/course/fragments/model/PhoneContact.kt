package com.android.course.fragments.model

import android.graphics.Bitmap
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PhoneContact(
    val name: String,
    val phoneNumber: String,
    val contactPhoto: Bitmap?
) : Parcelable

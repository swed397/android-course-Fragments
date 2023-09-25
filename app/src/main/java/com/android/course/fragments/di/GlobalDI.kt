package com.android.course.fragments.di

import android.content.ContentResolver
import com.android.course.fragments.App
import com.android.course.fragments.data.api.ImageService
import com.android.course.fragments.data.api.RetrofitClientInstance
import com.android.course.fragments.data.repo.ContactsRepository
import com.android.course.fragments.data.repo.PexelImagesRepository
import com.android.course.fragments.data.repo.PhoneImagesRepository
import retrofit2.Retrofit

object GlobalDI {

    private val contentResolver: ContentResolver
        get() = App.appContext.contentResolver

    val contactsRepo: ContactsRepository by lazy { ContactsRepository(contentResolver) }
    val phoneImagesRepo: PhoneImagesRepository by lazy { PhoneImagesRepository(contentResolver) }

    private val retrofit: Retrofit = RetrofitClientInstance.getInstance()
    private val pexelsService: ImageService = retrofit.create(ImageService::class.java)
    val pexelsImagesRepo: PexelImagesRepository by lazy { PexelImagesRepository(pexelsService) }
}
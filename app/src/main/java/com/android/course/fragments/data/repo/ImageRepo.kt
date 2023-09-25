package com.android.course.fragments.data.repo

interface ImageRepo {

    suspend fun getAllImages(): List<Images>
}
package com.android.course.fragments.data.repo

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class BaseImageRepo : ImageRepo {

    protected abstract suspend fun getData(): List<Images>

    override suspend fun getAllImages(): List<Images> {
        return withContext(Dispatchers.IO) {
            getData()
        }
    }
}
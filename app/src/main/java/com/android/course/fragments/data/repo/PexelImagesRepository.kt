package com.android.course.fragments.data.repo

import com.android.course.fragments.data.api.ImageService
import com.android.course.fragments.domain.model.PexelImages
import com.squareup.picasso.Picasso


class PexelImagesRepository(
    private val pexelsService: ImageService
) : BaseImageRepo() {

    override suspend fun getData(): List<Images> =
        pexelsService.getAllImages(AUTH_KEY).images.map {
            PexelImages(
                id = it.id,
                image = Picasso.get().load(it.src.tiny).get()
            )
        }

    private companion object {
        const val AUTH_KEY = "qopuKnaZCU5N17waErgVbND5Po1hKVzUMo9OtT9OAurx1gGX2CqTqhKv"
    }
}


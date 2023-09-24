package com.android.course.fragments.repo

import com.android.course.fragments.api.ImageService
import com.android.course.fragments.api.RetrofitClientInstance
import com.android.course.fragments.model.PexelImages
import com.android.course.fragments.model.PexelRs
import com.squareup.picasso.Picasso


class PexelImagesRepository : ImageRepo {

    private lateinit var pexelRs: PexelRs

    override fun getAllImages(): List<PexelImages> {
        val imageSource =
            RetrofitClientInstance.getInstance().create(ImageService::class.java).getAllImages(
                AUTH_KEY
            )

        pexelRs = imageSource.execute().body()!!
//        imageSource.enqueue(object : Callback<PexelRs> {
//            override fun onResponse(call: Call<PexelRs>, response: Response<PexelRs>) {
//                if (response.isSuccessful) {
//                    pexelRs = response.body()!!
//                }
//            }
//
//            override fun onFailure(call: Call<PexelRs>, t: Throwable) {
//            }
//        })

        return pexelRs.images.map {
            PexelImages(
                id = it.id,
                image = Picasso.get().load(it.src.tiny).get()
            )
        }
    }

    private companion object {
        const val AUTH_KEY = "qopuKnaZCU5N17waErgVbND5Po1hKVzUMo9OtT9OAurx1gGX2CqTqhKv"
    }
}


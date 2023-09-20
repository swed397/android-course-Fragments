package com.android.course.fragments.repo

import android.content.ContentResolver
import android.content.ContentUris
import android.graphics.Bitmap
import android.os.Build
import android.os.CancellationSignal
import android.provider.MediaStore
import android.util.Size
import com.android.course.fragments.model.PhoneImages

class ImagesRepository(private val contentResolver: ContentResolver) {

    fun getImages(): List<PhoneImages> {
        val photosList = mutableListOf<PhoneImages>()

        val cursor = contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            null,
            null,
            null,
            null
        ) ?: return photosList

        return cursor.use {
            while (cursor.moveToNext()) {
                val nameColumn = cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME)
                val idColumn = cursor.getColumnIndex(MediaStore.Images.Media._ID)

                val photoId = cursor.getLong(idColumn)
                val photoName = cursor.getString(nameColumn)


                photosList.add(
                    PhoneImages(
                        id = photoId,
                        name = photoName,
                        image = contentResolver.getBitmap(photoId)
                    )
                )
            }
            photosList
        }
    }

    private fun ContentResolver.getBitmap(id: Long): Bitmap {
        val cs = CancellationSignal()
        val imageUri =
            ContentUris.withAppendedId(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                id
            )

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            contentResolver.loadThumbnail(imageUri, Size(DEFAULT_SIZE, DEFAULT_SIZE), cs)
        } else {
            MediaStore.Images.Thumbnails.getThumbnail(
                contentResolver, id,
                MediaStore.Images.Thumbnails.MINI_KIND, null
            )
        }
    }

    private companion object {
        const val DEFAULT_SIZE = 50
    }
}
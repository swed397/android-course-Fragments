package com.android.course.fragments

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.android.course.fragments.repo.ContactsRepository
import com.android.course.fragments.repo.ImagesRepository
import com.android.course.fragments.utils.FragmentsIndex
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class MainActivity : AppCompatActivity() {

    val contactsRepo: ContactsRepository by lazy { ContactsRepository(contentResolver) }
    val imagesRepo: ImagesRepository by lazy { ImagesRepository(contentResolver) }
    private lateinit var tableLayout: TabLayout
    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkPermissions()

        tableLayout = findViewById(R.id.tab_layout)
        viewPager = findViewById<ViewPager2?>(R.id.view_pager).apply {
            adapter = MainFragmentsStateAdapter(this@MainActivity)
        }

        TabLayoutMediator(tableLayout, viewPager) { tab, position ->
            when (position) {
                FragmentsIndex.CONTACTS.index -> tab.text = getString(R.string.phone_contacts_label)
                FragmentsIndex.IMAGES_PHONE.index -> tab.text =
                    getString(R.string.images_label)

                else -> throw IllegalStateException("No such fragment")
            }
        }.attach()
    }

    private fun checkPermissions() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_CONTACTS
            ) != PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_MEDIA_IMAGES
            ) != PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_MEDIA_VIDEO
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                listOf(
                    Manifest.permission.READ_CONTACTS,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.READ_MEDIA_IMAGES,
                    Manifest.permission.READ_MEDIA_VIDEO,
                ).toTypedArray(), 0
            )
        }
    }

    companion object {
        const val KEY = "CONTACTS_KEY"
    }
}
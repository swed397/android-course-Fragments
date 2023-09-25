package com.android.course.fragments.presentation

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.android.course.fragments.R
import com.android.course.fragments.utils.FragmentType
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class MainActivity : AppCompatActivity() {

    private lateinit var tableLayout: TabLayout
    private lateinit var viewPager: ViewPager2

    private val requestMultiplePermissionsLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            val allPermissionsGranted = permissions.all { it.value }
            if (allPermissionsGranted) {
                initAdapter()
            } else {
                Toast.makeText(this, "Permission error!", Toast.LENGTH_SHORT).show()
            }
        }

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
                FragmentType.CONTACTS.ordinal -> tab.text = getString(R.string.phone_contacts_label)
                FragmentType.IMAGES_PHONE.ordinal -> tab.text =
                    getString(R.string.images_label)

                FragmentType.IMAGES_PEXEL.ordinal -> tab.text =
                    getString(R.string.images_pexel_label)

                else -> throw IllegalStateException("No such fragment")
            }
        }.attach()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initAdapter() {
        viewPager.adapter = MainFragmentsStateAdapter(this@MainActivity)
        TabLayoutMediator(tableLayout, viewPager) { tab, position ->
            when (position) {
                FragmentType.CONTACTS.ordinal -> tab.text = getString(R.string.phone_contacts_label)
                FragmentType.IMAGES_PHONE.ordinal -> tab.text = getString(R.string.images_label)
                FragmentType.IMAGES_PEXEL.ordinal -> tab.text =
                    getString(R.string.images_pexel_label)

                else -> throw IllegalStateException("No such fragment")
            }
        }.attach()
    }

    private fun checkPermissions() {
        val requiredPermissions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arrayOf(
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.READ_MEDIA_IMAGES,
                Manifest.permission.READ_MEDIA_VIDEO,
            )
        } else {
            arrayOf(
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.READ_EXTERNAL_STORAGE,
            )
        }

        val permissionsToRequest = requiredPermissions.filter {
            ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED
        }

        requestMultiplePermissionsLauncher.launch(permissionsToRequest.toTypedArray())
    }

    companion object {
        const val KEY = "CONTACTS_KEY"
    }
}
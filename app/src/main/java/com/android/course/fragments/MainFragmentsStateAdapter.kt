package com.android.course.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.android.course.fragments.MainActivity.Companion.KEY
import com.android.course.fragments.fragments.ImagesFragment
import com.android.course.fragments.fragments.PhoneContactsFragment
import com.android.course.fragments.utils.FragmentsIndex

class MainFragmentsStateAdapter(private val activity: MainActivity) :
    FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = FragmentsIndex.values().size

    override fun createFragment(position: Int): Fragment =
        when (position) {
            FragmentsIndex.CONTACTS.index -> {
                val bundle = Bundle().apply {
                    putParcelableArray(
                        KEY,
                        activity.contactsRepo.getContacts().toTypedArray()
                    )
                }
                PhoneContactsFragment.newInstance().apply { arguments = bundle }
            }

            FragmentsIndex.IMAGES_PHONE.index -> {
                val bundle = Bundle().apply {
                    putParcelableArray(
                        KEY,
                        activity.phoneImagesRepo.getAllImages().toTypedArray()
                    )
                }
                ImagesFragment.newInstance().apply { arguments = bundle }
            }

            FragmentsIndex.IMAGES_PEXEL.index -> {
                val bundle = Bundle().apply {
                    putParcelableArray(
                        KEY,
                        activity.pexelImagesRepo.getAllImages().toTypedArray()
                    )
                }
                ImagesFragment.newInstance().apply { arguments = bundle }
            }

            else -> throw IllegalStateException("No such fragment")
        }
}
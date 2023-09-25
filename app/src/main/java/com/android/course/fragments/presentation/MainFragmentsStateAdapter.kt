package com.android.course.fragments.presentation

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.android.course.fragments.presentation.MainActivity.Companion.KEY
import com.android.course.fragments.presentation.contacts.PhoneContactsFragment
import com.android.course.fragments.presentation.images.ImagesFragment
import com.android.course.fragments.utils.FragmentType

class MainFragmentsStateAdapter(private val activity: MainActivity) :
    FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = FragmentType.values().size

    override fun createFragment(position: Int): Fragment =
        when (position) {
            FragmentType.CONTACTS.ordinal -> {
                PhoneContactsFragment.newInstance()
            }

            FragmentType.IMAGES_PHONE.ordinal -> {
                ImagesFragment.newInstance()
                    .apply { arguments = bundleOf(KEY to FragmentType.IMAGES_PHONE) }
            }

            FragmentType.IMAGES_PEXEL.ordinal -> {
                ImagesFragment.newInstance()
                    .apply { arguments = bundleOf(KEY to FragmentType.IMAGES_PEXEL) }
            }

            else -> throw IllegalStateException("No such fragment")
        }
}
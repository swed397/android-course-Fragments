package com.android.course.fragments.presentation.images

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.android.course.fragments.R
import com.android.course.fragments.data.repo.BaseImageRepo
import com.android.course.fragments.di.GlobalDI
import com.android.course.fragments.presentation.MainActivity.Companion.KEY
import com.android.course.fragments.utils.FragmentType
import com.android.course.fragments.utils.getSerializableCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch


class ImagesFragment : Fragment(R.layout.phone_images_fragment) {

    private lateinit var repo: BaseImageRepo
    private lateinit var fragmentType: FragmentType
    private val scope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    override fun onAttach(context: Context) {
        super.onAttach(context)

        repo = arguments?.getSerializableCompat<FragmentType>(KEY).run {
            when (this) {
                FragmentType.IMAGES_PHONE -> {
                    fragmentType = FragmentType.IMAGES_PHONE
                    GlobalDI.phoneImagesRepo
                }

                FragmentType.IMAGES_PEXEL -> {
                    fragmentType = FragmentType.IMAGES_PHONE
                    GlobalDI.pexelsImagesRepo
                }

                else -> throw IllegalStateException("no repo for this fragment")
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.images_recycler_view)
        val adapter = ImagesRecyclerViewAdapter()
        recyclerView.adapter = adapter

        scope.launch {
            val images = repo.getAllImages().toList()
            adapter.setData(images)
        }
    }

    companion object {
        fun newInstance(): ImagesFragment = ImagesFragment()
    }
}
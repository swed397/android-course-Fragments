package com.android.course.fragments.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.android.course.fragments.MainActivity.Companion.KEY
import com.android.course.fragments.R
import com.android.course.fragments.adapters.images.ImagesRecyclerViewAdapter
import com.android.course.fragments.model.PhoneImages


class ImagesFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.phone_images_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val photoImagesList =
            arguments?.getParcelableArray(KEY) as Array<PhoneImages>

        val recyclerView = view.findViewById<RecyclerView>(R.id.images_recycler_view)
        val adapter = ImagesRecyclerViewAdapter()

        recyclerView.adapter = adapter
        adapter.setData(photoImagesList.toList())
    }

    companion object {
        fun newInstance(): ImagesFragment = ImagesFragment()
    }
}
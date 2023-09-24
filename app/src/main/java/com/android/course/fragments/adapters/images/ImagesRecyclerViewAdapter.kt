package com.android.course.fragments.adapters.images

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.android.course.fragments.R
import com.android.course.fragments.repo.Images

class ImagesRecyclerViewAdapter :
    RecyclerView.Adapter<ImagesRecyclerViewAdapter.ImagesRecyclerViewHolder>() {

    var phoneImagesList: List<Images> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesRecyclerViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.images_recycler_view_item, parent, false)

        return ImagesRecyclerViewHolder(view)
    }

    override fun getItemCount(): Int = phoneImagesList.size

    override fun onBindViewHolder(holder: ImagesRecyclerViewHolder, position: Int) {
        holder.bind(position)
    }

    fun setData(data: List<Images>) {
        phoneImagesList = data
    }

    inner class ImagesRecyclerViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        private val imageView: ImageView by lazy { itemView.findViewById(R.id.image_image_view) }

        fun bind(position: Int) = imageView.setImageBitmap(phoneImagesList[position].image)
    }
}
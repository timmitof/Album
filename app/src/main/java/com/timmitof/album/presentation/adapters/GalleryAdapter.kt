package com.timmitof.album.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.timmitof.album.database.entity.Image
import com.timmitof.album.databinding.ItemPhotosBinding

class GalleryAdapter(private val deleteListener: (Image) -> Unit): RecyclerView.Adapter<GalleryAdapter.ImageViewHolder>() {

    private var data = listOf<Image>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ItemPhotosBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(binding)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val item = data[position]
        with(holder) {
            Glide.with(holder.itemView).load(item.image).into(binding.imageView)
            binding.title.text = "${item.date?.time}"
            binding.imageView.setOnClickListener {
                deleteListener.invoke(item)
            }
        }
    }

    fun addList(list: List<Image>?) {
        if (list != null) {
            data = list
            notifyDataSetChanged()
        }
    }

    inner class ImageViewHolder(val binding: ItemPhotosBinding): RecyclerView.ViewHolder(binding.root)
}

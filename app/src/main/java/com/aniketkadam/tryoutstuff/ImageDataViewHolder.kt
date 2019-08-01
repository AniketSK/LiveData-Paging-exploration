package com.aniketkadam.tryoutstuff

import androidx.recyclerview.widget.RecyclerView
import com.aniketkadam.tryoutstuff.data.ImageData
import com.aniketkadam.tryoutstuff.databinding.DataItemBinding

class ImageDataViewHolder(private val itemBinding: DataItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(imageData: ImageData) {
        itemBinding.apply {
            data = imageData
            executePendingBindings()
        }
    }

}
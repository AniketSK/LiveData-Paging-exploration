package com.aniketkadam.tryoutstuff.bindingadapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

class ImageBindingAdapter {

    @BindingAdapter("profileImage")
    fun loadImage(view: ImageView, imageUrl: String) {
        Glide.with(view.context)
            .load(imageUrl).fitCenter()
            .into(view)
    }
}
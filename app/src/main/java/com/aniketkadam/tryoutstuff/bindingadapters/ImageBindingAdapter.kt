package com.aniketkadam.tryoutstuff.bindingadapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide

object ImageBindingAdapter {

    @JvmStatic
    @BindingAdapter("profileImage")
    fun loadImage(view: ImageView, imageUrl: String) {
        Glide.with(view.context)
            .load(imageUrl).fitCenter()
            .error(android.R.drawable.ic_menu_upload_you_tube)
            .placeholder(CircularProgressDrawable(view.context).apply {
                strokeWidth = 5f
                centerRadius = 30f
                start()
            })
            .into(view)
    }
}
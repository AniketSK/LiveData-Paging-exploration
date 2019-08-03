package com.aniketkadam.tryoutstuff

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.aniketkadam.tryoutstuff.data.ImageData
import com.aniketkadam.tryoutstuff.databinding.DataItemBinding

class PagedAdapter(private val onItemClickHandler: (ImageData?) -> Unit) :
    PagedListAdapter<ImageData, ImageDataViewHolder>(REPO_COMPARATOR) {

    private var layoutInflater: LayoutInflater? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageDataViewHolder {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.context)
        }
        // No way this layoutInflater can be null, also better crash early if it somehow is
        DataBindingUtil.inflate<DataItemBinding>(layoutInflater!!, R.layout.data_item, parent, false).let {
            return ImageDataViewHolder(it) { itemAdapterposition: Int -> onItemClickHandler(getItem(itemAdapterposition)) }
        }
    }

    // Crash it early if the item is somehow null
    override fun onBindViewHolder(holder: ImageDataViewHolder, position: Int) = holder.bind(getItem(position)!!)

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<ImageData>() {

            override fun areContentsTheSame(oldItem: ImageData, newItem: ImageData): Boolean =
                oldItem.id == newItem.id

            override fun areItemsTheSame(old: ImageData, new: ImageData): Boolean =
                old == new


        }
    }

}
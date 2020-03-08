package com.mbariah.wallpapers.wall

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mbariah.wallpapers.databinding.ItemBinding
import com.mbariah.wallpapers.models.Photo

class ImagesAdapter :
    ListAdapter<Photo, ImagesAdapter.ImagesViewHolder> {

    private val onClickListener: ClickListener

    constructor(onClickListener: ClickListener) : super(DiffCallback()) {
        this.onClickListener = onClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesViewHolder {
        return ImagesViewHolder(
            ItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: ImagesViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(item)
        }
        holder.bind(item)
    }

    class ImagesViewHolder : RecyclerView.ViewHolder {

        private val binding: ItemBinding

        constructor(binding: ItemBinding) : super(binding.root) {
            this.binding = binding
        }

        fun bind(photo: Photo) {
            binding.photo = photo
        }
    }

    //Class for comparing the old list and the new one, and updating it
    class DiffCallback : DiffUtil.ItemCallback<Photo>() {
        override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return newItem.id == oldItem.id
        }
    }

    //Create a class that takes a high level Unit function
    //& callback my onClick() - function
    class ClickListener(val clickListener: (photo: Photo) -> Unit) {
        fun onClick(photo: Photo) = clickListener(photo)
    }
}
package com.mbariah.wallpapers.utils

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mbariah.wallpapers.R
import com.mbariah.wallpapers.models.Photo
import com.mbariah.wallpapers.wall.ImagesAdapter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.picasso.Picasso
import java.lang.reflect.Type

//Binding adapter used to display images from URL using Glide
@BindingAdapter("imageUrl")
fun bindImage(imageView: ImageView, imgUrl: String?) {

    /*
        val moshi = Moshi.Builder().build()
        val jsonAdapter: JsonAdapter<Photo> = moshi.adapter<Photo>(Photo::class.java)
        val response: String? = jsonAdapter.toJson(imgUrl)
        print(response)
    */

    if (imgUrl != null) {
        Picasso.get()
            .load(imgUrl)
            .placeholder(R.drawable.loading_animation)
            .error(R.drawable.ic_broken_image)
            .into(imageView)
    }else{
        Picasso.get()
            .load(R.drawable.ic_broken_image)
            .placeholder(R.drawable.loading_animation)
            .fit().centerCrop()
            .error(R.drawable.ic_broken_image)
            .into(imageView)
    }

}

@BindingAdapter("visibleGone")
fun showHide(view: View, show: Boolean) {
    view.visibility = if (show) View.VISIBLE else View.GONE
}

//Binding adapter used to set adapter of RecyclerView
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Photo>?) {
    data?.let {
        val adapter = recyclerView.adapter as ImagesAdapter
        adapter.submitList(data)
    }
}

//Binding adapter used to hide the spinner once data is available.
@BindingAdapter("isNetworkError", "playlist")
fun hideIfNetworkError(view: View, isNetWorkError: Boolean, playlist: Any?) {
    view.visibility = if (playlist != null) View.GONE else View.VISIBLE
    if (isNetWorkError) {
        view.visibility = View.GONE
    }
}

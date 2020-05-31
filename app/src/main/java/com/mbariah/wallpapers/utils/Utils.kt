package com.mbariah.wallpapers.utils

import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.util.Log
import android.widget.ImageView
import com.mbariah.wallpapers.R
import com.squareup.picasso.Callback
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso

class Utils {

    companion object {

        fun loadWithPicasso(into: ImageView, url: String, error: Int = R.drawable.ic_broken_image) {
            //if url is empty that means we don't have the url simply return.
            if (TextUtils.isEmpty(url))
                return
            //We will try to fetch offline data if it is possible.
            //If no we will fetch it from URL
            Picasso.get()
                .load(url)
                .placeholder(R.drawable.loading_animation)
                .error(error)
                .networkPolicy(NetworkPolicy.OFFLINE)
                .into(into, object : Callback {
                    override fun onSuccess() {
                        Log.w("Picasso", "$url: Image loaded from cache")
                    }

                    override fun onError(e: Exception) {
                        Picasso.get()
                            .load(url)
                            .error(error)
                            .into(into, object : Callback {
                                override fun onSuccess() {}
                                override fun onError(e: Exception) {
                                    Log.w("Picasso", "$url: couldn't download the image.")
                                }
                            })
                    }
                })
        }


        fun preloadWithPicasso(
            into: ImageView,
            url: String,
            placeholder: Drawable,
            error: Int = R.drawable.ic_broken_image
        ) {
            //We will try to fetch offline data if it is possible.
            //If no we will fetch it from URL
            Picasso.get()
                .load(url)
                .placeholder(placeholder)
                .error(error)
                .networkPolicy(NetworkPolicy.OFFLINE)
                .into(into, object : Callback {
                    override fun onSuccess() {
                        Log.w("Picasso", "$url: Image loaded from cache")
                    }

                    override fun onError(e: Exception) {
                        Picasso.get()
                            .load(url)
                            .into(into, object : Callback {
                                override fun onSuccess() {}
                                override fun onError(e: Exception) {
                                    Log.w("Picasso", "$url: couldn't download the image.")
                                }
                            })
                    }
                })
        }

    }
}
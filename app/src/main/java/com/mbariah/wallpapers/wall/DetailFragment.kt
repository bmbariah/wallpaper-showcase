package com.mbariah.wallpapers.wall

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.mbariah.wallpapers.R
import com.mbariah.wallpapers.utils.Logger
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.detail_fragment.*
import kotlinx.android.synthetic.main.image_bar.*


class DetailFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.detail_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val safeArgs: DetailFragmentArgs by navArgs()
        val photo = safeArgs.photoItem

        hideToolbar(true)

        title.text = photo?.title
        author.text = photo?.ownername

        val buddyIcon = "https://farm${photo?.iconfarm}.staticflickr.com/${photo?.iconserver}/buddyicons/${photo?.owner}.jpg"

        Logger.dt(buddyIcon)
        Picasso.get()
            .load(buddyIcon)
            .placeholder(R.drawable.buddyicon)
            .error(R.drawable.buddyicon)
            .into(buddyico)

        Picasso.get()
            .load(photo?.urlL) // thumbnail url goes here
            .into(img, object : Callback {
                override fun onSuccess() {
                    Picasso.get()
                        .load(photo?.urlO)
                        .placeholder(img.drawable)
                        .error(R.drawable.ic_broken_image)
                        .into(img)
                }
                override fun onError(e: Exception?) {}
            })
    }
}
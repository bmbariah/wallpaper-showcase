package com.mbariah.wallpapers.ui

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.navigation.fragment.navArgs
import androidx.palette.graphics.Palette
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat
import com.mbariah.wallpapers.R
import com.mbariah.wallpapers.utils.Utils
import com.squareup.picasso.Callback
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
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

        Picasso.get()
            .load(buddyIcon)
            .placeholder(R.drawable.buddyicon)
            .error(R.drawable.buddyicon)
            .into(buddyico)

        Picasso.get()
            .load(photo?.urlL) // thumbnail url goes here
            .into(img, object : Callback {
                override fun onSuccess() {
                    Log.w("Picasso", "Inner Image loaded from cache")

                    photo?.urlO?.let {
                        Picasso.get()
                            .load(it)
                            .placeholder(img.drawable)
                            .error(img.drawable)
                            .into(img)
                    }

                }

                override fun onError(e: Exception?) {}
            })

        back.setOnClickListener { activity?.onBackPressed() }

        //force center crop
        img.scaleType = ImageView.ScaleType.CENTER_CROP

        //fetchPalette()
    }

    private fun fetchPalette() {

        img?.drawable?.toBitmap()?.let {
            Palette.from(it).generate { palette ->

                //val vibrantSwatch: Palette.Swatch? = palette?.vibrantSwatch
                //val darkVibrantSwatch: Palette.Swatch? = palette?.darkVibrantSwatch
                val lightVibrantSwatch: Palette.Swatch? = palette?.lightVibrantSwatch
                //val mutedSwatch: Palette.Swatch? = palette?.mutedSwatch
                //val darkMutedSwatch: Palette.Swatch? = palette?.darkMutedSwatch
                val lightMutedSwatch: Palette.Swatch? = palette?.lightMutedSwatch

                //Apply palette to background
                lightMutedSwatch?.rgb?.let { image_bg.setBackgroundColor(it) }

                //Apply palette to back button
                val vectorDrawable: Drawable? = VectorDrawableCompat.create(
                    resources,
                    R.drawable.ic_arrow_back_24dp,
                    null
                )
                val drawable = DrawableCompat.wrap(vectorDrawable!!)
                lightVibrantSwatch?.rgb?.let { DrawableCompat.setTint(drawable.mutate(), it) }
                back.setImageDrawable(drawable)

                //Apply to bottomSheet
                //lightMutedSwatch?.rgb?.let { bottom_sheet.setBackgroundColor(it)}

                /* darkMutedSwatch?.rgb?.let {
                         DrawableCompat.setTint(
                             back.drawable,
                             ContextCompat.getColor(  App.appContext, it)
                         )
                     }*/
            }
        }
    }
}
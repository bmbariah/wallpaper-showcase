package com.mbariah.wallpapers.wall

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.mbariah.wallpapers.R
import com.mbariah.wallpapers.databinding.DetailFragmentBinding
import com.squareup.picasso.Picasso

class DetailFragment : Fragment() {

    lateinit var binding: DetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DetailFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val safeArgs: DetailFragmentArgs by navArgs()
        val photo = safeArgs.photoItem

        Picasso.get()
            .load(photo?.src?.original)
            .placeholder(R.drawable.loading_animation)
            .error(R.drawable.ic_broken_image)
            .into(binding.img)

    }
}
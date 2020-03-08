package com.mbariah.wallpapers.wall

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {

    fun setTitle(title: String) {
        (activity as? AppCompatActivity)?.supportActionBar?.title = title
    }

    fun setHomeAsUp(value: Boolean = false) {

        if (value) {
            (activity as? AppCompatActivity)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
            (activity as? AppCompatActivity)?.supportActionBar?.setDisplayShowHomeEnabled(true)
        } else {
            (activity as? AppCompatActivity)?.supportActionBar?.setDisplayHomeAsUpEnabled(false)
            (activity as? AppCompatActivity)?.supportActionBar?.setDisplayShowHomeEnabled(false)
        }
    }

    fun hideToolbar(value: Boolean = false) {
        if (value) {
            (activity as? AppCompatActivity)?.supportActionBar?.hide()
        } else {
            (activity as? AppCompatActivity)?.supportActionBar?.show()
        }
    }
}
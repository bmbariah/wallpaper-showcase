@file:JvmName("ExtensionsUtils")
package com.mbariah.wallpapers.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

//Extension function to help avoid boilerplate & enable re-usability
fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
    //old way
    //val view = inflater.inflate(R.layout.news_fragment, container, false)
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}

/**
 * Retrieve ViewModel from current Fragment.
 */
inline fun <reified T : ViewModel> Fragment.getViewModel(viewModelFactory: ViewModelProvider.Factory): T =
    ViewModelProviders.of(this, viewModelFactory)[T::class.java]

//Generic Extended Class with a high order function as argument
fun<T> androidLazy(highOrderfn: () -> T) = lazy(LazyThreadSafetyMode.NONE, highOrderfn)
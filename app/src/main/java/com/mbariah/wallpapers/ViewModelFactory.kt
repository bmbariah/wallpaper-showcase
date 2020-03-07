package com.mbariah.wallpapers


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Lazy
import javax.inject.Inject

/**
 * Lazy ViewModel Factory to be used for scopes and subscopes.
 *
 */
class ViewModelFactory<T : ViewModel> : ViewModelProvider.Factory {
    private val viewModel: Lazy<T>

    // @Inject tells Dagger how to create instances of T
    @Inject
    constructor(viewModel: Lazy<T>) {
        this.viewModel = viewModel
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = viewModel.get() as T
}
package com.mbariah.wallpapers.ui

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mbariah.wallpapers.models.Photo
import com.mbariah.wallpapers.network.ImagesSource
import com.mbariah.wallpapers.utils.Logger
import kotlinx.coroutines.launch
import javax.inject.Inject


class HomeViewModel @Inject constructor(private val imagesSource: ImagesSource) : ViewModel() {

    //@Inject - Tells Dagger how to create instances of HomeViewModel

    //LiveData Object
    private var _imagesList: MutableLiveData<MutableList<Photo>> = MutableLiveData()
    val imagesList: LiveData<MutableList<Photo>> get() = _imagesList

    private var _isLoading: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    private var _hasNetworkError: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val hasNetworkError: LiveData<Boolean> get() = _hasNetworkError

    private val errorListener: View.OnClickListener

    private var page: Int = 1

    init {
        searchEmptyList(limit = "10")
        this.errorListener = View.OnClickListener { this.searchEmptyList(limit = "10") }
    }

    fun searchEmptyList(limit: String) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val results = imagesSource.getImages(page.toString(), limit)
                Logger.dt("Fetched from page $page")
                page += 1

                //TODO() Find a better way to do this
                if (_imagesList.value == null) {
                    //New Batch
                    _imagesList.value = results.toMutableList()
                } else {
                    //Append to existing
                    _imagesList.value!!.addAll(results)
                }

                _isLoading.value = false
            } catch (e: Exception) {
                Logger.dt(e.toString())
                _isLoading.value = false
                _hasNetworkError.value = true
            }
        }
    }


    fun searchList(page: String, limit: String, search: String) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val results = imagesSource.searchImages(page, limit, search)
                //_imagesList.value = results.photos?.photo
                _isLoading.value = false
            } catch (e: Exception) {
                Logger.dt(e.toString())
                _isLoading.value = false
                _hasNetworkError.value = true
            }
        }
    }

}
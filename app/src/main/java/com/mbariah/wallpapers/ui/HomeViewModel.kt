package com.mbariah.wallpapers.ui

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mbariah.wallpapers.models.Photo
import com.mbariah.wallpapers.network.ImagesAPI
import com.mbariah.wallpapers.utils.Logger
import kotlinx.coroutines.launch
import retrofit2.await
import javax.inject.Inject


class HomeViewModel @Inject constructor(private val api: ImagesAPI) : ViewModel() {

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
        this.errorListener = View.OnClickListener { this.searchEmptyList(limit = "10") }
        searchEmptyList(limit = "10")
    }

    fun searchEmptyList(limit: String) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val results = api.getImages(page.toString(), limit).await()
                Logger.dt("Fetched from page $page")

                page = results.photos?.page?.plus(1) ?: 1

                //TODO() Find a better way to do this
                if (_imagesList.value == null) {
                    //"New Batch"
                    _imagesList.value = results.photos?.photo?.toMutableList()
                } else {
                    //Append to existing
                    results.photos?.photo?.let { _imagesList.value!!.addAll(it) }
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
                val results = api.searchImages(page, limit, search).await()
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


/*
operator fun <T> MutableLiveData<List<T>>.plusAssign(values: List<T>) {
    val value = this.value ?: arrayListOf()
    value.addAll(values)
    value
    this.value = value
}*/

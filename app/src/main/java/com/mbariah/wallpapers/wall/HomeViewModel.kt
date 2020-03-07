package com.mbariah.wallpapers.wall

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mbariah.wallpapers.network.ImagesAPI
import com.mbariah.wallpapers.models.Photo
import com.mbariah.wallpapers.utils.Logger
import kotlinx.coroutines.launch
import retrofit2.await
import javax.inject.Inject

class HomeViewModel : ViewModel {

    private val api: ImagesAPI

    //@Inject - Tells Dagger how to create instances of HomeViewModel
    @Inject
    constructor(api: ImagesAPI) : super() {
        this.api = api
        this._imagesList = MutableLiveData<List<Photo>>()
        this._isLoading = MutableLiveData<Boolean>(false)
        this._hasNetworkError = MutableLiveData<Boolean>(false)
        this.errorListener = View.OnClickListener { this.searchEmptyList() }
        searchEmptyList()
    }

    //LiveData Object
    private var _imagesList: MutableLiveData<List<Photo>>
    val imagesList: LiveData<List<Photo>> get() = _imagesList

    private var _isLoading: MutableLiveData<Boolean>
    val isLoading: LiveData<Boolean> get() = _isLoading

    private var _hasNetworkError: MutableLiveData<Boolean>
    val hasNetworkError: LiveData<Boolean> get() = _hasNetworkError

    val errorListener: View.OnClickListener

    fun searchEmptyList(page: String = "1", limit: String = "15") {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _imagesList.postValue(null)
                val results = api.getImages(page, limit).await()
                _imagesList.value = results.photos
                Logger.dt(results.photos?.size.toString())
                _isLoading.value = false
            } catch (e: Exception) {
                _isLoading.value = false
                _hasNetworkError.value = true
            }
        }
    }

    fun searchList(page: String, limit: String, search: String) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _imagesList.postValue(null)
                val results = api.searchImages(page, limit, search).await()
                _imagesList.value = results.photos
                _isLoading.value = false
            } catch (e: Exception) {
                _isLoading.value = false
                _hasNetworkError.value = true
            }
        }
    }


}
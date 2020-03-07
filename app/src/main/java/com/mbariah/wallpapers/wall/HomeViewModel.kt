package com.mbariah.wallpapers.wall

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mbariah.wallpapers.network.ImagesAPI
import com.mbariah.wallpapers.models.Photo
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
        this.errorListener = View.OnClickListener { this.getList() }
        getList()
    }

    private var _imagesList: MutableLiveData<List<Photo>>

    //LiveData Object
    val imagesList: LiveData<List<Photo>> get() = _imagesList

    val errorListener: View.OnClickListener

    private fun getList() {
        viewModelScope.launch {
            try {
                val results = api.getImages("1", "15").await()
                _imagesList.value = results.photos
            } catch (e: Exception) {
            }
        }
    }


    fun searchEmptyList(page: String, limit: String) {
        viewModelScope.launch {
            try {
                _imagesList.postValue(null)
                val results = api.getImages(page, limit).await()
                _imagesList.value = results.photos
            } catch (e: Exception) {
            }
        }
    }

    fun searchList(page: String, limit: String, search: String) {
        viewModelScope.launch {
            try {
                _imagesList.postValue(null)
                val results = api.searchImages(page, limit, search).await()
                _imagesList.value = results.photos
            } catch (e: Exception) {
            }
        }
    }


}
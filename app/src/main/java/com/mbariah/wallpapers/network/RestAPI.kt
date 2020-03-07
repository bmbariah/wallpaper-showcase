package com.mbariah.wallpapers.network


import com.mbariah.wallpapers.models.Results
import retrofit2.Call
import javax.inject.Inject

class RestAPI @Inject constructor(imagesService: ImageService) : ImagesAPI {

    private val api: ImageService = imagesService

    //notice the override
    override suspend fun getImages(page: String, limit: String): Call<Results> {
        return api.getImages(page, limit)
    }

    override suspend fun searchImages(page: String, limit: String, search: String): Call<Results> {
        return api.searchImages(page, limit, search);
    }

}
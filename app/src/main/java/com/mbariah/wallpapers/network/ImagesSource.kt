package com.mbariah.wallpapers.network

import com.mbariah.wallpapers.models.Results
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import javax.inject.Inject

class ImagesSource {

    private val api: ImagesAPI

    @Inject
    constructor(api: ImagesAPI) {
        this.api = api
    }

    //notice the override
    suspend fun getImages(page: String, limit: String): Results {
        return api.getImages(page, limit)
    }

    suspend fun searchImages(page: String, limit: String, search: String): Results {
        return api.searchImages(page, limit, search)
    }

}

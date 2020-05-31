package com.mbariah.wallpapers.network

import com.mbariah.wallpapers.models.Results
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ImagesSource {

    private val api: ImagesAPI

    @Inject
    constructor(api: ImagesAPI) {
        this.api = api
    }

    /*
      - Run in Dispatchers.IO thread for network tasks
      - Perform blocking network IO
    */

    suspend fun getImages(page: String, limit: String): Results {
        return withContext(Dispatchers.IO) {
            api.getImages(page, limit)
        }
    }

    suspend fun searchImages(page: String, limit: String, search: String): Results {
        return withContext(Dispatchers.IO){
            api.searchImages(page, limit, search)
        }
    }

}

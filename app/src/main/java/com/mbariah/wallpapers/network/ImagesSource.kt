package com.mbariah.wallpapers.network

import com.mbariah.wallpapers.models.ImageResults
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ImagesSource @Inject constructor(private val api: ImagesAPI) {

    /*
      - Run in Dispatchers.IO thread for network tasks
      - Perform blocking network IO
    */
    suspend fun getImages(page: String, limit: String): ImageResults {
        return withContext(Dispatchers.IO) {
            api.getImages(page, limit)
        }
    }

    suspend fun searchImages(page: String, limit: String, search: String): ImageResults {
        return withContext(Dispatchers.IO){
            api.searchImages(page, limit, search)
        }
    }

}

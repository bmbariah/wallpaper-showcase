package com.mbariah.wallpapers.network

import com.mbariah.wallpapers.models.Results
import retrofit2.Call

/**
 * Images API
 *
 * @author Mbariah.
 */

interface ImagesAPI {
    suspend fun getImages(page: String, limit: String): Call<Results>
    suspend fun searchImages(page: String, limit: String, search: String): Call<Results>

}
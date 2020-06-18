package com.mbariah.wallpapers.network

import com.mbariah.wallpapers.models.ImageResults
import retrofit2.http.GET
import retrofit2.http.Query

interface ImagesAPI {

    @GET("photos")
    suspend fun getImages(
        @Query("page") page: String = "1",
        @Query("per_page") perPage: String
    ): ImageResults

    @GET("rest")
    suspend fun searchImages(
        @Query("page") page: String = "1",
        @Query("per_page") perPage: String,
        @Query("text") query: String,
        @Query("method") method: String = "flickr.photos.search"
    ): ImageResults


}
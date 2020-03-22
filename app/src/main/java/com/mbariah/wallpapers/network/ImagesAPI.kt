package com.mbariah.wallpapers.network

import com.mbariah.wallpapers.models.Results
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ImagesAPI {

    @GET("rest")
    suspend fun getImages(
        @Query("page") page: String = "1",
        @Query("per_page") perPage: String,
        @Query("method") method: String = "flickr.groups.pools.getPhotos",
        //@Query("text") query: String = "android wallpapers"
        @Query("group_id") tags: String = "14609814@N24"
    ): Results

    @GET("rest")
    suspend fun searchImages(
        @Query("page") page: String = "1",
        @Query("per_page") perPage: String,
        @Query("text") query: String,
        @Query("method") method: String = "flickr.photos.search"
    ): Results


}
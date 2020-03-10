package com.mbariah.wallpapers.network

import com.mbariah.wallpapers.models.Results
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageService {

    @GET("rest")
    fun getImages(
        @Query("page") page: String = "1",
        @Query("per_page") perPage: String,
        @Query("method") method: String = "flickr.groups.pools.getPhotos",
        //@Query("text") query: String = "android wallpapers"
        @Query("group_id") tags: String = "14609814@N24"
    ): Call<Results>

    @GET("rest")
    fun searchImages(
        @Query("page") page: String = "1",
        @Query("per_page") perPage: String,
        @Query("text") query: String,
        @Query("method") method: String = "flickr.photos.search"
    ): Call<Results>


}
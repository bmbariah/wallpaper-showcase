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
        @Query("method") method: String = "flickr.photos.search",
        //@Query("text") query: String = "android wallpapers"
        @Query("tags") tags: String = "4k wallpapers,hd wallpapers, 4k"
    ): Call<Results>

    @GET("rest")
    fun searchImages(
        @Query("page") page: String = "1",
        @Query("per_page") perPage: String,
        @Query("text") query: String,
        @Query("method") method: String = "flickr.photos.search"
    ): Call<Results>


}
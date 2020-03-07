package com.mbariah.wallpapers.network

import com.mbariah.wallpapers.models.Results
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageService {

    @GET("/v1/curated")
    fun getImages(
        @Query("page") page: String = "1",
        @Query("per_page") perPage: String
    ): Call<Results>

    @GET("/v1/search")
    fun searchImages(
        @Query("page") page: String = "1",
        @Query("per_page") perPage: String,
        @Query("query") query: String
        ): Call<Results>


}
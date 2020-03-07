package com.mbariah.wallpapers.network

import com.mbariah.wallpapers.models.Results
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageService {

    @GET("search")
    fun getImages(
        @Query("page") page: String = "1",
        @Query("per_page") perPage: String
    ): Call<Results>

    @GET("search")
    fun searchImages(
        @Query("page") page: String = "1",
        @Query("per_page") perPage: String,
        @Query("q") query: String
        ): Call<Results>


}
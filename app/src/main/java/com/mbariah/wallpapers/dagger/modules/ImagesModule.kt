package com.mbariah.wallpapers.dagger.modules


import com.mbariah.wallpapers.network.ImageService
import com.mbariah.wallpapers.network.ImagesAPI
import com.mbariah.wallpapers.network.RestAPI
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ImagesModule {

    @Provides
    @Singleton
    fun provideImagesAPI(api: ImageService): ImagesAPI = RestAPI(api)

    @Provides
    @Singleton
    fun provideImageServiceApi(retrofit: Retrofit): ImageService = retrofit.create(ImageService::class.java)

}

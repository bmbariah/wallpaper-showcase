package com.mbariah.wallpapers.dagger.modules

import com.mbariah.wallpapers.BuildConfig
import com.mbariah.wallpapers.network.ImagesAPI
import com.mbariah.wallpapers.network.ImagesSource
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
class NetworkModule() {

    private val API_KEY = BuildConfig.API_KEY
    private val URL = "https://api.flickr.com/services/"

    //init okhttp3, retrofit & cache? here
    @Provides
    @Reusable
    internal fun provideOkHttpClient(): OkHttpClient {

        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.HEADERS)

        val interceptor = Interceptor {
            val request = it.request()
            it.proceed(when (request.method) {
                "GET" -> {
                    val url = request.url
                    request.newBuilder()
                        .url(url.newBuilder()
                            .addQueryParameter("api_key", API_KEY)
                            .addQueryParameter("format", "json")
                            .addQueryParameter("nojsoncallback", "1")
                            .addQueryParameter("extras", "description, date_upload, date_taken, owner_name, icon_server, original_format, tags, machine_tags, o_dims, views, media, url_c, url_l, url_o")
                            .build())
                        .build()
                }
                else -> request
            })
        }

/*
        val interceptor = Interceptor {
            val url = it.request()
                .url
                .newBuilder()
                .build()
            val request = it.request()
                .newBuilder()
                .addHeader("X-API-Key", API_KEY)
                .url(url)
                .build()
            return@Interceptor it.proceed(request)
        }
*/

        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .retryOnConnectionFailure(false)
            .addInterceptor(interceptor)
            //.addInterceptor(logging)
            .build()
    }

    @Provides
    @Reusable
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {

        fun moshi(): Moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi()))
            .build()
    }

    @Provides
    @Singleton
    fun provideImagesApi(retrofit: Retrofit): ImagesAPI = retrofit.create(ImagesAPI::class.java)

    @Provides
    @Singleton
    fun provideImagesSource(api: ImagesAPI): ImagesSource = ImagesSource(api)



}
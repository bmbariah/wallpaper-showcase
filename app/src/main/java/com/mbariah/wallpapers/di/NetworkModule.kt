package com.mbariah.wallpapers.di

import com.mbariah.wallpapers.BuildConfig
import com.mbariah.wallpapers.network.ImagesAPI
import com.mbariah.wallpapers.network.ImagesSource
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    private val API_KEY = BuildConfig.API_KEY
    private val URL = "https://api.unsplash.com/"

    @Provides
    internal fun provideOkHttpClient(): OkHttpClient {

        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        val interceptor = Interceptor {
            val url = it.request()
                .url
                .newBuilder()
                .build()
            val request = it.request()
                .newBuilder()
                .addHeader("Authorization", API_KEY)
                .url(url)
                .build()
            return@Interceptor it.proceed(request)
        }

        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .retryOnConnectionFailure(false)
            .addInterceptor(interceptor)
            .addInterceptor(logging)
            .build()
    }

    @Provides
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
    fun provideImagesApi(retrofit: Retrofit): ImagesAPI = retrofit.create(ImagesAPI::class.java)

    @Provides
    fun provideImagesSource(api: ImagesAPI): ImagesSource = ImagesSource(api)

}
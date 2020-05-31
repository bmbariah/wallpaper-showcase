package com.mbariah.wallpapers

import android.app.ActivityManager
import android.app.Application
import android.content.Context
import android.util.Log
import com.mbariah.wallpapers.dagger.component.AppComponent
import com.mbariah.wallpapers.dagger.component.DaggerAppComponent
import com.mbariah.wallpapers.dagger.modules.NetworkModule
import com.squareup.picasso.LruCache
import com.squareup.picasso.Picasso

class App : Application(){

    //app init
    companion object{

        // appComponent lives in the Application class to share its lifecycle
        lateinit var appComponent: AppComponent
        lateinit  var appContext: Context

    }
    //dagger declaration
    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        appComponent = DaggerAppComponent
            .builder()
            .networkModule(NetworkModule())
            .build()


       /* val requestTransformer = Picasso.RequestTransformer { request ->
            Log.d("image request", request.toString())
            request
        }*/

        val builder = Picasso.Builder(this)
            .memoryCache(LruCache(getBytesForMemCache(20)))
            //.requestTransformer(requestTransformer)

        //One Picasso instance to share app lifecycle
        Picasso.setSingletonInstance(builder.build())
    }

    private fun getBytesForMemCache(percent: Int): Int {
        val mi = ActivityManager.MemoryInfo()
        val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        activityManager.getMemoryInfo(mi)
        val availableMemory = mi.availMem.toDouble()
        return (percent * availableMemory / 100).toInt()
    }
}
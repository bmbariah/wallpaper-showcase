package com.mbariah.wallpapers

import android.app.ActivityManager
import android.app.Application
import android.content.Context
import com.squareup.picasso.LruCache
import com.squareup.picasso.Picasso
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()

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
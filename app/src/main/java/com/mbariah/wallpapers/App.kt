package com.mbariah.wallpapers

import android.app.Application
import android.content.Context
import com.mbariah.wallpapers.dagger.component.AppComponent
import com.mbariah.wallpapers.dagger.component.DaggerAppComponent
import com.mbariah.wallpapers.dagger.modules.NetworkModule

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
    }
}
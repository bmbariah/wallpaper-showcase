package com.mbariah.wallpapers.dagger.component


import com.mbariah.wallpapers.dagger.modules.ImagesModule
import com.mbariah.wallpapers.dagger.modules.NetworkModule
import com.mbariah.wallpapers.network.ImageService
import com.mbariah.wallpapers.wall.HomeFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, ImagesModule::class])
//Application graph
interface AppComponent {

    // This tells Dagger that HomeFragment requests injection so the graph needs to
    // satisfy all the dependencies of the fields that HomeFragment is requesting.
    fun inject(homeFragment: HomeFragment)
    //fun inject(detailActivity: DetailActivity)
}
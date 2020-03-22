package com.mbariah.wallpapers.dagger.component


import com.mbariah.wallpapers.dagger.modules.NetworkModule
import com.mbariah.wallpapers.ui.HomeFragment
import dagger.Component
import javax.inject.Singleton

//Application graph


@Singleton
@Component(modules = [NetworkModule::class])
interface AppComponent {
    // This tells Dagger that HomeFragment requests injection so the graph needs to
    // satisfy all the dependencies of the fields that HomeFragment is requesting.
    fun inject(homeFragment: HomeFragment)
}
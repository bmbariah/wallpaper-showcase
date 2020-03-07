package com.mbariah.wallpapers.utils

import android.util.Log


object Logger {

    private const val TAG = "WallPaperX"

    /**
     * dt: Debug with Thread details.
     * Print current thread name plus given value.
     */
    fun dt(value: String) {
        Log.d(TAG, "Thread Name: ${Thread.currentThread().name} - $value")
    }
}
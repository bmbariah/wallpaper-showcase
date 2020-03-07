package com.mbariah.wallpapers.utils

import com.mbariah.wallpapers.models.Results

sealed class ResultState {
    class Success(val data: Results) : ResultState()
    class Error(val message: String?) : ResultState()
}
package com.mbariah.wallpapers.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Results (
    val totalResults: Long? = null,
    val page: Long? = null,
    val perPage: Long? = null,
    val photos: List<Photo>? = null,
    val nextPage: String? = null
): Parcelable

@Parcelize
data class Photo (
    val id: Long? = null,
    val width: Long? = null,
    val height: Long? = null,
    val url: String? = null,
    val photographer: String? = null,
    val photographerURL: String? = null,
    val photographerID: Long? = null,
    val src: Src? = null
): Parcelable

@Parcelize
data class Src (
    val original: String? = null,
    val large2X: String? = null,
    val large: String? = null,
    val medium: String? = null,
    val small: String? = null,
    val portrait: String? = null,
    val landscape: String? = null,
    val tiny: String? = null
): Parcelable

package com.mbariah.wallpapers.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Results (
    val data: List<Photo>? = null,
    val meta: Meta? = null
): Parcelable

@Parcelize
data class Photo (
    val id: String? = null,
    val url: String? = null,
    val shortURL: String? = null,
    val views: Long? = null,
    val favorites: Long? = null,
    val source: String? = null,
    val purity: String? = null,
    val category: String? = null,
    val dimensionX: Long? = null,
    val dimensionY: Long? = null,
    val resolution: String? = null,
    val ratio: String? = null,
    val fileSize: Long? = null,
    val fileType: String? = null,
    val createdAt: String? = null,
    val colors: List<String>? = null,
    val path: String? = null,
    val thumbs: Thumbs? = null
): Parcelable


@Parcelize
data class Thumbs (
    val large: String? = null,
    val original: String? = null,
    val small: String? = null
): Parcelable


@Parcelize
data class Meta (
    val currentPage: Long? = null,
    val lastPage: Long? = null,
    val perPage: Long? = null,
    val total: Long? = null,
    val query: String? = null,
    val seed: String? = null
): Parcelable

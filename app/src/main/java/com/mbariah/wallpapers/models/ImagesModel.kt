package com.mbariah.wallpapers.models

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Results(
    @Json(name = "stat")
    val stat: String? = null,

    @Json(name = "photos")
    val photos: Photos? = null
) : Parcelable

@Parcelize
data class Photos(
    @Json(name = "perpage")
    val perpage: Int? = null,

    @Json(name = "total")
    val total: Int? = null,

    @Json(name = "pages")
    val pages: Int? = null,

    @Json(name = "photo")
    val photo: List<Photo>? = null,

    @Json(name = "page")
    val page: Int? = null
) : Parcelable

@Parcelize
data class Photo(
    @Json(name = "server")
    val server: String? = null,

    @Json(name = "media_status")
    val mediaStatus: String? = null,

    @Json(name = "machine_tags")
    val machineTags: String? = null,

    @Json(name = "width_c")
    val widthC: Int? = null,

    @Json(name = "isfriend")
    val isfriend: Int? = null,

    @Json(name = "description")
    val description: Description? = null,

    @Json(name = "accuracy")
    val accuracy: Int? = null,

    @Json(name = "secret")
    val secret: String? = null,

    @Json(name = "media")
    val media: String? = null,

    @Json(name = "title")
    val title: String? = null,

    @Json(name = "url_z")
    val urlZ: String? = null,

    @Json(name = "height_l")
    val heightL: Int? = null,

    @Json(name = "height_m")
    val heightM: Int? = null,

    @Json(name = "height_n")
    val heightN: Int? = null,

    @Json(name = "height_o")
    val heightO: Int? = null,

    @Json(name = "datetaken")
    val datetaken: String? = null,

    @Json(name = "height_c")
    val heightC: Int? = null,

    @Json(name = "iconserver")
    val iconserver: String? = null,

    @Json(name = "ispublic")
    val ispublic: Int? = null,

    @Json(name = "originalsecret")
    val originalsecret: String? = null,

    @Json(name = "context")
    val context: Int? = null,

    @Json(name = "farm")
    val farm: Int? = null,

    @Json(name = "width_l")
    val widthL: Int? = null,

    @Json(name = "id")
    val id: String? = null,

    @Json(name = "width_n")
    val widthN: Int? = null,

    @Json(name = "pathalias")
    val pathalias: String? = null,

    @Json(name = "width_m")
    val widthM: Int? = null,

    @Json(name = "views")
    val views: String? = null,

    @Json(name = "width_o")
    val widthO: Int? = null,

    @Json(name = "owner")
    val owner: String? = null,

    @Json(name = "iconfarm")
    val iconfarm: Int? = null,

    @Json(name = "ownername")
    val ownername: String? = null,

    @Json(name = "width_z")
    val widthZ: Int? = null,

    @Json(name = "isfamily")
    val isfamily: Int? = null,

    @Json(name = "dateupload")
    val dateupload: String? = null,

    @Json(name = "tags")
    val tags: String? = null,

    @Json(name = "url_c")
    val urlC: String? = null,

    @Json(name = "originalformat")
    val originalformat: String? = null,

    @Json(name = "url_n")
    val urlN: String? = null,

    @Json(name = "url_o")
    val urlO: String? = null,

    @Json(name = "height_z")
    val heightZ: Int? = null,

    @Json(name = "url_l")
    val urlL: String? = null,

    @Json(name = "lastupdate")
    val lastupdate: String? = null,

    @Json(name = "url_m")
    val urlM: String? = null,

    @Json(name = "datetakenunknown")
    val datetakenunknown: String? = null,

    @Json(name = "o_height")
    val oHeight: String? = null,

    @Json(name = "o_width")
    val oWidth: String? = null,

    @Json(name = "geo_is_friend")
    val geoIsFriend: Int? = null,

    @Json(name = "place_id")
    val placeId: String? = null,

    @Json(name = "woeid")
    val woeid: String? = null

) : Parcelable


@Parcelize
data class Description(
    @Json(name = "_content")
    val content: String? = null
) : Parcelable



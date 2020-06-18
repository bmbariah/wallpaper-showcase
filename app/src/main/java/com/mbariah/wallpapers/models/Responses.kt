package com.mbariah.wallpapers.models

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

typealias ImageResults = List<Photo>

@Parcelize
data class Photo(

	@Json(name="color")
	val color: String? = null,

	@Json(name="sponsorship")
	val sponsorship: Sponsorship? = null,

	@Json(name="created_at")
	val createdAt: String? = null,

	@Json(name="description")
	val description: String? = null,

	@Json(name="liked_by_user")
	val likedByUser: Boolean? = null,

	@Json(name="urls")
	val urls: Urls? = null,

	@Json(name="alt_description")
	val altDescription: String? = null,

	@Json(name="updated_at")
	val updatedAt: String? = null,

	@Json(name="width")
	val width: Int? = null,

	@Json(name="links")
	val links: Links? = null,

	@Json(name="id")
	val id: String? = null,

	@Json(name="user")
	val user: User? = null,

	@Json(name="height")
	val height: Int? = null,

	@Json(name="likes")
	val likes: Int? = null
) : Parcelable

@Parcelize
data class Sponsorship(

	@Json(name="sponsor")
	val sponsor: Sponsor? = null,

	@Json(name="tagline")
	val tagline: String? = null,

	@Json(name="impression_urls")
	val impressionUrls: List<String?>? = null
) : Parcelable

@Parcelize
data class Sponsor(

	@Json(name="total_photos")
	val totalPhotos: Int? = null,

	@Json(name="accepted_tos")
	val acceptedTos: Boolean? = null,

	@Json(name="twitter_username")
	val twitterUsername: String? = null,

	@Json(name="last_name")
	val lastName: String? = null,

	@Json(name="bio")
	val bio: String? = null,

	@Json(name="total_likes")
	val totalLikes: Int? = null,

	@Json(name="portfolio_url")
	val portfolioUrl: String? = null,

	@Json(name="profile_image")
	val profileImage: ProfileImage? = null,

	@Json(name="updated_at")
	val updatedAt: String? = null,

	@Json(name="name")
	val name: String? = null,

	@Json(name="links")
	val links: Links? = null,

	@Json(name="total_collections")
	val totalCollections: Int? = null,

	@Json(name="id")
	val id: String? = null,

	@Json(name="first_name")
	val firstName: String? = null,

	@Json(name="instagram_username")
	val instagramUsername: String? = null,

	@Json(name="username")
	val username: String? = null
) : Parcelable

@Parcelize
data class User(

	@Json(name="total_photos")
	val totalPhotos: Int? = null,

	@Json(name="accepted_tos")
	val acceptedTos: Boolean? = null,

	@Json(name="last_name")
	val lastName: String? = null,

	@Json(name="bio")
	val bio: String? = null,

	@Json(name="total_likes")
	val totalLikes: Int? = null,

	@Json(name="portfolio_url")
	val portfolioUrl: String? = null,

	@Json(name="profile_image")
	val profileImage: ProfileImage? = null,

	@Json(name="updated_at")
	val updatedAt: String? = null,

	@Json(name="name")
	val name: String? = null,

	@Json(name="location")
	val location: String? = null,

	@Json(name="links")
	val links: Links? = null,

	@Json(name="total_collections")
	val totalCollections: Int? = null,

	@Json(name="id")
	val id: String? = null,

	@Json(name="first_name")
	val firstName: String? = null,

	@Json(name="instagram_username")
	val instagramUsername: String? = null,

	@Json(name="username")
	val username: String? = null
) : Parcelable

@Parcelize
data class Links(

	@Json(name="followers")
	val followers: String? = null,

	@Json(name="portfolio")
	val portfolio: String? = null,

	@Json(name="following")
	val following: String? = null,

	@Json(name="self")
	val self: String? = null,

	@Json(name="html")
	val html: String? = null,

	@Json(name="photos")
	val photos: String? = null,

	@Json(name="likes")
	val likes: String? = null,

	@Json(name="download")
	val download: String? = null,

	@Json(name="download_location")
	val downloadLocation: String? = null
) : Parcelable

@Parcelize
data class Urls(

	@Json(name="small")
	val small: String? = null,

	@Json(name="thumb")
	val thumb: String? = null,

	@Json(name="raw")
	val raw: String? = null,

	@Json(name="regular")
	val regular: String? = null,

	@Json(name="full")
	val full: String? = null
) : Parcelable

@Parcelize
data class ProfileImage(

	@Json(name="small")
	val small: String? = null,

	@Json(name="large")
	val large: String? = null,

	@Json(name="medium")
	val medium: String? = null
) : Parcelable

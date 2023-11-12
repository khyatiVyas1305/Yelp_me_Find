package com.example.yelpmefind
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class ApiResponse(
    @SerializedName("businesses")
    val businesses: List<Business>
    )

@Parcelize
data class Business(
    val id: String,
    val name: String,
    @SerializedName("image_url")
    val imageUrl: String,
    val url: String,
    @SerializedName("review_count")
    val reviewCount: Int,
    val categories: List<Category>,
    val rating: Double,
    val location: Location,
    val phone: String,
    val distance: Double
) : Parcelable

@Parcelize
data class Category(
    val alias: String,
    val title: String
) : Parcelable

@Parcelize
data class Location(
    val address1: String,
    val address2: String?,
    val address3: String?,
    val city: String,
    val country: String,
    val state: String,
    @SerializedName("display_address")
    val displayAddress: List<String>
) : Parcelable
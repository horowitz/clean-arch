package com.example.danielhorowitz.clean.data.model
import com.google.gson.annotations.SerializedName

/**
 * Created by danielhorowitz on 14/03/18.
 */
data class PlaceDetailsDTO(val result: PlaceDetailsResultDTO,
                           val status: String = "")

data class PhotosItem(
        @SerializedName("photo_reference")
        val photoReference: String = "",
        val width: Int = 0,
        @SerializedName("html_attributions")
        val htmlAttributions: List<String>?,
        val height: Int = 0)


data class Viewport(val southwest: Southwest,
                    val northeast: Northeast)


data class Open(val time: String = "",
                val day: Int = 0)


data class OpeningHours(
        @SerializedName("open_now")
        val openNow: Boolean = false,
        val periods: List<PeriodsItem>?,
        @SerializedName("weekday_text")
        val weekdayText: List<String>?)


data class PlaceDetailsResultDTO(val utcOffset: Int = 0,
                                 @SerializedName("formatted_address")
                                 val formattedAddress: String = "",
                                 val types: List<String>?,
                                 val website: String = "",
                                 val icon: String = "",
                                 val rating: Double = 0.0,
                                 @SerializedName("address_components")
                                 val addressComponents: List<AddressComponentsItem>?,
                                 val photos: List<PhotosItem>?,
                                 val url: String = "",
                                 val reference: String = "",
                                 val reviews: List<ReviewsItem>?,
                                 val scope: String = "",
                                 val name: String = "",
                                 @SerializedName("opening_hours")
                                 val openingHours: OpeningHours,
                                 val geometry: Geometry,
                                 val vicinity: String = "",
                                 val id: String = "",
                                 @SerializedName("adr_address")
                                 val adrAddress: String = "",
                                 @SerializedName("formatted_phone_number")
                                 val formattedPhoneNumber: String = "",
                                 @SerializedName("international_phone_number")
                                 val internationalPhoneNumber: String = "",
                                 @SerializedName("place_id")
                                 val placeId: String = "")


data class Geometry(val viewport: Viewport,
                    val location: Location)


data class ReviewsItem(
        @SerializedName("author_name")
        val authorName: String = "",
        @SerializedName("profile_photo_url")
        val profilePhotoUrl: String = "",
        @SerializedName("author_url")
        val authorUrl: String = "",
        val rating: Int = 0,
        val language: String = "",
        val text: String = "",
        val time: Int = 0,
        @SerializedName("relative_time_description")
        val relativeTimeDescription: String = "")


data class Southwest(val lng: Double = 0.0,
                     val lat: Double = 0.0)


data class PeriodsItem(val close: Close,
                       val open: Open)


data class AddressComponentsItem(val types: List<String>?,
                                 @SerializedName("short_name")
                                 val shortName: String = "",
                                 @SerializedName("long_name")
                                 val longName: String = "")


data class Close(val time: String = "",
                 val day: Int = 0)


data class Northeast(val lng: Double = 0.0,
                     val lat: Double = 0.0)


data class Location(val lng: Double = 0.0,
                    val lat: Double = 0.0)



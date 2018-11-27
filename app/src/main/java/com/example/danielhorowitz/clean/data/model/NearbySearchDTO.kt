package com.example.danielhorowitz.clean.data.model

import com.google.gson.annotations.SerializedName

data class NearbySearchDTO(
        @SerializedName("next_page_token")
        val nextPageToken: String = "",
        val results: List<NearbyPlaceResultDTO>?,
        val status: String = "") {
    constructor() : this("", null, "")
}


data class NearbyPlaceResultDTO(val reference: String = "",
                                val types: List<String>?,
                                val scope: String = "",
                                val icon: String = "",
                                val name: String = "",
                                @SerializedName("opening_hours")
                                val openingHours: OpeningHours? = null,
                                val rating: Double = 0.0,
                                val geometry: Geometry? = null,
                                val vicinity: String = "",
                                val id: String = "",
                                val photos: List<PhotosItem>? = null,
                                @SerializedName("place_id")
                                val placeId: String = "") {
}




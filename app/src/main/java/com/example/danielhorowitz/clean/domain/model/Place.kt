package com.example.danielhorowitz.clean.domain.model

import com.example.danielhorowitz.clean.data.model.ReviewsItem
import com.example.danielhorowitz.clean.data.network.NetworkConfig

/**
 * Created by danielhorowitz on 17/03/18.
 */

data class Place(
    var name: String = "",
    var images: MutableList<String>,
    var rating: Double = 0.0,
    var vicinity: String = "",
    var id: String = "",
    val reviews: MutableList<ReviewsItem> = mutableListOf(),
    var openNow: Boolean = false,
    var distance: Double = 0.0,
    var voters: MutableList<String> = mutableListOf(),
    var votes: Int = 0
) {
    constructor() : this("", mutableListOf<String>(), 0.0, "", "", mutableListOf())

    fun addPhotoFromGooglePlaces(photoReference: String) {
        val url =
            "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=%s&key=${NetworkConfig.GOOGLE_MAPS_KEY}"
        images.add(String.format(url, photoReference))
    }
}



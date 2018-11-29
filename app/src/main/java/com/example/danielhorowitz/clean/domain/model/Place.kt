package com.example.danielhorowitz.clean.domain.model

import android.os.Parcelable
import com.example.danielhorowitz.clean.data.network.NetworkConfig
import kotlinx.android.parcel.Parcelize

/**
 * Created by danielhorowitz on 17/03/18.
 */

@Parcelize
data class Place(
    var name: String = "",
    var images: MutableList<String>,
    var rating: Double = 0.0,
    var vicinity: String = "",
    var id: String = "",
    var openNow: Boolean = false,
    var distance: Double = 0.0,
    var voters: MutableList<String> = mutableListOf(),
    var votes: Int = 0
) : Parcelable {
    constructor() : this("", mutableListOf<String>(), 0.0, "", "")

    fun addPhotoFromGooglePlaces(photoReference: String) {
        val url =
            "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=%s&key=${NetworkConfig.GOOGLE_MAPS_KEY}"
        images.add(String.format(url, photoReference))
    }
}



package com.example.danielhorowitz.clean.domain.model

import android.os.Parcelable
import com.example.danielhorowitz.clean.data.network.NetworkConfig
import kotlinx.android.parcel.Parcelize

/**
 * Created by danielhorowitz on 17/03/18.
 */

@Parcelize
data class Place(
    val name: String = "",
    val images: MutableList<String> = mutableListOf(),
    val rating: Double = 0.0,
    var distance: Double = 0.0,
    val vicinity: String = "",
    val id: String = "",
    val openNow: Boolean = false) : Parcelable {
    fun addPhotoFromGooglePlaces(photoReference: String) {
        val url =
            "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=%s&key=${NetworkConfig.GOOGLE_PLACES_API_KEY}"
        images.add(String.format(url, photoReference))
    }
}



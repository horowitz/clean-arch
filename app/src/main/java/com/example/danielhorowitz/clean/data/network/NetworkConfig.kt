package com.example.danielhorowitz.clean.data.network

/**
 * Created by danielhorowitz on 10/02/2018.
 */
object NetworkConfig {
    const val GOOGLE_MAPS_API = "https://maps.googleapis.com"
    const val GOOGLE_MAPS_KEY = "your_maps_key"

    const val GOOGLE_PLACES_API_PATH = "maps/api/place/details/json"
    const val GOOGLE_NEARBY_SEARCH_API_PATH = "maps/api/place/nearbysearch/json"

    object Places{
        const val KEY_PARAM = "key"
        const val PLACE_ID_PARAM = "placeid"
        const val TYPE_PARAM = "type"
        const val LOCATION_PARAM = "location"
        const val RADIUS_PARAM = "radius"
        const val RANK_BY_PARAM = "rankBy"
    }

}
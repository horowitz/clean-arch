package com.example.danielhorowitz.clean.data.repository

import com.example.danielhorowitz.clean.data.model.NearbySearchDTO
import com.example.danielhorowitz.clean.data.model.PlaceDetailsDTO
import com.example.danielhorowitz.clean.data.network.GooglePlacesAPI
import com.example.danielhorowitz.clean.data.network.NetworkConfig
import com.example.danielhorowitz.clean.data.repository.GooglePlacesRepository
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Created by danielhorowitz on 15/03/18.
 */
class GooglePlacesRepositoryImpl(private val googlePlacesAPI: GooglePlacesAPI) : GooglePlacesRepository {
    override fun nearbySearch(location: String, radius: Double, type: String, rankBy: String): Single<NearbySearchDTO> {
        return googlePlacesAPI.nearbySearch(location, radius, type,rankBy, NetworkConfig.GOOGLE_MAPS_KEY)
    }

    override fun getPlacesDetails(placeId: String): Observable<PlaceDetailsDTO> =
            googlePlacesAPI.getPlaceDetails(NetworkConfig.GOOGLE_MAPS_KEY, placeId)

}
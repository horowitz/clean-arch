package com.example.danielhorowitz.clean.data.repository
import com.example.danielhorowitz.clean.data.model.NearbySearchDTO
import com.example.danielhorowitz.clean.data.model.PlaceDetailsDTO
import io.reactivex.Single

/**
 * Created by danielhorowitz on 15/03/18.
 */
interface GooglePlacesRepository{
    fun getPlacesDetails(placeId: String): Single<PlaceDetailsDTO>

    fun nearbySearch(location: String, radius: Double, type: String, rankBy: String): Single<NearbySearchDTO>
}
package com.example.danielhorowitz.clean.domain

import com.example.danielhorowitz.clean.domain.model.NearbyPlaces
import io.reactivex.Single

/**
 * Created by danielhorowitz on 16/03/2018.
 */
interface PlacesInteractor {
    fun fetchNearbyPlaces(latitude: Double, longitude: Double): Single<NearbyPlaces>
}

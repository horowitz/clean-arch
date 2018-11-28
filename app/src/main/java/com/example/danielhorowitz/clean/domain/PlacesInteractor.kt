package com.example.danielhorowitz.clean.domain

import com.example.danielhorowitz.clean.domain.model.Place
import io.reactivex.Single

/**
 * Created by danielhorowitz on 16/03/2018.
 */
interface PlacesInteractor{
    fun fetchNearbyRestaurants(latitude: Double, longitude: Double): Single<List<Place>>
}

package com.example.danielhorowitz.clean

import com.example.danielhorowitz.clean.domain.model.Place

/**
 * Created by danielhorowitz on 8/10/17.
 */
interface Navigator {
    fun navigateToPlaceDetails(place: Place)
}
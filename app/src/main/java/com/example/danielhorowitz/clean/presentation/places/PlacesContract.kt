package com.example.danielhorowitz.clean.presentation.places

import com.example.danielhorowitz.clean.domain.model.Location
import com.example.danielhorowitz.clean.domain.model.NearbyPlaces
import com.example.danielhorowitz.clean.domain.model.Place
import com.example.danielhorowitz.clean.presentation.common.BasePresenter
import com.example.danielhorowitz.clean.presentation.common.BaseView
import io.reactivex.Single

/**
 * Created by danielhorowitz on 16/03/2018.
 */
interface PlacesContract {
    interface View : BaseView {
        fun showPlaces(places: NearbyPlaces)
        fun getCurrentLocation(): Single<Location>
        fun navigateToPlaceDetails(place: Place)
    }

    interface Presenter: BasePresenter {
        fun fetchNearbyPlaces()
        fun onPlaceClicked(place: Place)
    }
}
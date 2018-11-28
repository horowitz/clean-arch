package com.example.danielhorowitz.clean.presentation

import com.example.danielhorowitz.clean.domain.model.Location
import com.example.danielhorowitz.clean.domain.model.Place
import com.example.danielhorowitz.clean.presentation.common.BaseView
import io.reactivex.Single

/**
 * Created by danielhorowitz on 16/03/2018.
 */
interface PlacesContract {
    interface View : BaseView {
        fun showPlaces(place: List<Place>)
        fun getCurrentLocation(): Single<Location>
    }

    interface Presenter {
        fun onViewReady()
    }
}
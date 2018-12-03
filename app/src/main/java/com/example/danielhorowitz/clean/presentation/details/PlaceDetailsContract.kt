package com.example.danielhorowitz.clean.presentation.details

import com.example.danielhorowitz.clean.domain.model.Place
import com.example.danielhorowitz.clean.domain.model.PlaceDetails
import com.example.danielhorowitz.clean.presentation.common.BasePresenter
import com.example.danielhorowitz.clean.presentation.common.BaseView

interface PlaceDetailsContract {
    interface View : BaseView {
        fun showPlaceInfo(placeDetails: PlaceDetails)
    }

    interface Presenter : BasePresenter {
        fun fetchPlaceDetails(place: Place?)
    }
}
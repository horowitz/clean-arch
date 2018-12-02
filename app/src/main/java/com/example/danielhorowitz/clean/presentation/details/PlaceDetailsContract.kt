package com.example.danielhorowitz.clean.presentation.details

import com.example.danielhorowitz.clean.domain.model.Place
import com.example.danielhorowitz.clean.presentation.common.BasePresenter
import com.example.danielhorowitz.clean.presentation.common.BaseView

interface PlaceDetailsContract {
    interface View : BaseView {
        fun showPlaceInfo(place: Place)
    }

    interface Presenter : BasePresenter {
        fun fetchPlaceDetails(placeId: String?)
    }
}
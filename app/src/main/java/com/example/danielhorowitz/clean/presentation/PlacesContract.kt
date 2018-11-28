package com.example.danielhorowitz.clean.presentation

import com.example.danielhorowitz.clean.domain.model.Place
import com.example.danielhorowitz.clean.presentation.common.BaseView

/**
 * Created by danielhorowitz on 16/03/2018.
 */
interface PlacesContract {
    interface View : BaseView {
        fun showCompletionIndicator()
        fun showSavingIndicator()
        fun updatePlaces(place: Place)
    }

    interface Presenter {

    }
}
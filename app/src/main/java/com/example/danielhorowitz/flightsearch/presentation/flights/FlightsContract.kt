package com.example.danielhorowitz.flightsearch.presentation.flights

import com.example.danielhorowitz.flightsearch.presentation.common.BasePresenter
import com.example.danielhorowitz.flightsearch.presentation.common.BaseView
import com.example.danielhorowitz.flightsearch.presentation.model.Flight

interface FlightsContract{
    interface Presenter: BasePresenter{
        fun fetchFlights()
    }
    interface View: BaseView{
        fun showFlights(expectedFlights: List<Flight>)
    }
}
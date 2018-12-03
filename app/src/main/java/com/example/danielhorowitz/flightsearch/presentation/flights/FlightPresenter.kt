package com.example.danielhorowitz.flightsearch.presentation.flights

import com.example.danielhorowitz.flightsearch.domain.flights.FlightsInteractor
import com.example.danielhorowitz.flightsearch.presentation.common.RxPresenter
import io.reactivex.Scheduler

class FlightPresenter(
    private val view: FlightsContract.View,
    private val interactor: FlightsInteractor,
    observeOn: Scheduler,
    subscribeOn: Scheduler
) : RxPresenter(observeOn, subscribeOn), FlightsContract.Presenter {

}
package com.example.danielhorowitz.clean.presentation.places

import com.example.danielhorowitz.clean.Navigator
import com.example.danielhorowitz.clean.domain.PlacesInteractor
import com.example.danielhorowitz.clean.domain.model.Place
import com.example.danielhorowitz.clean.presentation.common.RxPresenter
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

/**
 * Created by danielhorowitz on 17/02/18.
 */
class PlacesPresenter(
    private val view: PlacesContract.View,
    private val interactor: PlacesInteractor,
    private val navigator: Navigator,
    observeOn: Scheduler,
    subscribeOn: Scheduler
) : RxPresenter(observeOn, subscribeOn), PlacesContract.Presenter {

    override fun onPlaceClicked(place: Place) {

    }

    override fun onViewReady() {
        view.showLoading()

        disposable = view.getCurrentLocation()
            .observeOn(Schedulers.io())
            .flatMap { location -> interactor.fetchNearbyRestaurants(location.latitude, location.longitude) }
            .observeOn(observeOn)
            .subscribeOn(subscribeOn)
            .subscribe({ places ->
                view.hideLoading()
                view.showPlaces(places)
            }, { error ->
                view.hideLoading()
                view.showError(
                    error,
                    TAG
                )
            })
    }

    companion object {
        private val TAG = PlacesPresenter::class.java.simpleName
    }
}

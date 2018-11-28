package com.example.danielhorowitz.clean.presentation

import com.example.danielhorowitz.clean.Navigator
import com.example.danielhorowitz.clean.domain.PlacesInteractor
import com.example.danielhorowitz.clean.presentation.common.RxPresenter
import io.reactivex.Scheduler

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

    override fun onLocationObtained(lat: Double, lng: Double) {
        view.showLoading()

        disposable = interactor.fetchNearbyRestaurants(lat, lng)
            .observeOn(observeOn)
            .subscribeOn(subscribeOn)
            .subscribe({ places ->
                view.hideLoading()
                view.showPlaces(places)
            }, { error ->
                view.hideLoading()
                view.showError(error, TAG)
            })
    }

    companion object {
        private val TAG = PlacesPresenter::class.java.simpleName
    }
}

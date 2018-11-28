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


    private fun fetchNearbyPlaces(longitude: Double, latitude: Double) {
        disposable = interactor.fetchNearbyRestaurants(latitude, longitude)
            .observeOn(observeOn)
            .subscribeOn(subscribeOn)
            .subscribe({
                view.hideLoading()
//                view.updatePlaces(it)
            }, {
                view.hideLoading()
                view.showError(it, TAG)
            })
    }

    companion object {
        private val TAG = PlacesPresenter::class.java.simpleName
    }
}

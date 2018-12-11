package com.example.danielhorowitz.clean.presentation.details

import com.example.danielhorowitz.clean.domain.PlaceDetailsInteractor
import com.example.danielhorowitz.clean.domain.model.Place
import com.example.danielhorowitz.clean.presentation.common.RxPresenter
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.rxkotlin.subscribeBy

class PlaceDetailsPresenter(
    private val view: PlaceDetailsContract.View,
    private val interactor: PlaceDetailsInteractor,
    observeOn: Scheduler,
    subscribeOn: Scheduler
) : RxPresenter(observeOn, subscribeOn), PlaceDetailsContract.Presenter {

    override fun fetchPlaceDetails(place: Place?) {
        view.showLoading()

        disposable = Single.fromCallable { requireNotNull(place) }
            .flatMap { interactor.fetchPlaceDetails(it) }
            .observeOn(observeOn)
            .subscribeOn(subscribeOn)
            .subscribeBy(
                onSuccess = { placeDetails ->
                    view.hideLoading()
                    view.showPlaceInfo(placeDetails)
                },
                onError = {
                    view.hideLoading()
                    view.showError(it)
                })
    }

}
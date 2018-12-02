package com.example.danielhorowitz.clean.presentation.details

import com.example.danielhorowitz.clean.domain.PlaceDetailsInteractor
import com.example.danielhorowitz.clean.presentation.common.RxPresenter
import io.reactivex.Scheduler
import io.reactivex.Single

class PlaceDetailsPresenter(
    private val view: PlaceDetailsContract.View,
    private val interactor: PlaceDetailsInteractor,
    observeOn: Scheduler,
    subscribeOn: Scheduler
) : RxPresenter(observeOn, subscribeOn), PlaceDetailsContract.Presenter {

    override fun fetchPlaceDetails(placeId: String?) {
        view.showLoading()

        disposable = Single.fromCallable { requireNotNull(placeId) }
            .flatMap { interactor.fetchPlaceDetails(it) }
            .observeOn(observeOn)
            .subscribeOn(subscribeOn)
            .subscribe({ placeDetails ->
                view.hideLoading()
                view.showPlaceInfo(placeDetails)
            }, {
                view.hideLoading()
                view.showError(it)
            })
    }

}
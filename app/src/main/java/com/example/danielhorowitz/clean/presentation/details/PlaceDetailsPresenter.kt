package com.example.danielhorowitz.clean.presentation.details

import com.example.danielhorowitz.clean.domain.PlaceDetailsInteractor
import com.example.danielhorowitz.clean.presentation.common.RxPresenter
import io.reactivex.Scheduler

class PlaceDetailsPresenter(
    private val view: PlaceDetailsContract.View,
    private val interactor: PlaceDetailsInteractor,
    observeOn: Scheduler,
    subscribeOn: Scheduler
) : RxPresenter(observeOn, subscribeOn), PlaceDetailsContract.Presenter {

    override fun fetchPlaceDetails(placeId: String?) {
        view.showLoading()

        disposable = interactor.fetchPlaceDetails(requireNotNull(placeId))
            .observeOn(observeOn)
            .subscribeOn(subscribeOn)
            .subscribe({
                view.hideLoading()
                view.showPlaceInfo(it.place)
            },{
                view.hideLoading()
                view.showError(it)
            })

    }

}
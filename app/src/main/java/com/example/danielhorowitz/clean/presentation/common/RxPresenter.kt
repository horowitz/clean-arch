package com.example.danielhorowitz.clean.presentation.common

import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable

/**
 * Created by danielhorowitz on 18/02/18.
 */
abstract class RxPresenter(val observeOn: Scheduler,
                           val subscribeOn: Scheduler) : BasePresenter {


    var disposable: Disposable? = null

    override fun destroy() {
        disposable?.dispose()
    }
}

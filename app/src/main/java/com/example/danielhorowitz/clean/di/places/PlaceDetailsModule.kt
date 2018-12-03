package com.example.danielhorowitz.clean.di.places

import android.app.Activity
import com.example.danielhorowitz.clean.data.repository.GooglePlacesRepository
import com.example.danielhorowitz.clean.di.PerActivity
import com.example.danielhorowitz.clean.domain.PlaceDetailsInteractor
import com.example.danielhorowitz.clean.domain.PlaceDetailsInteractorImpl
import com.example.danielhorowitz.clean.presentation.details.PlaceDetailsActivity
import com.example.danielhorowitz.clean.presentation.details.PlaceDetailsContract
import com.example.danielhorowitz.clean.presentation.details.PlaceDetailsPresenter
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import javax.inject.Named


@Module
abstract class PlaceDetailsModule {
    @Binds
    @PerActivity
    internal abstract fun provideView(activity: PlaceDetailsActivity): PlaceDetailsContract.View

    @Binds
    @PerActivity
    internal abstract fun provideActivity(activity: PlaceDetailsActivity): Activity

    @Module
    companion object {
        @Provides
        @PerActivity
        @JvmStatic
        internal fun provideInteractor(googlePlacesRepository: GooglePlacesRepository): PlaceDetailsInteractor =
            PlaceDetailsInteractorImpl(googlePlacesRepository)

        @Provides
        @PerActivity
        @JvmStatic
        internal fun providePresenter(
            view: PlaceDetailsContract.View,
            interactor: PlaceDetailsInteractor,
            @Named("observeOn") observeOn: Scheduler,
            @Named("subscribeOn") subscribeOn: Scheduler
        ): PlaceDetailsContract.Presenter =
            PlaceDetailsPresenter(view, interactor, observeOn, subscribeOn)
    }
}

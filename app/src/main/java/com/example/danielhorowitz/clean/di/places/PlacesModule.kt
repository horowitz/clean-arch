package com.example.danielhorowitz.clean.di.places

import android.app.Activity
import com.example.danielhorowitz.clean.data.repository.GooglePlacesRepository
import com.example.danielhorowitz.clean.di.PerActivity
import com.example.danielhorowitz.clean.domain.PlacesInteractor
import com.example.danielhorowitz.clean.domain.PlacesInteractorImpl
import com.example.danielhorowitz.clean.presentation.common.LocationHandler
import com.example.danielhorowitz.clean.presentation.common.LocationHandlerImpl
import com.example.danielhorowitz.clean.presentation.places.PlacesActivity
import com.example.danielhorowitz.clean.presentation.places.PlacesContract
import com.example.danielhorowitz.clean.presentation.places.PlacesPresenter
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import javax.inject.Named


@Module
abstract class PlacesModule {
    @Binds
    @PerActivity
    internal abstract fun provideView(activity: PlacesActivity): PlacesContract.View

    @Binds
    @PerActivity
    internal abstract fun provideActivity(activity: PlacesActivity): Activity

    @Module
    companion object {
        @Provides
        @PerActivity
        @JvmStatic
        internal fun provideInteractor(googlePlacesRepository: GooglePlacesRepository,
                                       @Named("subscribeOn") subscribeOn: Scheduler): PlacesInteractor = PlacesInteractorImpl(googlePlacesRepository,subscribeOn)

        @Provides
        @PerActivity
        @JvmStatic
        internal fun providePresenter(view: PlacesContract.View,
                                      interactor: PlacesInteractor,
                                      @Named("observeOn") observeOn: Scheduler,
                                      @Named("subscribeOn") subscribeOn: Scheduler): PlacesContract.Presenter =
            PlacesPresenter(
                view,
                interactor,
                observeOn,
                subscribeOn
            )

        @Provides
        @PerActivity
        @JvmStatic
        internal fun provideLocationHandler(activity: PlacesActivity): LocationHandler = LocationHandlerImpl(activity)
    }
}

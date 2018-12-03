package com.example.danielhorowitz.clean.di.app

import android.app.Activity
import com.example.danielhorowitz.clean.di.places.PlaceDetailsSubComponent
import com.example.danielhorowitz.clean.di.places.PlacesSubComponent
import com.example.danielhorowitz.clean.presentation.details.PlaceDetailsActivity
import com.example.danielhorowitz.clean.presentation.places.PlacesActivity
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

/**
 * This module contains all the binding to the sub component builders in the app
 */
@Module
abstract class BuildersModule {

    @Binds
    @IntoMap
    @ActivityKey(PlacesActivity::class)
    abstract fun bindPlacesActivityInjectorFactory(builder: PlacesSubComponent.Builder): AndroidInjector.Factory<out Activity>

    @Binds
    @IntoMap
    @ActivityKey(PlaceDetailsActivity::class)
    abstract fun bindPlaceDetailsActivityInjectorFactory(builder: PlaceDetailsSubComponent.Builder): AndroidInjector.Factory<out Activity>

}
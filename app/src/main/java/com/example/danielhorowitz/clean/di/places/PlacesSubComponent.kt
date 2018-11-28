package com.example.danielhorowitz.clean.di.places

import com.example.danielhorowitz.clean.di.ActivityModule
import com.example.danielhorowitz.clean.di.PerActivity
import com.example.danielhorowitz.clean.presentation.PlacesActivity
import dagger.Subcomponent
import dagger.android.AndroidInjector

/**
 * Created by danielhorowitz on 8/9/17.
 */
@PerActivity
@Subcomponent(modules = [(ActivityModule::class), (PlacesModule::class)])
interface PlacesSubComponent : AndroidInjector<PlacesActivity> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<PlacesActivity>()
}
package com.example.danielhorowitz.clean.di.places

import com.example.danielhorowitz.clean.di.ActivityModule
import com.example.danielhorowitz.clean.di.PerActivity
import com.example.danielhorowitz.clean.presentation.details.PlaceDetailsActivity
import dagger.Subcomponent
import dagger.android.AndroidInjector

@PerActivity
@Subcomponent(modules = [(ActivityModule::class), (PlaceDetailsModule::class)])
interface PlaceDetailsSubComponent : AndroidInjector<PlaceDetailsActivity> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<PlaceDetailsActivity>()
}
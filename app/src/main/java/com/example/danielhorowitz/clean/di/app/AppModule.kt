package com.example.danielhorowitz.clean.di.app

import android.content.Context
import com.example.danielhorowitz.clean.CleanApplication
import com.example.danielhorowitz.clean.di.places.PlacesSubComponent
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named

/**
 * Created by danielhorowitz on 8/9/17.
 */
@Module(
    subcomponents = [
        (PlacesSubComponent::class)
    ]
)
class AppModule {

    @Provides
    fun context(application: CleanApplication): Context = application.applicationContext

    @Provides
    @Named("observeOn")
    fun observeOnScheduler(): Scheduler = AndroidSchedulers.mainThread()

    @Provides
    @Named("subscribeOn")
    fun subscribeOnScheduler(): Scheduler = Schedulers.io()

}


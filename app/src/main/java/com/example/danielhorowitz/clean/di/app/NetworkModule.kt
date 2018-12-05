package com.example.danielhorowitz.clean.di.app

import com.example.danielhorowitz.clean.data.network.GooglePlacesAPI
import com.example.danielhorowitz.clean.data.network.RetrofitAdapter
import com.example.danielhorowitz.clean.data.repository.GooglePlacesRepository
import com.example.danielhorowitz.clean.data.repository.GooglePlacesRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class NetworkModule {
    @Provides
    fun googlePlacesRepository(): GooglePlacesRepository = GooglePlacesRepositoryImpl(
        RetrofitAdapter.googlePlacesRetrofit.create(GooglePlacesAPI::class.java)
    )
}
package com.example.danielhorowitz.flightsearch.di.app

import com.example.danielhorowitz.flightsearch.data.network.RetrofitAdapter
import dagger.Module
import dagger.Provides

@Module
class NetworkModule {
    @Provides
    fun retrofitAdapter(): RetrofitAdapter = RetrofitAdapter.instance()

    @Provides
    fun googlePlacesRepository(retrofitAdapter: RetrofitAdapter): GooglePlacesRepository = GooglePlacesRepositoryImpl(
        retrofitAdapter.getGooglePlacesRetrofit().create(
            GooglePlacesAPI::class.java
        )
    )
}
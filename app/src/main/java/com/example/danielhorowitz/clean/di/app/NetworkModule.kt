package com.example.danielhorowitz.clean.di.app

import com.example.danielhorowitz.clean.data.network.GooglePlacesAPI
import com.example.danielhorowitz.clean.data.network.RetrofitAdapter
import com.example.danielhorowitz.clean.data.repository.GooglePlacesRepository
import com.example.danielhorowitz.clean.data.repository.GooglePlacesRepositoryImpl
import dagger.Module
import dagger.Provides

/**
 * Created by danielhorowitz on 19/02/2018.
 */
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
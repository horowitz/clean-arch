package com.example.danielhorowitz.clean.data.network

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by danielhorowitz on 10/30/16.
 */

class RetrofitAdapter {

    fun getGooglePlacesRetrofit() = googlePlacesRetrofit

    companion object {
        private var instance: RetrofitAdapter? = null

        lateinit var googlePlacesRetrofit: Retrofit


        fun instance():  RetrofitAdapter  {
                if (instance == null) {
                    instance = RetrofitAdapter()
                    init()
                }
                return instance!!
        }

        private fun init() {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

            this.googlePlacesRetrofit = Retrofit.Builder()
                    .baseUrl(NetworkConfig.GOOGLE_MAPS_API)
                    .client(client)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }
    }

}
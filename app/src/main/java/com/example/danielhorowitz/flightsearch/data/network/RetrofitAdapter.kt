package com.example.danielhorowitz.flightsearch.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit


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
            val client = OkHttpClient.Builder().addInterceptor(interceptor)
                .readTimeout(15, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS)
                .build()

//            this.googlePlacesRetrofit = Retrofit.Builder()
//                    .baseUrl(NetworkConfig.GOOGLE_MAPS_API)
//                    .client(client)
//                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build()
        }
    }

}
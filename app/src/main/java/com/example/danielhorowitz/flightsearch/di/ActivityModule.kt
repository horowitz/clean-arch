package com.example.danielhorowitz.flightsearch.di
import android.app.Activity
import com.example.danielhorowitz.flightsearch.Navigator
import com.example.danielhorowitz.flightsearch.NavigatorImpl
import dagger.Module
import dagger.Provides

@Module
abstract class ActivityModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        internal fun provideNavigator(activity: Activity): Navigator = NavigatorImpl(activity)
    }
}
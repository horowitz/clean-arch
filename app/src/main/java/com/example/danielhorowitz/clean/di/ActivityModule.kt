package com.example.danielhorowitz.clean.di
import android.app.Activity
import com.example.danielhorowitz.clean.Navigator
import com.example.danielhorowitz.clean.NavigatorImpl
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
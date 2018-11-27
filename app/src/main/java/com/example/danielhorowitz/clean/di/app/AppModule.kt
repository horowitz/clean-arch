package com.dhorowitz.groupin.di.app

import android.arch.persistence.room.Room
import android.content.Context
import com.dhorowitz.groupin.GroupinApplication
import com.dhorowitz.groupin.data.GroupinDatabase
import com.dhorowitz.groupin.data.repository.DatabaseRepository
import com.dhorowitz.groupin.data.repository.DatabaseRepositoryImpl
import com.dhorowitz.groupin.data.repository.UserPreferencesRepository
import com.dhorowitz.groupin.data.repository.UserPreferencesRepositoryImpl
import com.dhorowitz.groupin.di.MainSubComponent
import com.dhorowitz.groupin.di.event.EventSubComponent
import com.dhorowitz.groupin.di.home.HomeSubComponent
import com.dhorowitz.groupin.di.onboarding.OnboardingSubComponent
import com.dhorowitz.groupin.di.places.PlacesSubComponent
import com.dhorowitz.groupin.di.splash.SplashSubComponent
import com.dhorowitz.groupin.di.voting.VotingSubComponent
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named

/**
 * Created by danielhorowitz on 8/9/17.
 */
@Module(subcomponents = [
    (SplashSubComponent::class),
    (OnboardingSubComponent::class),
    (PlacesSubComponent::class),
    (HomeSubComponent::class),
    (MainSubComponent::class),
    (EventSubComponent::class),
    (VotingSubComponent::class)
])
class AppModule {

    @Provides
    fun context(application: GroupinApplication): Context = application.applicationContext

    @Provides
    fun userPreferencesRepository(context: Context): UserPreferencesRepository = UserPreferencesRepositoryImpl(context)

    @Provides
    fun databaseRepository(database: GroupinDatabase): DatabaseRepository = DatabaseRepositoryImpl(database)

    @Provides
    fun provideDatabase(context: Context): GroupinDatabase = Room.databaseBuilder(context, GroupinDatabase::class.java, "db").build()

    @Provides
    @Named("observeOn")
    fun observeOnScheduler(): Scheduler = AndroidSchedulers.mainThread()

    @Provides
    @Named("subscribeOn")
    fun subscribeOnScheduler(): Scheduler = Schedulers.io()

}


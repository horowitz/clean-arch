package com.example.danielhorowitz.clean.presentation

import com.example.danielhorowitz.clean.Navigator
import com.example.danielhorowitz.clean.domain.PlacesInteractor
import io.reactivex.schedulers.Schedulers
import org.mockito.Mock

class PlacesPresenterTest{

    @Mock
    lateinit var view: PlacesContract.View
    @Mock
    lateinit var interactor: PlacesInteractor
    @Mock
    lateinit var navigator: Navigator

    val presenter by lazy {
        PlacesPresenter(view, interactor, navigator, Schedulers.trampoline(), Schedulers.trampoline())
    }

}
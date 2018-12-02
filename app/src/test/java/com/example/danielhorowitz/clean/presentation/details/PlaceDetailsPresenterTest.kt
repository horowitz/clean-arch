package com.example.danielhorowitz.clean.presentation.details

import com.example.danielhorowitz.clean.domain.PlaceDetailsInteractor
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class PlaceDetailsPresenterTest {

    @Mock
    lateinit var view: PlaceDetailsContract.View
    @Mock
    lateinit var interactor: PlaceDetailsInteractor

    private val placeId = "123"

    private val presenter: PlaceDetailsContract.Presenter by lazy {
        PlaceDetailsPresenter(view, interactor, Schedulers.trampoline(), Schedulers.trampoline())
    }

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun foo() {
        presenter.fetchPlaceDetails(placeId)
    }
}
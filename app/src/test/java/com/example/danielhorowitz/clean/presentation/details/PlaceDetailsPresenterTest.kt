package com.example.danielhorowitz.clean.presentation.details

import com.example.danielhorowitz.clean.domain.PlaceDetailsInteractor
import com.example.danielhorowitz.clean.domain.model.Place
import com.example.danielhorowitz.clean.domain.model.PlaceDetails
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
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

    private val place = Place(id = "123")

    private val presenter: PlaceDetailsContract.Presenter by lazy {
        PlaceDetailsPresenter(view, interactor, Schedulers.trampoline(), Schedulers.trampoline())
    }

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    private val details = PlaceDetails(place, emptyList())

    @Test
    fun `should show place info given fetch place details success`() {
        val placeDetails = details
        givenFetchPlaceDetailsSuccess(placeDetails)
        presenter.fetchPlaceDetails(place)

        verify(view).showPlaceInfo(placeDetails)
    }

    @Test
    fun `should show loading when fetching place details`() {
        givenFetchPlaceDetailsSuccess(details)
        presenter.fetchPlaceDetails(place)

        verify(view).showLoading()
    }

    @Test
    fun `should hide loading when place details fetched`() {
        givenFetchPlaceDetailsSuccess(details)
        presenter.fetchPlaceDetails(place)

        verify(view).hideLoading()
    }

    @Test
    fun `should show error when place details fails`() {
        val exception = Exception()
        givenFetchPlaceDetailsFails(exception)
        presenter.fetchPlaceDetails(place)

        verify(view).showError(exception)
    }

    @Test
    fun `should show error given null place id`() {
        givenFetchPlaceDetailsFails(Exception())
        presenter.fetchPlaceDetails(null)

        verify(view).showError(any(),any(), any())
    }

    @Test
    fun `should hide loading when place details fails`() {
        givenFetchPlaceDetailsFails(Exception())
        presenter.fetchPlaceDetails(place)

        verify(view).hideLoading()
    }

    private fun givenFetchPlaceDetailsFails(exception: Exception) {
        whenever(interactor.fetchPlaceDetails(place)).thenReturn(Single.error(exception))
    }

    private fun givenFetchPlaceDetailsSuccess(placeDetails: PlaceDetails) {
        whenever(interactor.fetchPlaceDetails(place)).thenReturn(Single.just(placeDetails))
    }
}
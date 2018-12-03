package com.example.danielhorowitz.clean.presentation

import com.example.danielhorowitz.clean.Navigator
import com.example.danielhorowitz.clean.domain.PlacesInteractor
import com.example.danielhorowitz.clean.domain.model.Location
import com.example.danielhorowitz.clean.domain.model.NearbyPlaces
import com.example.danielhorowitz.clean.domain.model.Place
import com.example.danielhorowitz.clean.presentation.places.PlacesContract
import com.example.danielhorowitz.clean.presentation.places.PlacesPresenter
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class PlacesPresenterTest {

    @Mock
    lateinit var view: PlacesContract.View
    @Mock
    lateinit var interactor: PlacesInteractor
    @Mock
    lateinit var navigator: Navigator

    private val presenter by lazy {
        PlacesPresenter(
            view,
            interactor,
            navigator,
            Schedulers.trampoline(),
            Schedulers.trampoline()
        )
    }

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `should navigate to details when place is clicked`() {
        val place = Place()
        presenter.onPlaceClicked(place)

        verify(navigator).navigateToPlaceDetails(place)
    }

    @Test
    fun `should show places given fetch nearby places successful`() {
        val expectedPlaces = NearbyPlaces(emptyList(),emptyList(),emptyList())
        givenFetchNearbyPlacesSuccessful(expectedPlaces)
        givenLocationObtained()

        presenter.fetchNearbyPlaces()

        verify(view).showPlaces(expectedPlaces)
    }

    @Test
    fun `should show error given fetch nearby places fails`() {
        val exception = Exception()
        givenFetchNearbyPlacesFails(exception)
        givenLocationObtained()

        presenter.fetchNearbyPlaces()

        verify(view).showError(eq(exception), any(), any())
    }

    @Test
    fun `should show loading when fetching nearby places`() {
        val expectedPlaces = NearbyPlaces(emptyList(),emptyList(),emptyList())
        givenFetchNearbyPlacesSuccessful(expectedPlaces)
        givenLocationObtained()

        presenter.fetchNearbyPlaces()

        verify(view).showLoading()
    }

    @Test
    fun `should hide loading when nearby places fetched`() {
        val expectedPlaces = NearbyPlaces(emptyList(),emptyList(),emptyList())
        givenFetchNearbyPlacesSuccessful(expectedPlaces)
        givenLocationObtained()

        presenter.fetchNearbyPlaces()

        verify(view).hideLoading()
    }

    private fun givenFetchNearbyPlacesFails(exception: Exception) {
        whenever(interactor.fetchNearbyPlaces(any(), any())).thenReturn(Single.error(exception))
    }

    private fun givenLocationObtained() {
        whenever(view.getCurrentLocation()).thenReturn(Single.just(Location(0.0, 0.0)))
    }

    private fun givenFetchNearbyPlacesSuccessful(places: NearbyPlaces) {
        whenever(interactor.fetchNearbyPlaces(any(), any())).thenReturn(Single.just(places))
    }

}
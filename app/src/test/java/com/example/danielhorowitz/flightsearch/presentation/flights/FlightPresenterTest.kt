package com.example.danielhorowitz.flightsearch.presentation.flights

import com.example.danielhorowitz.flightsearch.domain.flights.FlightsInteractor
import com.example.danielhorowitz.flightsearch.presentation.model.Flight
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class FlightPresenterTest {
    @Mock
    lateinit var view: FlightsContract.View

    @Mock
    lateinit var interactor: FlightsInteractor

    private val presenter by lazy {
        FlightPresenter(view,interactor)
    }

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `should show loading given search successful`(){
        val expectedFlights = emptyList<Flight>()
        givenSearchSuccessful(expectedFlights)

        presenter.fetchFlights()

        verify(view).showFlights(expectedFlights)
    }

    private fun givenSearchSuccessful(expectedFlights: List<Flight>) {
        whenever(interactor).
    }
}
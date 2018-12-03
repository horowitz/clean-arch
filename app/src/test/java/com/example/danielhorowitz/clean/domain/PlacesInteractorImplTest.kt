package com.example.danielhorowitz.clean.domain

import com.example.danielhorowitz.clean.data.model.NearbySearchDTO
import com.example.danielhorowitz.clean.data.model.PlaceDetailsDTO
import com.example.danielhorowitz.clean.data.repository.GooglePlacesRepository
import com.example.danielhorowitz.clean.fromJson
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class PlacesInteractorImplTest: BaseTest() {
    @Mock
    lateinit var googlePlacesRepository: GooglePlacesRepository

    private lateinit var nearbySearchDTO: NearbySearchDTO
    private lateinit var placeDetailsDTO: PlaceDetailsDTO

    private val interactor by lazy { PlacesInteractorImpl(googlePlacesRepository) }

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        nearbySearchDTO = getResource("nearby_search.json").fromJson(NearbySearchDTO::class.java)
        placeDetailsDTO = getResource("place_details.json").fromJson(PlaceDetailsDTO::class.java)
    }

    @Test
    fun `should return list of places given nearby search successful`() {
        givenNearbySearchSuccessful()

        val result = interactor.fetchNearbyPlaces(0.0, 0.0)
            .observeOn(Schedulers.trampoline())
            .subscribeOn(Schedulers.trampoline())
            .test()

        result.assertNoErrors()
        val places = result.values().first()
        assertNotNull(places)
        assert(places.bars.size == nearbySearchDTO.results?.size)
        assert(places.cafes.size == nearbySearchDTO.results?.size)
        assert(places.restaurants.size == nearbySearchDTO.results?.size)

    }

    private fun givenNearbySearchSuccessful() {
        whenever(googlePlacesRepository.nearbySearch(any(), any(), any(), any()))
            .thenReturn(Single.just(nearbySearchDTO))
    }
}
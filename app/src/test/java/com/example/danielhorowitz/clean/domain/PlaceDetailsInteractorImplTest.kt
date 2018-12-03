package com.example.danielhorowitz.clean.domain

import com.example.danielhorowitz.clean.data.model.PlaceDetailsDTO
import com.example.danielhorowitz.clean.data.repository.GooglePlacesRepository
import com.example.danielhorowitz.clean.domain.model.Place
import com.example.danielhorowitz.clean.fromJson
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class PlaceDetailsInteractorImplTest : BaseTest() {
    @Mock
    lateinit var googlePlacesRepository: GooglePlacesRepository

    val interactor by lazy { PlaceDetailsInteractorImpl(googlePlacesRepository) }

    private lateinit var placeDetailsDTO: PlaceDetailsDTO
    private val place = Place(id = "4f89212bf76dde31f092cfc14d7506555d85b5c7",name = "Google")

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        placeDetailsDTO = getResource("place_details.json").fromJson(PlaceDetailsDTO::class.java)
    }

    @Test
    fun `should return place details given fetch successful`() {
        givenPlaceDetailsSuccessful()

        val placeDetails = interactor.fetchPlaceDetails(place)
            .test()
            .values().first()

        Assert.assertEquals(placeDetails.place.name, placeDetailsDTO.result.name)
    }

    private fun givenPlaceDetailsSuccessful() {
        whenever(googlePlacesRepository.getPlacesDetails(any()))
            .thenReturn(Single.just(placeDetailsDTO))
    }
}
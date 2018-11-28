package com.example.danielhorowitz.clean.domain

import com.example.danielhorowitz.clean.data.model.NearbySearchDTO
import com.example.danielhorowitz.clean.data.model.PlaceDetailsDTO
import com.example.danielhorowitz.clean.data.repository.GooglePlacesRepository
import com.google.gson.Gson
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.io.File

class PlacesInteractorImplTest {
    @Mock
    lateinit var googlePlacesRepository: GooglePlacesRepository

    lateinit var nearbySearchDTO: NearbySearchDTO
    lateinit var placeDetailsDTO: PlaceDetailsDTO

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
        givenPlaceDetailsSuccessful()

        val result = interactor.fetchNearbyRestaurants(0.0, 0.0)
            .observeOn(Schedulers.trampoline())
            .subscribeOn(Schedulers.trampoline())
            .test()
        result.assertNoErrors()
        val places = result.values().first()

        assertNotNull(places)
//        assert(places.size == nearbySearchResults.size)
    }

    private fun givenNearbySearchSuccessful() {
        whenever(googlePlacesRepository.nearbySearch(any(), any(), any(), any()))
            .thenReturn(Single.just(nearbySearchDTO))
    }

    private fun givenPlaceDetailsSuccessful() {
        whenever(googlePlacesRepository.getPlacesDetails(any()))
            .thenReturn(Observable.just(placeDetailsDTO))
    }

    private fun getResource(fileName: String): File {
        val loader = ClassLoader.getSystemClassLoader()
        val resource = loader.getResource(fileName)
        return File(resource.path)
    }

}

fun <T> File.fromJson(type: Class<T>): T = Gson().fromJson(this.readText(), type)
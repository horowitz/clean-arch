package com.example.danielhorowitz.clean.domain

import com.example.danielhorowitz.clean.data.model.PlaceDetailsDTO
import com.example.danielhorowitz.clean.data.repository.GooglePlacesRepository
import com.example.danielhorowitz.clean.domain.mapper.GooglePlacesMapper
import com.example.danielhorowitz.clean.domain.model.Place
import io.reactivex.Single
import org.mapstruct.factory.Mappers

/**
 * Created by danielhorowitz on 16/03/2018.
 */
class PlacesInteractorImpl(private val googlePlacesRepository: GooglePlacesRepository) : PlacesInteractor {

    companion object {
        private const val RADIUS = 500.0
        private const val RESTAURANT_TYPE = "restaurant"
        private const val RANK_BY_DISTANCE = "distance"
    }


    override fun fetchNearbyRestaurants(latitude: Double, longitude: Double): Single<List<Place>> {
        val latLng = "$latitude, $longitude"

        return googlePlacesRepository.nearbySearch(latLng, RADIUS, RESTAURANT_TYPE, RANK_BY_DISTANCE)
            .map { it.results ?: listOf() }
            .flattenAsObservable { it }
            .flatMap { googlePlacesRepository.getPlacesDetails(it.placeId) }
            .map { convertToPresentation(it) }
            .toList()
    }

    private fun convertToPresentation(placeDetailsDTO: PlaceDetailsDTO): Place =
        Mappers.getMapper(GooglePlacesMapper::class.java).convertPlaceDetails(placeDetailsDTO.result)

}

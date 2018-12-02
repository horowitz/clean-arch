package com.example.danielhorowitz.clean.domain

import com.example.danielhorowitz.clean.data.model.NearbyPlaceResultDTO
import com.example.danielhorowitz.clean.data.model.PlaceDetailsDTO
import com.example.danielhorowitz.clean.data.repository.GooglePlacesRepository
import com.example.danielhorowitz.clean.domain.mapper.GooglePlacesMapper
import com.example.danielhorowitz.clean.domain.model.NearbyPlaces
import com.example.danielhorowitz.clean.domain.model.Place
import io.reactivex.Single
import io.reactivex.functions.Function3
import org.mapstruct.factory.Mappers

/**
 * Created by danielhorowitz on 16/03/2018.
 */
class PlacesInteractorImpl(private val googlePlacesRepository: GooglePlacesRepository) : PlacesInteractor {

    companion object {
        private const val RADIUS = 500.0
        private const val RESTAURANT_TYPE = "restaurant"
        private const val BAR_TYPE = "bar"
        private const val CAFE_TYPE = "cafe"
        private const val RANK_BY_DISTANCE = "distance"
    }

    override fun fetchNearbyPlaces(latitude: Double, longitude: Double): Single<NearbyPlaces> {
        val latLng = "$latitude, $longitude"

        val restaurantsObservable = createPlacesObservable(latLng, RESTAURANT_TYPE)
        val barsObservable = createPlacesObservable(latLng, BAR_TYPE)
        val cafesObservable = createPlacesObservable(latLng, CAFE_TYPE)

        return Single.zip(restaurantsObservable,
            barsObservable,
            cafesObservable,
            Function3<List<Place>, List<Place>, List<Place>, NearbyPlaces> { restaurants, bars, cafes ->
                NearbyPlaces(restaurants, bars, cafes)
            })
    }

    private fun createPlacesObservable(
        latLng: String,
        type: String
    ): Single<List<Place>>? {
        return googlePlacesRepository.nearbySearch(latLng, RADIUS, type, RANK_BY_DISTANCE)
            .map { requireNotNull(it.results) }
            .map { convertNearbyPlaceResultToPresentation(it) }
    }

    private fun convertNearbyPlaceResultToPresentation(nearbyPlaceResultDTOs: List<NearbyPlaceResultDTO>): List<Place> =
        nearbyPlaceResultDTOs.map { dto ->
            val place = Mappers.getMapper(GooglePlacesMapper::class.java).convertNearbySearch(dto)
            dto.photos?.first()?.let { place.addPhotoFromGooglePlaces(it.photoReference) }
            place
        }


    private fun convertToPresentation(placeDetailsDTO: PlaceDetailsDTO): Place {
        val place = Mappers.getMapper(GooglePlacesMapper::class.java).convertPlaceDetails(placeDetailsDTO.result)
        placeDetailsDTO.result.photos?.forEach { place.addPhotoFromGooglePlaces(it.photoReference) }
        return place
    }
}

package com.example.danielhorowitz.clean.domain

import android.location.Location
import com.example.danielhorowitz.clean.data.model.NearbyPlaceResultDTO
import com.example.danielhorowitz.clean.data.repository.GooglePlacesRepository
import com.example.danielhorowitz.clean.domain.mapper.GooglePlacesMapper
import com.example.danielhorowitz.clean.domain.model.NearbyPlaces
import com.example.danielhorowitz.clean.domain.model.Place
import io.reactivex.Single
import io.reactivex.functions.Function3
import io.reactivex.schedulers.Schedulers

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
        val restaurantsObservable = createPlacesObservable(latitude, longitude, RESTAURANT_TYPE)
        val barsObservable = createPlacesObservable(latitude, longitude, BAR_TYPE)
        val cafesObservable = createPlacesObservable(latitude, longitude, CAFE_TYPE)

        return Single.zip(restaurantsObservable,
            barsObservable,
            cafesObservable,
            Function3<List<Place>, List<Place>, List<Place>, NearbyPlaces> { restaurants, bars, cafes ->
                NearbyPlaces(restaurants, bars, cafes)
            })
    }

    private fun createPlacesObservable(
        latitude: Double,
        longitude: Double,
        type: String
    ): Single<List<Place>> {
        val latLng = "$latitude, $longitude"
        return googlePlacesRepository.nearbySearch(latLng, RADIUS, type, RANK_BY_DISTANCE)
            .map { requireNotNull(it.results) }
            .map { convertNearbyPlaceResultToPresentation(it,latitude, longitude) }
            .subscribeOn(Schedulers.io())
    }

    private fun convertNearbyPlaceResultToPresentation(
        nearbyPlaceResultDTOs: List<NearbyPlaceResultDTO>,
        latitude: Double,
        longitude: Double
    ): List<Place> =
        nearbyPlaceResultDTOs.map { dto ->
            val place = GooglePlacesMapper.convertNearbySearchResult(dto)
            dto.photos?.first()?.let { place.addPhotoFromGooglePlaces(it.photoReference) }
            val location = requireNotNull(dto.geometry?.location)
            place.distance = calculateDistance(latitude, longitude, location).toDouble()
            place
        }

    private fun calculateDistance(
        latitude: Double,
        longitude: Double,
        location: com.example.danielhorowitz.clean.data.model.Location
    ): Float {
        val currentLocation = Location("")
        currentLocation.longitude = longitude
        currentLocation.latitude = latitude
        val placeLocation = Location("")
        placeLocation.latitude = location.lat
        placeLocation.longitude = location.lng
        return currentLocation.distanceTo(placeLocation) / 1000
    }
}

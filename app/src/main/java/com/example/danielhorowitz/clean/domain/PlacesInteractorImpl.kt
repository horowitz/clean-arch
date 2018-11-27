package com.example.danielhorowitz.clean.domain

import com.example.danielhorowitz.clean.data.model.Location
import com.example.danielhorowitz.clean.data.model.PlaceDetailsDTO
import com.example.danielhorowitz.clean.data.repository.GooglePlacesRepository
import com.example.danielhorowitz.clean.domain.mapper.GooglePlacesMapper
import com.example.danielhorowitz.clean.domain.model.Place
import io.reactivex.Observable
import org.mapstruct.factory.Mappers

/**
 * Created by danielhorowitz on 16/03/2018.
 */
class PlacesInteractorImpl(private val googlePlacesRepository: GooglePlacesRepository) : PlacesInteractor {


    override fun fetchNearbyRestaurants(latitude: Double, longitude: Double): Observable<Place> {
        val latLng = "$latitude, $longitude"
        return googlePlacesRepository.nearbySearch(latLng, 500.0, "restaurant", "distance")
            .map { it.results ?: listOf() }
            .flattenAsObservable { it }
            .flatMap { googlePlacesRepository.getPlacesDetails(it.placeId) }
            .map { convertToPresentation(it, latitude, longitude) }
    }

    private fun convertToPresentation(placeDetailsDTO: PlaceDetailsDTO, latitude: Double, longitude: Double): Place {
        val place = Mappers.getMapper(GooglePlacesMapper::class.java).convertPlaceDetails(placeDetailsDTO.result)
        val distance = calculateDistance(longitude, latitude, placeDetailsDTO.result.geometry.location)
        place.distance = distance.toDouble()
        placeDetailsDTO.result.photos?.forEach {
            place.addPhotoFromGooglePlaces(it.photoReference)
        }
        return place
    }

    private fun calculateDistance(longitude: Double, latitude: Double, location: Location): Float {
        val currentLocation = android.location.Location("")
        currentLocation.longitude = longitude
        currentLocation.latitude = latitude
        val placeLocation = android.location.Location("")
        placeLocation.latitude = location.lat
        placeLocation.longitude = location.lng
        return currentLocation.distanceTo(placeLocation) / 1000
    }

}

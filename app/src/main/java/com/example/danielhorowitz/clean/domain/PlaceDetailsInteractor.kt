package com.example.danielhorowitz.clean.domain

import com.example.danielhorowitz.clean.data.model.PlaceDetailsDTO
import com.example.danielhorowitz.clean.data.repository.GooglePlacesRepository
import com.example.danielhorowitz.clean.domain.mapper.GooglePlacesMapper
import com.example.danielhorowitz.clean.domain.model.Place
import com.example.danielhorowitz.clean.domain.model.PlaceDetails
import io.reactivex.Single
import org.mapstruct.factory.Mappers

interface PlaceDetailsInteractor {
    fun fetchPlaceDetails(place: Place): Single<PlaceDetails>
}

class PlaceDetailsInteractorImpl(private val googlePlacesRepository: GooglePlacesRepository) : PlaceDetailsInteractor {

    override fun fetchPlaceDetails(place: Place): Single<PlaceDetails> {
        return googlePlacesRepository.getPlacesDetails(place.id)
            .map { convertToPresentation(it,place) }
    }

    private fun convertToPresentation(
        placeDetailsDTO: PlaceDetailsDTO,
        place: Place
    ): PlaceDetails {
        val details = Mappers.getMapper(GooglePlacesMapper::class.java).convertPlaceDetails(placeDetailsDTO.result)
        placeDetailsDTO.result.photos?.forEach { details.place.addPhotoFromGooglePlaces(it.photoReference) }
        details.place.distance = place.distance
        return details
    }

}
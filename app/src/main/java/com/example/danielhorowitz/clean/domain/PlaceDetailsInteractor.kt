package com.example.danielhorowitz.clean.domain

import com.example.danielhorowitz.clean.data.model.PlaceDetailsDTO
import com.example.danielhorowitz.clean.data.repository.GooglePlacesRepository
import com.example.danielhorowitz.clean.domain.mapper.GooglePlacesMapper
import com.example.danielhorowitz.clean.domain.model.PlaceDetails
import io.reactivex.Single
import org.mapstruct.factory.Mappers

interface PlaceDetailsInteractor {
    fun fetchPlaceDetails(placeId: String): Single<PlaceDetails>
}

class PlaceDetailsInteractorImpl(private val googlePlacesRepository: GooglePlacesRepository) : PlaceDetailsInteractor {

    override fun fetchPlaceDetails(placeId: String): Single<PlaceDetails> {
        return googlePlacesRepository.getPlacesDetails(placeId)
            .map { convertToPresentation(it) }
    }

    private fun convertToPresentation(placeDetailsDTO: PlaceDetailsDTO): PlaceDetails {
        val details = Mappers.getMapper(GooglePlacesMapper::class.java).convertPlaceDetails(placeDetailsDTO.result)
        placeDetailsDTO.result.photos?.forEach { details.place.addPhotoFromGooglePlaces(it.photoReference) }
        return details
    }

}
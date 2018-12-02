package com.example.danielhorowitz.clean.domain.mapper

import com.example.danielhorowitz.clean.data.model.NearbyPlaceResultDTO
import com.example.danielhorowitz.clean.data.model.PlaceDetailsResultDTO
import com.example.danielhorowitz.clean.data.model.ReviewsItem
import com.example.danielhorowitz.clean.domain.model.Place
import com.example.danielhorowitz.clean.domain.model.PlaceDetails
import com.example.danielhorowitz.clean.domain.model.Reviews
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings

/**
 * Created by danielhorowitz on 17/03/18.
 */
@Mapper
interface GooglePlacesMapper {

    @Mappings(
            Mapping(source = "placeId", target = "id")
    )
    fun convertNearbySearch(nearbySearchDTO: NearbyPlaceResultDTO): Place

    fun convertNearbySearch(nearbySearchDTOs: List<NearbyPlaceResultDTO>): List<Place>

    @Mappings(
        Mapping(source = "openingHours.openNow", target = "place.openNow"),
        Mapping(source = "id", target = "place.id"),
        Mapping(source = "name", target = "place.name"),
        Mapping(source = "rating", target = "place.rating"),
        Mapping(source = "vicinity", target = "place.vicinity")
    )
    fun convertPlaceDetails(placeDetailsResultDTO: PlaceDetailsResultDTO): PlaceDetails

    fun convertReviews(reviewsItem: ReviewsItem): Reviews
}
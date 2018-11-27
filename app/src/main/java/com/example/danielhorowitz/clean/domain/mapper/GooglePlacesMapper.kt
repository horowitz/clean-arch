package com.example.danielhorowitz.clean.domain.mapper

import com.example.danielhorowitz.clean.data.model.NearbyPlaceResultDTO
import com.example.danielhorowitz.clean.data.model.PlaceDetailsResultDTO
import com.example.danielhorowitz.clean.domain.model.Place
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

    @Mapping(source = "openingHours.openNow", target = "openNow")
    fun convertPlaceDetails(placeDetailsResultDTO: PlaceDetailsResultDTO): Place
}
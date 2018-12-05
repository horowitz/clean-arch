package com.example.danielhorowitz.clean.domain.mapper

import com.example.danielhorowitz.clean.data.model.NearbyPlaceResultDTO
import com.example.danielhorowitz.clean.data.model.PlaceDetailsResultDTO
import com.example.danielhorowitz.clean.data.model.ReviewsItem
import com.example.danielhorowitz.clean.domain.model.Place
import com.example.danielhorowitz.clean.domain.model.PlaceDetails
import com.example.danielhorowitz.clean.domain.model.Reviews

/**
 * Created by danielhorowitz on 17/03/18.
 */
class GooglePlacesMapper {

    companion object {
        fun convertNearbySearchResult(nearbySearchResultDTO: NearbyPlaceResultDTO): Place {
            val name = nearbySearchResultDTO.name
            val id = nearbySearchResultDTO.placeId
            val images = mutableListOf<String>()
            val rating = nearbySearchResultDTO.rating
            val distance = 0.0
            val vicinity = nearbySearchResultDTO.vicinity
            val openNow = nearbySearchResultDTO.openingHours?.openNow ?: false
            return Place(name, images, rating, distance, vicinity, id, openNow)
        }
        
        fun convertPlaceDetails(placeDetailsResultDTO: PlaceDetailsResultDTO): PlaceDetails{
            val name = placeDetailsResultDTO.name
            val id = placeDetailsResultDTO.placeId
            val images = mutableListOf<String>()
            val rating = placeDetailsResultDTO.rating
            val distance = 0.0
            val vicinity = placeDetailsResultDTO.vicinity
            val openNow = placeDetailsResultDTO.openingHours?.openNow ?: false
            val place = Place(name, images, rating, distance, vicinity, id, openNow)
            val reviews = convertReviews(placeDetailsResultDTO.reviews)

            return PlaceDetails(place,reviews)
        }

        private fun convertReviews(reviewDTOs: List<ReviewsItem>?): List<Reviews> {
            val reviews = mutableListOf<Reviews>()
            reviewDTOs?.forEach {
                val authorName = it.authorName
                val profilePhotoUrl = it.profilePhotoUrl
                val rating = it.rating
                val text = it.text
                val relativeTimeDescription = it.relativeTimeDescription
                reviews.add(Reviews(authorName,profilePhotoUrl,rating,text,relativeTimeDescription))
            }
            return reviews
        }
    }
}
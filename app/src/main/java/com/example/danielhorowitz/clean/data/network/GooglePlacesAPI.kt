package com.example.danielhorowitz.clean.data.network

import com.example.danielhorowitz.clean.data.model.NearbySearchDTO
import com.example.danielhorowitz.clean.data.model.PlaceDetailsDTO
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by danielhorowitz on 14/03/18.
 */
interface GooglePlacesAPI {
    @GET(NetworkConfig.GOOGLE_PLACES_API_PATH)
    fun getPlaceDetails(@Query(NetworkConfig.Places.KEY_PARAM) key: String,
                        @Query(NetworkConfig.Places.PLACE_ID_PARAM) placeId: String): Observable<PlaceDetailsDTO>

    @GET(NetworkConfig.GOOGLE_NEARBY_SEARCH_API_PATH)
    fun nearbySearch(@Query (NetworkConfig.Places.LOCATION_PARAM) location: String,
                     @Query (NetworkConfig.Places.RADIUS_PARAM) radius: Double,
                     @Query (NetworkConfig.Places.TYPE_PARAM) type: String,
                     @Query (NetworkConfig.Places.RANK_BY_PARAM) rankBy: String,
                     @Query(NetworkConfig.Places.KEY_PARAM) key: String): Single<NearbySearchDTO>
}
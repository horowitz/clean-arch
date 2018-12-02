package com.example.danielhorowitz.clean.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by danielhorowitz on 17/03/18.
 */


@Parcelize
data class PlaceDetails(
    var place: Place,
    var reviews: List<Reviews>
) : Parcelable {
    constructor() : this(Place(), emptyList())
}

@Parcelize
data class Reviews(
    var authorName: String,
    var profilePhotoUrl: String,
    var rating: Int,
    var text: String,
    var relativeTimeDescription: String
) : Parcelable{
    constructor(): this("","",0,"","")
}



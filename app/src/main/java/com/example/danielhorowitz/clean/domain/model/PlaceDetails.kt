package com.example.danielhorowitz.clean.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PlaceDetails(
    val place: Place,
    val reviews: List<Reviews>
) : Parcelable

@Parcelize
data class Reviews(
    val authorName: String,
    val profilePhotoUrl: String,
    val rating: Int,
    val text: String,
    val relativeTimeDescription: String
) : Parcelable



package com.example.danielhorowitz.clean

import android.app.Activity
import android.content.Intent
import com.example.danielhorowitz.clean.domain.model.Place
import com.example.danielhorowitz.clean.presentation.details.PlaceDetailsActivity

/**
 * Created by danielhorowitz on 8/10/17.
 */
class NavigatorImpl(
    private val activity: Activity
) : Navigator {

    override fun navigateToPlaceDetails(place: Place) {
        val intent = Intent(activity, PlaceDetailsActivity::class.java)
        intent.putExtra(PlaceDetailsActivity.PLACE_EXTRA, place)
        activity.startActivity(intent)
    }
}
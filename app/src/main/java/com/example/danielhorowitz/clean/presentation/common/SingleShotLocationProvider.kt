package com.example.danielhorowitz.clean.presentation.common

import android.annotation.SuppressLint
import android.content.Context
import android.location.Criteria
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper

class SingleShotLocationProvider(private val looper: Looper, val onLocationReceived: (Location) -> Unit) {
    lateinit var locationManager: LocationManager
    private var locationListener: LocationListener? = null

    @SuppressLint("MissingPermission")
    fun requestSingleUpdate(context: Context) {
        locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        locationListener = object : LocationListener {
            override fun onLocationChanged(location: Location) {
                onLocationReceived(location)
            }

            override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {
                print("foo")
            }

            override fun onProviderEnabled(provider: String) {
                print("foo")
            }

            override fun onProviderDisabled(provider: String) {
                print("foo")
            }
        }
        if (isNetworkEnabled) {
            val criteria = Criteria()
            criteria.accuracy = Criteria.ACCURACY_FINE
            locationManager.requestSingleUpdate(criteria, locationListener, looper)
        } else {
            val isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
            if (isGPSEnabled) {
                val criteria = Criteria()
                criteria.accuracy = Criteria.ACCURACY_FINE
                locationManager.requestSingleUpdate(criteria, locationListener, looper)
            }
        }
    }

    fun removeUpdates(){
        locationManager.removeUpdates(locationListener)
    }
}
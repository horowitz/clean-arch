package com.example.danielhorowitz.clean.presentation.common

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.os.Handler
import android.os.Looper
import com.example.danielhorowitz.clean.R
import com.example.danielhorowitz.clean.domain.model.Location
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import io.reactivex.Single
import io.reactivex.SingleEmitter
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.yesButton
import java.net.SocketTimeoutException

/**
 * Created by danielhorowitz on 24/03/18.
 */

interface LocationHandler {
    fun getCurrentLocation(): Single<Location>
}

@SuppressLint("MissingPermission")
class LocationHandlerImpl(val activity: Activity) : LocationHandler {
    val looper = Looper.myLooper()
    val handler = Handler(looper)

    override fun getCurrentLocation(): Single<Location> {
        return Single.create {
            Dexter.withActivity(activity)
                .withPermissions(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(object : MultiplePermissionsListener {
                    override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                        if (report?.areAllPermissionsGranted() == true) {
                            createSingleLocationRequest(it)
                        }
                    }

                    override fun onPermissionRationaleShouldBeShown(
                        permissions: MutableList<PermissionRequest>?,
                        token: PermissionToken?
                    ) {
                        it.onError(LocationException())
                        activity.alert(R.string.location_permission_denied, R.string.location_permission_title) {
                            yesButton { dialog ->
                                dialog.dismiss()
                                token?.continuePermissionRequest()
                            }
                            noButton { dialog ->
                                dialog.dismiss()
                                token?.cancelPermissionRequest()
                            }
                            onCancelled {
                                token?.cancelPermissionRequest()
                            }
                        }.show()
                    }

                }).check()
        }
    }

    private fun createSingleLocationRequest(emitter: SingleEmitter<Location>) {
        val provider = SingleShotLocationProvider(looper) {
            emitter.onSuccess(Location(it.latitude, it.longitude))
            handler.removeCallbacksAndMessages(null)
        }

        handler.postDelayed({
            provider.removeUpdates()
            emitter.onError(SocketTimeoutException())
        }, 15000)

        provider.requestSingleUpdate(activity)
    }
}

class LocationException : Exception()

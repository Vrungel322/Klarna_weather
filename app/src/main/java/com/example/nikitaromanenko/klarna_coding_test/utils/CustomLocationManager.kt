package com.example.nikitaromanenko.klarna_coding_test.utils

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import com.example.nikitaromanenko.klarna_coding_test.App
import com.example.nikitaromanenko.klarna_coding_test.locationPermission

private const val REQUEST_LOCATION_PERIOD = 2 * 60 * 100L // 2 min
private const val REQUEST_LOCATION_INTERVAL = 5F // 5m

/**
 * Class used for defining (lat,long)
 */
class CustomLocationManager(private val context: Context) {

    private val locationManager: LocationManager? by lazy { context.getSystemService(Context.LOCATION_SERVICE) as? LocationManager }
    private val permissionHandler: PermissionHandler by lazy { App.permissionHandler }

    /**
     * Get last known location and invoke  @param locationUpdateCallback,
     * after start requestLocationUpdates and each location changed also invoke @param locationUpdateCallback
     */
    @SuppressLint("MissingPermission")
    fun getCurrentLocation(locationUpdateCallback: (Pair<Double?, Double?>) -> Unit, failCallback: () -> Unit) {
        val locationListener = object : LocationListener {
            override fun onLocationChanged(location: Location?) {
                locationUpdateCallback.invoke(Pair(location?.latitude, location?.longitude))
            }

            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
            }

            override fun onProviderEnabled(provider: String?) {
            }

            override fun onProviderDisabled(provider: String?) {
                failCallback.invoke()
            }
        }
        if (permissionHandler.isPermissionGranted(locationPermission)) {
            val lastKnownLocation = locationManager?.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            locationUpdateCallback.invoke(Pair(lastKnownLocation?.latitude, lastKnownLocation?.longitude))
            locationManager?.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                REQUEST_LOCATION_PERIOD,
                REQUEST_LOCATION_INTERVAL,
                locationListener
            )
        }
    }
}
package com.example.nikitaromanenko.klarna_coding_test.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.support.v4.content.ContextCompat

private const val REQUEST_LOCATION_PERIOD = 2 * 60 * 100L // 2 min
private const val REQUEST_LOCATION_INTERVAL = 5F // 5m

/**
 * Class used for defining (lat,long)
 */
class CustomLocationManager(private val context: Context) {

    private val locationManager: LocationManager? by lazy { context.getSystemService(Context.LOCATION_SERVICE) as? LocationManager }

    /**
     * Get last known location and invoke  @param locationUpdateCallback,
     * after start requestLocationUpdates and each location changed also invoke @param locationUpdateCallback
     */
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
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
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
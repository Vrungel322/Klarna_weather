package com.example.nikitaromanenko.klarna_coding_test.utils

import android.content.Context
import android.location.Geocoder

class CustomGeocoder(val context: Context) {

    /**
     * Find location name by [latitude] and [longitude]
     * @return location name
     */
    fun fetchLocationName(latitude: Double?, longitude: Double?): String {
        return if (latitude != null && longitude != null) {
            val geocoder = Geocoder(context)
            val addresses = geocoder.getFromLocation(latitude, longitude, 1)
            val cityName = addresses.get(0).getAddressLine(0)
            cityName
        } else "Not defined Location"
    }
}
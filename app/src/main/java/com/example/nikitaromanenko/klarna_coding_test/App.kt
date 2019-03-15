package com.example.nikitaromanenko.klarna_coding_test

import android.Manifest
import android.app.Application
import android.content.Context
import com.example.nikitaromanenko.klarna_coding_test.network.ApiHelper
import com.example.nikitaromanenko.klarna_coding_test.utils.CustomGeocoder
import com.example.nikitaromanenko.klarna_coding_test.utils.CustomLocationManager
import com.example.nikitaromanenko.klarna_coding_test.utils.PermissionHandler
import com.google.gson.Gson

const val locationPermission = Manifest.permission.ACCESS_FINE_LOCATION

class App : Application() {
    companion object {
        lateinit var apiHelper: ApiHelper
        lateinit var gson: Gson
        lateinit var geocoder: CustomGeocoder
        lateinit var customLocationManager: CustomLocationManager
        lateinit var permissionHandler: PermissionHandler
        lateinit var appContext: Context
    }


    override fun onCreate() {
        super.onCreate()

        appContext = this
        gson = Gson()
        apiHelper = ApiHelper(gson)
        geocoder = CustomGeocoder(this)
        customLocationManager = CustomLocationManager(this)
        permissionHandler = PermissionHandler()
    }

}
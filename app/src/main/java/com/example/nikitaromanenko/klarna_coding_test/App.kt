package com.example.nikitaromanenko.klarna_coding_test

import android.app.Application
import com.example.nikitaromanenko.klarna_coding_test.network.ApiHelper
import com.example.nikitaromanenko.klarna_coding_test.utils.CustomGeocoder
import com.example.nikitaromanenko.klarna_coding_test.utils.CustomLocationManager
import com.example.nikitaromanenko.klarna_coding_test.utils.PermissionHandler
import com.google.gson.Gson

class App : Application() {
    companion object {
        lateinit var apiHelper: ApiHelper
        lateinit var gson: Gson
        lateinit var geocoder: CustomGeocoder
        lateinit var customLocationManager: CustomLocationManager
        lateinit var permissionHandler: PermissionHandler
    }


    override fun onCreate() {
        super.onCreate()

        gson = Gson()
        apiHelper = ApiHelper(gson)
        geocoder = CustomGeocoder(this)
        customLocationManager = CustomLocationManager(this)
        permissionHandler = PermissionHandler()
    }

}
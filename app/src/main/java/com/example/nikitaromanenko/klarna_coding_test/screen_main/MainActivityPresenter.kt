package com.example.nikitaromanenko.klarna_coding_test.screen_main

import com.example.nikitaromanenko.klarna_coding_test.base.BasePresenter
import com.example.nikitaromanenko.klarna_coding_test.network.ApiHelper
import com.example.nikitaromanenko.klarna_coding_test.utils.CustomGeocoder
import com.example.nikitaromanenko.klarna_coding_test.utils.CustomLocationManager

class MainActivityPresenter(
    private val apiHelper: ApiHelper,
    private val geocoder: CustomGeocoder,
    private val customLocationManager: CustomLocationManager
) : BasePresenter<IMainActivityView>() {

    private val defaultFailCallback: () -> Unit = { view?.dismissLoading() }

    /**
     * Define current location in (lat,long) format, and load weather data from server
     */
    fun loadWeather() {
        customLocationManager.getCurrentLocation({
            view?.showLoading()
            apiHelper.loadWeather(it.first, it.second, { weatherModel ->
                val locationName = geocoder.fetchLocationName(it.first, it.second)
                view?.apply {
                    showWeather(weatherModel, locationName)
                    dismissLoading()
                }
            }, {
                processError(it)
                defaultFailCallback.invoke()
            })
        }, defaultFailCallback)

    }
}
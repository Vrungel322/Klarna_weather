package com.example.nikitaromanenko.klarna_coding_test.screen_main

import com.example.nikitaromanenko.klarna_coding_test.utils.CustomGeocoder
import com.example.nikitaromanenko.klarna_coding_test.utils.CustomLocationManager
import com.example.nikitaromanenko.klarna_coding_test.base.BasePresenter
import com.example.nikitaromanenko.klarna_coding_test.network.ApiHelper

class MainActivityPresenter(
        private val apiHelper: ApiHelper,
        private val geocoder: CustomGeocoder,
        private val customLocationManager: CustomLocationManager
) : BasePresenter<IMainActivityView>() {

    private val defaultFailCallback: () -> Unit = { view?.dismissLoading() }

    fun loadWeather() {
        customLocationManager.getCurrentLocation({
            view?.showLoading()
            apiHelper.loadWeather(it.first, it.second, { weatherModel ->
                view?.apply {
                    val locationName = geocoder.fetchLocationName(it.first, it.second)
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
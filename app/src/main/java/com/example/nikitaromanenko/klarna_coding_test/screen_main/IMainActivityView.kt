package com.example.nikitaromanenko.klarna_coding_test.screen_main

import com.example.nikitaromanenko.klarna_coding_test.base.BaseViewWithLoading
import com.example.nikitaromanenko.klarna_coding_test.model.WeatherModel

interface IMainActivityView : BaseViewWithLoading {
    fun showWeather(
        weatherModel: WeatherModel,
        locationName: String
    )
}
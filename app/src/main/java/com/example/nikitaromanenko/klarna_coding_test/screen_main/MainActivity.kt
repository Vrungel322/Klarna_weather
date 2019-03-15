package com.example.nikitaromanenko.klarna_coding_test.screen_main

import android.Manifest
import android.os.Bundle
import com.example.nikitaromanenko.klarna_coding_test.App
import com.example.nikitaromanenko.klarna_coding_test.R
import com.example.nikitaromanenko.klarna_coding_test.base.BaseActivityWithLoading
import com.example.nikitaromanenko.klarna_coding_test.model.WeatherModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivityWithLoading(), IMainActivityView {
    private val permissionHandler = App.permissionHandler
    private val presenter: MainActivityPresenter by lazy {
        MainActivityPresenter(App.apiHelper, App.geocoder, App.customLocationManager)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.attachView(this)
    }

    override fun onStart() {
        super.onStart()
        permissionHandler.checkLocationPermission(this, {
            ifInternetAvailableDo {
                presenter.loadWeather()
            }
        }, {
            showMessage(R.string.allow_location)
        }, Manifest.permission.ACCESS_FINE_LOCATION)
    }

    override fun showWeather(
            weatherModel: WeatherModel,
            locationName: String
    ) {
        weatherModel.apply {
            tv_temperature.text = getString(R.string.temperature, currently?.temperature.toString())
            tv_summary.text = getString(R.string.summary, currently?.summary)
            tv_location.text = locationName
            tv_data.text = this.toString()
        }
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        permissionHandler.handlePermission(requestCode, permissions, grantResults, this)
    }


}

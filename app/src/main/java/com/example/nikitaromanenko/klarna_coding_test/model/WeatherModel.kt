package com.example.nikitaromanenko.klarna_coding_test.model

data class WeatherModel(
    val alerts: List<Alert?>?,
    val currently: Currently?,
    val daily: Daily?,
    val hourly: Hourly?,
    val latitude: Double?,
    val longitude: Double?,
    val offset :Float?,
    val timezone: String?
)

data class Currently(
    val apparentTemperature: Double?,
    val cloudCover: Double?,
    val dewPoint: Double?,
    val humidity: Double?,
    val icon: String?,
    val ozone: Double?,
    val precipIntensity: Double?,
    val precipProbability: Double?,
    val precipType: String?,
    val pressure: Double?,
    val summary: String?,
    val temperature: Double?,
    val time :Float?,
    val uvIndex :Float?,
    val visibility: Double?,
    val windBearing :Float?,
    val windGust: Double?,
    val windSpeed: Double?
)

data class Daily(
    val data: List<DataWeather?>?,
    val icon: String?,
    val summary: String?
)

data class DataWeather(
    val apparentTemperatureHigh: Double?,
    val apparentTemperatureHighTime :Float?,
    val apparentTemperatureLow: Double?,
    val apparentTemperatureLowTime :Float?,
    val apparentTemperatureMax: Double?,
    val apparentTemperatureMaxTime :Float?,
    val apparentTemperatureMin: Double?,
    val apparentTemperatureMinTime :Float?,
    val cloudCover: Double?,
    val dewPoint: Double?,
    val humidity: Double?,
    val icon: String?,
    val moonPhase: Double?,
    val ozone: Double?,
    val precipAccumulation: Double?,
    val precipIntensity: Double?,
    val precipIntensityMax: Double?,
    val precipIntensityMaxTime :Float?,
    val precipProbability: Double?,
    val precipType: String?,
    val pressure: Double?,
    val summary: String?,
    val sunriseTime :Float?,
    val sunsetTime :Float?,
    val temperatureHigh: Double?,
    val temperatureHighTime :Float?,
    val temperatureLow: Double?,
    val temperatureLowTime :Float?,
    val temperatureMax: Double?,
    val temperatureMaxTime :Float?,
    val temperatureMin: Double?,
    val temperatureMinTime :Float?,
    val time :Float?,
    val uvIndex :Float?,
    val uvIndexTime :Float?,
    val visibility: Double?,
    val windBearing :Float?,
    val windGust: Double?,
    val windGustTime :Float?,
    val windSpeed: Double?
)

data class Hourly(
    val `data`: List<Data?>?,
    val icon: String?,
    val summary: String?
)

data class Data(
    val apparentTemperature: Double?,
    val cloudCover: Double?,
    val dewPoint: Double?,
    val humidity: Double?,
    val icon: String?,
    val ozone: Double?,
    val precipAccumulation: Double?,
    val precipIntensity :Float?,
    val precipProbability :Float?,
    val precipType: String?,
    val pressure: Double?,
    val summary: String?,
    val temperature: Double?,
    val time :Float?,
    val uvIndex :Float?,
    val visibility :Float?,
    val windBearing :Float?,
    val windGust: Double?,
    val windSpeed: Double?
)

data class Alert(
    val description: String?,
    val expires :Float?,
    val regions: List<String?>?,
    val severity: String?,
    val time :Float?,
    val title: String?,
    val uri: String?
)

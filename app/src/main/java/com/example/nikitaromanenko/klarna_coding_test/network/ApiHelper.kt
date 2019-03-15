package com.example.nikitaromanenko.klarna_coding_test.network

import com.example.nikitaromanenko.klarna_coding_test.model.WeatherModel
import com.google.gson.Gson
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class ApiHelper(private val gson: Gson) {

    private val client = OkHttpClient()

    companion object {
        private val BASE_URL = "https://api.darksky.net"
    }

    fun loadWeather(
        latitude: Double?, longitude: Double?,
        successCallback: ((WeatherModel) -> Unit)? = null, failCallback: ((Exception) -> Unit)? = null
    ) {
        val link = if (latitude == null || longitude == null) {
            "$BASE_URL/forecast/2bb07c3bece89caf533ac9a5d23d8417/59.337239,18.062381"
        } else {
            "$BASE_URL/forecast/2bb07c3bece89caf533ac9a5d23d8417/${latitude.toString()},${longitude.toString()}"
        }
        val request = createRequest(link)

        client.newCall(request).enqueue(object : UIMainCallback() {
            override fun failure(e: IOException) {
                failCallback?.invoke(e)
            }

            override fun response(response: Response) {
                if (response.isSuccessful) {
                    response.body()?.string()?.let {
                        val weather = gson.fromJson(it, WeatherModel::class.java)
                        successCallback?.invoke(weather)
                    }
                } else {
                    failCallback?.invoke(IOException("message:${response.message()} code:${response.code()}"))
                }
            }
        })
    }


    private fun createRequest(link: String): Request {
        val urlBuilder = HttpUrl.parse(link)?.newBuilder()
        val url = urlBuilder?.build().toString()
        return Request.Builder().url(url).build()
    }
}
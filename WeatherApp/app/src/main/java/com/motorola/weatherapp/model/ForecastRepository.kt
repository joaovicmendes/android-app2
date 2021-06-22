package com.motorola.weatherapp.model

import com.motorola.weatherapp.fetch.Config
import com.motorola.weatherapp.fetch.OneCallApiService
import com.motorola.weatherapp.fetch.RetrofitClient

class ForecastRepository() {
    private val retrofit = RetrofitClient.createService(OneCallApiService::class.java)

    suspend fun fetchWeather(lat: Double, lon: Double): Forecast {
        return retrofit.fetch(lat, lon, Config.apiKey)
    }
}

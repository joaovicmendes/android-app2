package com.motorola.weatherapp.model

import com.google.gson.annotations.SerializedName

class Weather(
    val dt: Long = 0,
    val temp: Double = 0.0
)

// Gambiarra porque a API manda a temperatura de uma forma diferente
data class DailyWeather(
    val dt: Long,
    val temp: Temperature,
)

data class Temperature(
    val day: Double
)

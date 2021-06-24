package com.motorola.weatherapp.model

data class Forecast(
    val current: Weather,
    val hourly: List<Weather>,
    val daily: List<Weather>
)

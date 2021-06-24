package com.motorola.weatherapp.model

import com.google.gson.annotations.SerializedName

data class Weather(
    val dt: Long = 0,
    @SerializedName("sunrise")
    val temp: Double = 0.0
)

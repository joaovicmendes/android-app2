package com.motorola.weatherapp.model

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

abstract class WeatherFormater {
    companion object {
        fun formatTime(dt: Long): String {
            val sdf = SimpleDateFormat("dd/MM hh:mm aa", Locale.getDefault())
            return sdf.format(Date(dt*1000))
        }

        fun formatTemperature(temp: Double): String {
            return temp.roundToInt().toString() + "˚C"
        }
    }
}
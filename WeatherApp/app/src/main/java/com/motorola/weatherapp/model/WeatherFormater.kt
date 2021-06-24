package com.motorola.weatherapp.model

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

abstract class WeatherFormater {
    companion object {
        fun formatTime(dt: Long): String {
            val sdf = SimpleDateFormat("dd/MM hh:mm", Locale.getDefault())
            return sdf.format(Date(dt))
        }

        fun formatTemperature(temp: Double): String {
            return temp.roundToInt().toString() + "˚C"
        }
    }
}
package com.motorola.weatherapp;

import android.app.Application;
import android.content.Context

class WeatherApp : Application() {
    var context: Context? = null

    override fun onCreate() {
        super.onCreate()
        this.context = applicationContext
    }
}

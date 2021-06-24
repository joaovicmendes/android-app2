package com.motorola.weatherapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.motorola.weatherapp.model.Forecast
import com.motorola.weatherapp.model.ForecastRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ForecastViewModel : ViewModel() {
    private val repository = ForecastRepository()
    val forecast = MutableLiveData<Forecast>()

    fun fetchWeather() {
        // TODO: get current gps data
        val lat = -22.016720
        val lon = -47.891972
        viewModelScope.launch(Dispatchers.IO) {
            forecast.postValue(repository.fetchWeather(lat, lon))
        }
    }
}
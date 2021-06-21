package com.motorola.weatherapp.model

import androidx.lifecycle.LiveData

class ForecastRepository(private val dao: ForecastDao) {
    fun select(): LiveData<List<Forecast>> {
        return dao.getAll()
    }

    suspend fun insert(forecast: Forecast) {
        dao.insert(forecast)
    }

    suspend fun update(forecast: Forecast) {
        dao.update(forecast)
    }

    suspend fun delete(forecast: Forecast) {
        dao.delete(forecast)
    }
}

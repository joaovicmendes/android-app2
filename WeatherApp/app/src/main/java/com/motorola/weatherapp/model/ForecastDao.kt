package com.motorola.weatherapp.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ForecastDao {
    @Query("SELECT * FROM forecasts")
    fun getAll(): LiveData<List<Forecast>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(forecast: Forecast)

    @Update
    suspend fun update(forecast: Forecast)

    @Delete
    suspend fun delete(forecast: Forecast)
}

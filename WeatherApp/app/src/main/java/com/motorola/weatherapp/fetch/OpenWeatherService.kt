package com.motorola.weatherapp.fetch

import com.motorola.weatherapp.model.Forecast
import retrofit2.http.GET
import retrofit2.http.Query

interface OneCallApiService {
    @GET("data/2.5/onecall")
    suspend fun fetch(
        @Query("lat") lat: Double = -22.016720,
        @Query("lon") lon: Double = -47.891972,
        @Query("appid") appid: String = "",
        @Query("exclude") exclude: String = "minutely",
        @Query("unit") unit: String = "metric"
    ): Forecast
}

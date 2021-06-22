package com.motorola.weatherapp.fetch

import com.motorola.weatherapp.model.Forecast
import retrofit2.http.GET
import retrofit2.http.Query

interface OneCallApiService {
    @GET("data/2.5/onecall")
    suspend fun fetch(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appid: String
    ): Forecast
}

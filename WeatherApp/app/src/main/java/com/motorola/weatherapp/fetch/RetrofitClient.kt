package com.motorola.weatherapp.fetch

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient private constructor() {
    companion object {
        @Volatile
        private var INSTANCE: Retrofit? = null
        private const val baseUrl = Config.baseUrl

        private fun getInstance(): Retrofit {
            val httpClient = OkHttpClient.Builder()
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                INSTANCE = instance
                return instance
            }
        }

        fun <T> createService(serviceClass: Class<T>): T {
            return getInstance().create(serviceClass)
        }
    }
}
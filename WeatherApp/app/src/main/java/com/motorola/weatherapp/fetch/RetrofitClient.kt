package com.motorola.weatherapp.fetch

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

abstract class RetrofitClient {

    companion object {
        @Volatile
        private var INSTANCE: Retrofit? = null
        private const val baseUrl = Config.baseUrl

        private fun getInstance(): Retrofit {
            val interceptor = HttpLoggingInterceptor().apply {
                this.level = HttpLoggingInterceptor.Level.BODY
            }
            val httpClient = OkHttpClient.Builder().addInterceptor(interceptor)
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
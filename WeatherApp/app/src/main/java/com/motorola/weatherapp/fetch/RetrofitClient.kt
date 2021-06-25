package com.motorola.weatherapp.fetch

import com.motorola.weatherapp.WeatherApp
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

abstract class RetrofitClient {

    companion object {
        @Volatile
        private var INSTANCE: Retrofit? = null
        private const val baseUrl = Config.baseUrl

        private fun getInstance(): Retrofit {
            val httpClient = OkHttpClient.Builder()
            setupCache((httpClient))
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

        private fun setupCache(httpClient: OkHttpClient.Builder) {
            val cacheSize = (5 * 1024 * 1024).toLong()
            val context = WeatherApp().context

            context?.let {
                val cache = Cache(context.cacheDir, cacheSize)
                httpClient.cache(cache)
                    .addInterceptor { chain ->
                        var request = chain.request()
                        request = request.newBuilder().header(
                            "Cache-Control",
                            "public, max-age=" + 30
                        ).build()
                        chain.proceed(request)
                    }
            }
        }
    }
}
package com.motorola.weatherapp.ui

import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.motorola.weatherapp.R
import com.motorola.weatherapp.adapter.DailyWeatherAdapter
import com.motorola.weatherapp.adapter.WeatherAdapter
import com.motorola.weatherapp.model.WeatherFormater
import com.motorola.weatherapp.viewmodel.ForecastViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var hourlyAdapter = WeatherAdapter()
    private var dailyAdapter = DailyWeatherAdapter()
    private var forecastViewModel: ForecastViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Setting up Hourly Weather RecyclerView
        rv_hourly_forecast.adapter = hourlyAdapter
        rv_hourly_forecast.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )

        // Setting up Daily Weather RecyclerView
        rv_daily_forecast.adapter = dailyAdapter
        rv_daily_forecast.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )

        forecastViewModel = ViewModelProvider(this).get(ForecastViewModel::class.java)
        forecastViewModel!!.forecast.observe(this, { forecast ->
            // Atualizando conteúdo principal
            tv_curr_forecast_temp.text = WeatherFormater.formatTemperature(forecast.current.temp)
            tv_curr_forecast_time.text = WeatherFormater.formatTime(forecast.current.dt)

            // Atualizando listas de próximos climas
            hourlyAdapter.setList(forecast.hourly)
            dailyAdapter.setList(forecast.daily)

            // Escondendo progressBar
            progress_bar.visibility = View.GONE
            tv_daily_forecast.visibility = View.VISIBLE
            tv_hourly_forecast.visibility = View.VISIBLE
        })

        refresh_layout.setOnRefreshListener {
            forecastViewModel!!.fetchWeather()
            refresh_layout.isRefreshing = false

        }

        forecastViewModel!!.fetchWeather()
    }

}
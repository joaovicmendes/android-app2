package com.motorola.weatherapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.motorola.weatherapp.R
import com.motorola.weatherapp.adapter.WeatherAdapter
import com.motorola.weatherapp.viewmodel.ForecastViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var hourlyAdapter = WeatherAdapter()
    private var dailyAdapter = WeatherAdapter()
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
            tv_curr_forecast_temp.text = forecast.current.temp.toString()
            tv_curr_forecast_time.text = forecast.current.dt.toString()
            hourlyAdapter.setList(forecast.hourly)
            dailyAdapter.setList(forecast.daily)
        })

        forecastViewModel!!.fetchWeather()
    }

}
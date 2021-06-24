package com.motorola.weatherapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.motorola.weatherapp.R
import com.motorola.weatherapp.model.Weather
import com.motorola.weatherapp.model.WeatherFormater
import kotlinx.android.synthetic.main.forecast_item.view.*

class WeatherAdapter : RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {
    private var weatherList = emptyList<Weather>()

    class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        return WeatherViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.forecast_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val curr = weatherList[position]
        holder.itemView.apply {
            tv_weather_time.text = WeatherFormater.formatTime(curr.dt)
            tv_weather_temp.text = WeatherFormater.formatTemperature(curr.temp)
        }
    }

    override fun getItemCount(): Int {
        return this.weatherList.size
    }

    fun setList(weatherList: List<Weather>) {
        this.weatherList = weatherList
        notifyDataSetChanged()
    }

}
package com.motorola.weatherapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "forecasts")
data class Forecast(
    @ColumnInfo(name = "place_holder") val placeHolder: String,
    @PrimaryKey(autoGenerate = true) val uid: Int = 0)
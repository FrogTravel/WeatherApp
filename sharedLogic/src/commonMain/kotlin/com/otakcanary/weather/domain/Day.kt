package com.otakcanary.weather.domain

data class Day(
    val date: String,
    val weatherCode: WmoPresentWeather,
    val temperatureMax: Double,
    val temperatureMin: Double,
)
package com.otakcanary.weather.domain

data class Hour(
    val time: String,
    val temperature: Double,
    val weatherCode: WmoPresentWeather,
)
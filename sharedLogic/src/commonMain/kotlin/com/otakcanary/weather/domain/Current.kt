package com.otakcanary.weather.domain

data class Current(
    val time: String,
    val temperature: Double,
    val weatherCode: WmoPresentWeather
)
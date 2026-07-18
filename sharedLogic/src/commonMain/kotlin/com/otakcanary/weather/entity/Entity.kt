package com.otakcanary.weather.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherResponse(
    @SerialName("hourly")
    val hourly: TemperatureData
)

@Serializable
data class TemperatureData(
    @SerialName("time")
    val time: List<String>,
    @SerialName("temperature_2m")
    val temperature: List<Double>
)
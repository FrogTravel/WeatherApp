package com.otakcanary.weather.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherResponseHourly(
    @SerialName("hourly")
    val hourly: HourlyTemperatureData,
)

@Serializable
data class WeatherResponseDaily(
    @SerialName("daily")
    val daily: DailyTemperatureData
)

@Serializable
data class WeatherResponseCurrent(
    @SerialName("current")
    val current: CurrentTemperatureData
)

@Serializable
data class HourlyTemperatureData(
    @SerialName("time")
    val time: List<String>,
    @SerialName("temperature_2m")
    val temperature: List<Double>,
    @SerialName("weather_code")
    val weatherCode: List<Int>
)

@Serializable
data class DailyTemperatureData(
    @SerialName("time")
    val time: List<String>,
    @SerialName("temperature_2m_max")
    val maxTemperature: List<Double>,
    @SerialName("temperature_2m_min")
    val minTemperature: List<Double>,
    @SerialName("weather_code")
    val weatherCode: List<Int>
)

@Serializable
data class CurrentTemperatureData(
    @SerialName("time")
    val time: String,
    @SerialName("temperature_2m")
    val temperature: Double,
    @SerialName("weather_code")
    val weatherCode: Int
)
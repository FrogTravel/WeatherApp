package com.otakcanary.weather.network

import com.otakcanary.weather.domain.Current
import com.otakcanary.weather.domain.Day
import com.otakcanary.weather.domain.Hour
import com.otakcanary.weather.domain.WmoPresentWeather
import com.otakcanary.weather.entity.CurrentTemperatureData
import com.otakcanary.weather.entity.WeatherResponseCurrent
import com.otakcanary.weather.entity.WeatherResponseDaily
import com.otakcanary.weather.entity.WeatherResponseHourly
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import kotlin.collections.indices

class WeatherApi {
    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                useAlternativeNames = false
            })
        }
    }

    suspend fun getHourlyWeather(): List<Hour> {
        val hourly =
            (httpClient.get("https://api.open-meteo.com/v1/forecast?latitude=52.5244&longitude=13.4105&hourly=temperature_2m,weather_code&current=weather_code")
                .body() as WeatherResponseHourly).hourly
        return hourly.time.indices.map { i ->
            Hour(
                time = hourly.time[i],
                temperature = hourly.temperature[i],
                weatherCode = WmoPresentWeather.fromCodeOrThrow(hourly.weatherCode[i])
            )
        }
    }

    suspend fun getDailyWeather(): List<Day> {
        val daily =
            (httpClient.get("https://api.open-meteo.com/v1/forecast?latitude=52.5244&longitude=13.4105&daily=weather_code,temperature_2m_max,temperature_2m_min")
                .body() as WeatherResponseDaily).daily
        return daily.time.indices.map { i ->
            Day(
                date = daily.time[i],
                temperatureMax = daily.maxTemperature[i],
                temperatureMin = daily.minTemperature[i],
                weatherCode = WmoPresentWeather.fromCodeOrThrow(daily.weatherCode[i])
            )
        }
    }

    suspend fun getCurrentWeather(): Current {
        val current =
            (httpClient.get("https://api.open-meteo.com/v1/forecast?latitude=52.5244&longitude=13.4105&current=temperature_2m,weather_code")
                .body() as WeatherResponseCurrent).current
        return Current(
            current.time,
            current.temperature,
            WmoPresentWeather.fromCodeOrThrow(current.weatherCode)
        )
    }
}
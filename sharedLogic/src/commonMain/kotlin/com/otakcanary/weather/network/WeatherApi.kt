package com.otakcanary.weather.network

import com.otakcanary.weather.domain.Day
import com.otakcanary.weather.entity.WeatherResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class WeatherApi {
    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                useAlternativeNames = false
            })
        }
    }

    suspend fun getNextWeekWeather(): List<Day> {
        val hourly =
            (httpClient.get("https://api.open-meteo.com/v1/forecast?latitude=52.5244&longitude=13.4105&hourly=temperature_2m&current=temperature_2m")
                .body() as WeatherResponse).hourly
        return hourly.time.zip(hourly.temperature) { time, temp ->
            Day(time, temp)
        }
    }
}
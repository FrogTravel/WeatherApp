package com.otakcanary.weather

import com.otakcanary.weather.domain.Current
import com.otakcanary.weather.domain.Day
import com.otakcanary.weather.domain.Hour
import com.otakcanary.weather.network.WeatherApi

class WeatherSDK(val api: WeatherApi) {
    @Throws(Exception::class)
    suspend fun getHourlyWeather(): List<Hour> {
        return api.getHourlyWeather()
    }

    @Throws(Exception::class)
    suspend fun getDailyWeather(): List<Day> {
        return api.getDailyWeather()
    }

    @Throws(Exception::class)
    suspend fun getCurrentWeather(): Current {
        return api.getCurrentWeather()
    }
}
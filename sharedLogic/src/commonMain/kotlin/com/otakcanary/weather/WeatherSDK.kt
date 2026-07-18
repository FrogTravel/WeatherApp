package com.otakcanary.weather

import com.otakcanary.weather.domain.Day
import com.otakcanary.weather.network.WeatherApi

class WeatherSDK(val api: WeatherApi) {
    @Throws(Exception::class)
    suspend fun getNextWeekWeather(): List<Day> {
        return api.getNextWeekWeather()
    }
}
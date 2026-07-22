package com.otakcanary.weather.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.otakcanary.weather.WeatherSDK
import com.otakcanary.weather.domain.Day
import com.otakcanary.weather.domain.Hour
import com.otakcanary.weather.network.WeatherApi
import kotlinx.coroutines.launch

class WeatherViewModel(private val sdk: WeatherSDK) : ViewModel() {
    private val _state = mutableStateOf(WeatherScreenState())
    val state: State<WeatherScreenState> = _state

    init {
        loadWeather()
    }

    fun loadWeather() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)

            _state.value = _state.value.copy(hourlyWeather = sdk.getHourlyWeather())
            _state.value = _state.value.copy(dailyWeather = sdk.getDailyWeather())

            _state.value = _state.value.copy(isLoading = false)
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val sdk = WeatherSDK(WeatherApi())
                WeatherViewModel(
                    sdk
                )
            }
        }
    }
}

data class WeatherScreenState(
    val isLoading: Boolean = false,
    val hourlyWeather: List<Hour> = emptyList(),
    val dailyWeather: List<Day> = emptyList()
)
package com.crazycats.weatherappmvicompose.presentation.model

import com.crazycats.weatherappmvicompose.domain.weather.WeatherInfo

data class WeatherState(
    val weatherInfo: WeatherInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)

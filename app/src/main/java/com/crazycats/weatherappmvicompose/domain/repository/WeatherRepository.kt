package com.crazycats.weatherappmvicompose.domain.repository

import com.crazycats.weatherappmvicompose.domain.util.Resource
import com.crazycats.weatherappmvicompose.domain.weather.WeatherInfo

interface WeatherRepository {

    suspend fun getWeather(lat: Double, long: Double) : Resource<WeatherInfo>
}
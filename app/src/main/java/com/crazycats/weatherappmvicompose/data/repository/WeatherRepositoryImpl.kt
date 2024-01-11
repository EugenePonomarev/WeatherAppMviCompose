package com.crazycats.weatherappmvicompose.data.repository

import com.crazycats.weatherappmvicompose.data.mappers.toWeatherInfo
import com.crazycats.weatherappmvicompose.data.remote.WeatherApi
import com.crazycats.weatherappmvicompose.domain.repository.WeatherRepository
import com.crazycats.weatherappmvicompose.domain.util.Resource
import com.crazycats.weatherappmvicompose.domain.weather.WeatherInfo
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(private val api: WeatherApi) : WeatherRepository {
    override suspend fun getWeather(lat: Double, long: Double): Resource<WeatherInfo> {
        return try {
            Resource.Success(
                data = api.getWeatherData(
                    lat = lat,
                    long = long
                ).toWeatherInfo()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown message occurred")
        }
    }
}
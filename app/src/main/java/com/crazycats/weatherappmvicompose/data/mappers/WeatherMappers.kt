package com.crazycats.weatherappmvicompose.data.mappers

import com.crazycats.weatherappmvicompose.data.model.WeatherDataDto
import com.crazycats.weatherappmvicompose.data.model.WeatherDto
import com.crazycats.weatherappmvicompose.domain.weather.WeatherData
import com.crazycats.weatherappmvicompose.domain.weather.WeatherInfo
import com.crazycats.weatherappmvicompose.domain.weather.WeatherType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private data class IndexedWeatherData(
    val index: Int,
    val data: WeatherData
)
fun WeatherDataDto.toWeatherDataMap() : Map<Int, List<WeatherData>> {
    return time.mapIndexed { index, time ->
        val temperature = temperatures[index]
        val weatherCode = weatherCodes[index]
        val windSpeed = windSpeeds[index]
        val pressure = pressures[index]
        val humidity = humidities[index]
        IndexedWeatherData(
            index = index,
            data = WeatherData(
                time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
                temperatureCelsius = temperature,
                pressure = pressure,
                windSpeed = windSpeed,
                humidity = humidity,
                weatherType = WeatherType.fromWMO(weatherCode)
            )
        )
    }.groupBy {
        it.index / 24
    }.mapValues {
        it.value.map { indexedWeatherData -> indexedWeatherData.data }
    }
}

fun WeatherDto.toWeatherInfo() : WeatherInfo {
    val weatherDataMAp = weatherData.toWeatherDataMap()
    val now = LocalDateTime.now()
    val currentWeatherData = weatherDataMAp[0]?.find {
        val hour = if (now.minute < 30) now.hour else now.hour + 1
        it.time.hour == hour
    }

    return WeatherInfo(
        weatherDataPerDay = weatherDataMAp,
        currentWeatherData = currentWeatherData
    )
}